package com.apascual.spring.transaction.service;

import com.apascual.spring.transaction.ExampleApplication;
import com.apascual.spring.transaction.persistence.entity.Role;
import com.apascual.spring.transaction.persistence.entity.User;
import com.apascual.spring.transaction.persistence.repository.RoleRepository;
import org.hibernate.LazyInitializationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ExampleApplication.class)
public class UserServiceTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserService userService;


    @Before
    public void setUp() {
        final User user = new User();
        user.setActive(true);
        user.setPassword("qwerty");
        user.setFirstName("Robert");
        user.setLastName("Lopez");
        user.setEmail("roper@gmail.com");

        final Set<Role> role = new HashSet<>();
        role.add(roleRepository.save(new Role("Costumer")));
        role.add(roleRepository.save(new Role("Admin")));

        user.setRoles(role);

        userService.create(user);
    }

    @Test(expected = LazyInitializationException.class)
    public void lazy_exception() {
        final User user = userService.getUser(1L);
        assertNotNull(user);
        //Force lazy initialization exception
        user.getRoles().size();
    }

    @Test
    public void roles_with_readOnly() {
        assertEquals(userService.getRoleByUserId(1L).size(), 2);
    }

    @Test
    public void update_user() {
        final String lastName = "PacoDos";
        final User user = userService.getUser(1L);
        user.setLastName(lastName);
        userService.update(user);
        assertEquals(lastName, userService.getUser(1L).getLastName());
    }

}
