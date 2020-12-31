package com.apascual.spring.transaction.persistence.repository;

import com.apascual.spring.transaction.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

    Optional<User> findByEmail(final String email);

}
