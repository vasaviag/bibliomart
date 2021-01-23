package com.bibliomart.BiblioMartRegistration.controller;

import com.bibliomart.BiblioMartRegistration.entity.Id;
import com.bibliomart.BiblioMartRegistration.entity.User;
import com.bibliomart.BiblioMartRegistration.services.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@CrossOrigin(origins = "*")
public class RegistrationController {
    @Autowired
    IRegistrationService iRegistrationService ;

    @CrossOrigin
    @PostMapping(value = "/registration")
    User addUser(@RequestBody User user)
    {
        System.out.println(user.getImage());
        return iRegistrationService.addUser(user);
    }

    @CrossOrigin
    @GetMapping(value = "/registration/profile/{userId}")
    User getProfile(@PathVariable("userId") int id)
    {
        return iRegistrationService.findById(id);
    }

    @CrossOrigin
    @GetMapping(value = "/registration/loginHistory/{date}/{userId}")
    List<String> getTimeStampsForParticularDate(@PathVariable ("date") String date, @PathVariable("userId") int id) throws Exception
    {
        System.out.println(date);
        return iRegistrationService.getTimeStampsForParticularDate(date, id);
    }

    @CrossOrigin
    @GetMapping(value = "/registration/loginHistory/{userId}")
    List<String> getTimeStamps(@PathVariable("userId") int id) throws Exception
    {
        return iRegistrationService.getTimeStamps(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/registration/login")
    Id loginUser(@RequestBody User user) throws Exception
    {
        System.out.println("hhhhh");
        return iRegistrationService.loginUser(user);
    }

    @CrossOrigin
    @PutMapping(value = "/registration/updateProfile")
    User updateUserProfile(@RequestBody User user)
    {
        User user1 = iRegistrationService.findById(user.getUserId());
        user.setTimestamps(user1.getTimestamps());
        return iRegistrationService.save(user);
    }
}
