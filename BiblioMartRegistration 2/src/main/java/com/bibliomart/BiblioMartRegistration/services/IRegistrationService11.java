package com.bibliomart.BiblioMartRegistration.services;

import com.bibliomart.BiblioMartRegistration.entity.User;

import java.util.List;

public interface IRegistrationService11 {
    User save(User user);
    User findById(int id);
    void deleteById(int id);
    List<User> findAll();
    User findByEmail(String email);
}
