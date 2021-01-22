package com.bibliomart.BiblioMartRegistration.controller;

import com.bibliomart.BiblioMartRegistration.entity.Id;
import com.bibliomart.BiblioMartRegistration.entity.User;
import com.bibliomart.BiblioMartRegistration.services.IRegistrationService;
import com.bibliomart.BiblioMartRegistration.services.impl.RegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistrationController {

//    private static final int OK_STATUS = 200;
//    private static final int ERROR_STATUS = 400;

    @Autowired
    IRegistrationService registrationServiceImpl;

    @PostMapping(value = "/registration")
    User addUser(@RequestBody User user)
    {
        return registrationServiceImpl.save(user);
    }

    @GetMapping(value = "/profile/{userId}")
    User getProfile(@PathVariable ("userId") int id)
    {
        return registrationServiceImpl.findById(id);
    }

//    @GetMapping(value = "/loginHistory/{date}")
//    List<String> getTimeStampsForParticularDate(@PathVariable ("date") String date, @PathVariable("userId") int id)
//    {
//
//    }
//
//    @GetMapping(value = "/loginHistory")
//    List<String> getTimeStamps()
//    {
//
//    }

    @PostMapping(value = "/login")
    Id loginUser(@RequestBody User user)
    {
        Id id = new Id();
        User user1 = registrationServiceImpl.findByEmail(user.getEmail());
        if(user1.getFb() == 1 || user1.getGmail() == 1)
        {
            // Verify Access Token
        }
        else
        {
            if (user.getPassword().equals(user1.getPassword())) {
                id.setUserId(user1.getUserId());
                id.setStatus(200);
            }
            else
            {
                id.setUserId(-1);
                id.setStatus(400);
            }
        }
        return id;
    }

    @PutMapping(value = "/updateProfile/{userId}")
    User updateUserProfile(@RequestBody User user)
    {
        return registrationServiceImpl.save(user);
    }
}
