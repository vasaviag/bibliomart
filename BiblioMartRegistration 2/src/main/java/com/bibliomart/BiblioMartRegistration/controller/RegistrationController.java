package com.bibliomart.BiblioMartRegistration.controller;

import com.bibliomart.BiblioMartRegistration.entity.Id;
import com.bibliomart.BiblioMartRegistration.entity.User;
import com.bibliomart.BiblioMartRegistration.services.IRegistrationService11;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    List<String> getTimeStampsForParticularDate(@PathVariable ("date") String date, @PathVariable("userId") int id) throws Exception
    {
        List<String> requiredTimeStamps = new ArrayList<String>();
        User user = new User();
        user = iRegistrationService.findById(id);
        String timeStampsStr = user.getTimestamps();
        String timeStampsArr [] = timeStampsStr.split(",");
        Date searchDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        for (String timeStamp: timeStampsArr)
        {
            Date dateToMatch = new SimpleDateFormat("yyyy-MM-dd").parse(timeStamp);

            if(dateToMatch.equals(searchDate))
            {
                Date dateAndTimeToStore = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStamp);
                requiredTimeStamps.add(dateAndTimeToStore+"");
            }
        }
        return requiredTimeStamps;
    }

    @GetMapping(value = "/loginHistory/{userId}")
    List<String> getTimeStamps(@PathVariable("userId") int id) throws Exception
    {
        List<String> requiredTimeStamps = new ArrayList<String>();
        User user = new User();
        user = iRegistrationService.findById(id);
        String timeStampsStr = user.getTimestamps();
        String timeStampsArr [] = timeStampsStr.split(",");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currDate = new Date();

        for (String timeStamp: timeStampsArr) {
            Date dateToMatch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timeStamp);
            long diff = currDate.getTime() - dateToMatch.getTime();
            long diffInDays = TimeUnit.MILLISECONDS.toDays(diff);
            if(diffInDays < 180) {
                requiredTimeStamps.add(dateToMatch + "");
            }
        }
        return requiredTimeStamps;
    }

    @PostMapping(value = "/login")
    Id loginUser(@RequestBody User user) throws Exception
    {
        Id id = new Id();
        User user1 = iRegistrationService.findByEmail(user.getEmail());

        if(user.getGmail() == 1)
        {
            id = loginGoogle(user);
            if(id.getStatus() == 200)
            {
                user1.setGmail(1);
                user1.setAccessTokenGmail(user.getAccessTokenGmail());
            }
        }
        else if(user.getFb()==1)
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
        if(id.getStatus() == 200)
        {
            Timestamp sqlTimestamp = new Timestamp(System.currentTimeMillis());
            String timestamps = user1.getTimestamps();
            if(timestamps == null)
            {
                timestamps = sqlTimestamp+"";
                user1.setTimestamps(timestamps);
            }
            else
            {
                timestamps = timestamps.concat(",");
                timestamps = timestamps.concat(sqlTimestamp+"");
                user.setTimestamps(timestamps);
            }
            iRegistrationService.save(user1);
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


    Id loginGoogle (@RequestBody User user) throws Exception {

        Id id = new Id();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList("39901288485-drpr7fbip0401d1r62vm3t7rr8i64ruc.apps.googleusercontent.com"))
                .build();

        GoogleIdToken idToken = verifier.verify(user.getAccessTokenGmail());
        if (idToken != null) {
            Payload payload = idToken.getPayload();

            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            System.out.println(emailVerified);

            if(email.equals(user.getEmail()))
            {
                id.setUserId(user.getUserId());
                id.setStatus(200);
            }
            System.out.println(email);
        }

        else {
            id.setStatus(400);
            System.out.println("Invalid ID token.");
        }
        return id;
    }

}
