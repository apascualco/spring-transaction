package com.apascual.spring.transaction.service;

import com.apascual.spring.transaction.exception.UserNotFoundException;
import com.apascual.spring.transaction.persistence.entity.Role;
import com.apascual.spring.transaction.persistence.entity.User;
import com.apascual.spring.transaction.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(final User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(final User user) {
        final User updatableUser = userRepository.findById(user.getId())
                .orElseThrow(NullPointerException::new);
        updatableUser.setActive(user.isActive());
        updatableUser.setEmail(user.getEmail());
        updatableUser.setFirstName(user.getFirstName());
        updatableUser.setLastName(user.getLastName());
        updatableUser.setRoles(user.getRoles());
        return updatableUser;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(final Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getRoleByUserId(final Long id) {
        final User user = getUser(id);
        Hibernate.initialize(user.getRoles());
        return user.getRoles();
    }

}
