package com.apascual.spring.transaction.service;

import com.apascual.spring.transaction.persistence.entity.Role;
import com.apascual.spring.transaction.persistence.entity.User;

import java.util.Set;

public interface UserService {

    User create(final User user);

    User update(final User user);

    User getUser(final Long id);

    Set<Role> getRoleByUserId(final Long id);

}
