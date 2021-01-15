package com.bibliomart.BiblioMartRegistration.repository;

import com.bibliomart.BiblioMartRegistration.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRegistrationRepository extends CrudRepository<User, Integer> {
    List<User> findByEmail(String email);
}
