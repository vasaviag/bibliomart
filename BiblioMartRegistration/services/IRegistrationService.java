package com.bibliomart.BiblioMartRegistration.services;

import com.bibliomart.BiblioMartRegistration.entity.Id;
import com.bibliomart.BiblioMartRegistration.entity.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IRegistrationService {
    User save(User user);
    User findById(int id);
    void deleteById(int id);
    List<User> findAll();
    User findByEmail(String email);
    User addUser(User user);
    List<String> getTimeStampsForParticularDate( String date, int id) throws Exception;
    List<String> getTimeStamps(int id) throws Exception;
    Id loginUser(User user) throws Exception;
}
