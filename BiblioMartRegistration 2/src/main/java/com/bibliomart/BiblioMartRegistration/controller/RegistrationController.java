package com.bibliomart.BiblioMartRegistration.controller;

import com.bibliomart.BiblioMartRegistration.entity.Id;
import com.bibliomart.BiblioMartRegistration.entity.User;
import com.bibliomart.BiblioMartRegistration.services.IRegistrationService11;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;


import java.net.URL;
import java.util.*;

@RestController
public class RegistrationController {
    @Autowired
    IRegistrationService11 iRegistrationService ;

    @PostMapping(value = "/registration")
    User addUser(@RequestBody User user)
    {
        return iRegistrationService.save(user);
    }

    @GetMapping(value = "/profile/{userId}")
    User getProfile(@PathVariable("userId") int id)
    {
        return iRegistrationService.findById(id);
    }

    @GetMapping(value = "/loginHistory/{date}/{userId}")
    List<String> getTimeStampsForParticularDate(@PathVariable ("date") String date, @PathVariable("userId") int id)
    {
        User user = new User();
        user = iRegistrationService.findById(id);
        String s = user.getTimestamps();
        String arr [] = s.split(",");
        for (String s1: arr) {


        }
    }

    @GetMapping(value = "/loginHistory/{userId}")
    List<String> getTimeStamps(@PathVariable("userId") int id)
    {
        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, -180);
        Date today180 = cal.getTime();


    }

    @PostMapping(value = "/login")
    Id loginUser(@RequestBody User user)
    {
        Id id = new Id();
        User user1 = iRegistrationService.findByEmail(user.getEmail());
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

    @PutMapping(value = "/updateProfile")
    User updateUserProfile(@RequestBody User user)
    {
        return iRegistrationService.save(user);
    }

//    @PostMapping(value = "/login/fb")
//    User loginFb (User user)
//    {
//        String accessToken = "EAAJyHBh5uiUBAN4ZAyRfwB9H48gIRpEt6hhPAlqVVJXLtjv6WCV2QBojYOMJR8XqJQ06jfCzCsIYdUUloLgYo785QsdtBZBfoGDRcQfKxpZC6Wrc9ZApqZCUYBgdwCCKa0aS2WdprQTd2n0CBoezsH0NbtISm3YWFOiYP0A9DECfALqoeSEUagPfER2LlZBZBbHi0ERdiGMrgZDZD";
//        String appAccessToken = "EAAJyHBh5uiUBAIVsFKGnVljhKZCYpRZAL7Ng9plVUotFF6CwDO5FnkZBrrPVIKq2bkID8xvL1LVF1TUQ8iNXRBkGU0bkR4HlEFNUv5uC6nHM05ARtWJOGipvbsEthZCVAMYenWdDkVkzs1NKIJGzLsO4y2y3ubUlJjCHnn2KZCUGCOFrs32wXBsHDK2G7ltwZD";
//    }

    @PostMapping(value = "/loginGoogle")
    User loginGoogle (@RequestBody User user) throws Exception {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList("39901288485-drpr7fbip0401d1r62vm3t7rr8i64ruc.apps.googleusercontent.com"))
                .build();

        System.out.println(user.getAccessTokenGmail());

        // (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify(user.getAccessTokenGmail());
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");
            System.out.println(email);

        }

        else {
            System.out.println("Invalid ID token.");
        }
        return user;
    }

}
