package com.bibliomart.BiblioMartRegistration.services.impl;

import com.bibliomart.BiblioMartRegistration.entity.Id;
import com.bibliomart.BiblioMartRegistration.entity.User;
import com.bibliomart.BiblioMartRegistration.repository.IRegistrationRepository;
import com.bibliomart.BiblioMartRegistration.services.IRegistrationService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RegistrationServiceImpl implements IRegistrationService {
    @Autowired
    IRegistrationRepository iRegistrationRepository;

    public User addUser(User user)
    {
        Timestamp sqlTimestamp = new Timestamp(System.currentTimeMillis());
        user.setTimestamps(sqlTimestamp + "");
        return save(user);
    }

    public List<String> getTimeStampsForParticularDate(String date, int id) throws Exception
    {
        List<String> requiredTimeStamps = new ArrayList<String>();
        User user = new User();
        user = findById(id);
        String timeStampsStr = user.getTimestamps();
        String timeStampsArr [] = timeStampsStr.split(",");
        Date searchDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        System.out.println("hhhh");
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

    public List<String> getTimeStamps(int id) throws Exception
    {
        List<String> requiredTimeStamps = new ArrayList<String>();
        User user = new User();
        user = findById(id);
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

    @Transactional(readOnly = false)
    public User save(User user)
    {
        return iRegistrationRepository.save(user);
    }
    public User findById(int id)
    {
        System.out.println(id);
        System.out.println(iRegistrationRepository.findById(id).get());
        return iRegistrationRepository.findById(id).get();
    }
    @Transactional(readOnly = false)
    public void deleteById(int id)
    {
        iRegistrationRepository.deleteById(id);
    }
    public List<User> findAll()
    {
        Iterable<User> userIterable = iRegistrationRepository.findAll();
        List<User> userList = new ArrayList<>();
        userIterable.forEach(userList::add);
        return userList;
    }
    @Override
    public User findByEmail(String email)
    {
        return iRegistrationRepository.findByEmail(email);
    }


    @Transactional(readOnly = false)//not required
    public Id loginUser(User user) throws Exception
    {
        Id id = new Id();

        User user1 = findByEmail(user.getEmail());
        if(user1 == null)
        {
            user1 = new User();
        }

        if(user.getGmail() == 1)
        {
            id = loginGoogle(user);
            if(id.getStatus() == 200)
            {
                user1.setEmail(user.getEmail());
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
                id.setName(user1.getName());
                id.setStatus(200);
                id.setEmail(user1.getEmail());
            }
            else
            {
                id.setUserId(-1);
                id.setStatus(400);
            }
        }

        Timestamp sqlTimestamp = new Timestamp(System.currentTimeMillis());
        String timestamps = user1.getTimestamps();
        if(timestamps == null)
        {
            timestamps = sqlTimestamp + "";
            user1.setTimestamps(timestamps);
        }
        else
        {
            timestamps = timestamps.concat(",");
            timestamps = timestamps.concat(sqlTimestamp + "");
            user1.setTimestamps(timestamps);
        }
        save(user1);

        System.out.println(id.getUserId());
        return id;
    }

    Id loginGoogle (User user) throws Exception {

        Id id = new Id();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
                .setAudience(Collections.singletonList("39901288485-drpr7fbip0401d1r62vm3t7rr8i64ruc.apps.googleusercontent.com"))
                .build();

        GoogleIdToken idToken = verifier.verify(user.getAccessTokenGmail());
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();

            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            String email = payload.getEmail();
            System.out.println(email);
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            System.out.println(emailVerified);

            // redundant use of repo
            if(email.equals(findByEmail(email).getEmail()))
            {
                id.setUserId(findByEmail(email).getUserId());
                id.setEmail(email);
                id.setName(user.getName());
                id.setStatus(200);
            }

            else
            {

                user.setPassword("123456789@#$");
                User user1 = save(user);
                id.setUserId(user1.getUserId());
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

//    User loginFb (User user)
//    {
//        String accessToken = "EAAJyHBh5uiUBAN4ZAyRfwB9H48gIRpEt6hhPAlqVVJXLtjv6WCV2QBojYOMJR8XqJQ06jfCzCsIYdUUloLgYo785QsdtBZBfoGDRcQfKxpZC6Wrc9ZApqZCUYBgdwCCKa0aS2WdprQTd2n0CBoezsH0NbtISm3YWFOiYP0A9DECfALqoeSEUagPfER2LlZBZBbHi0ERdiGMrgZDZD";
//        String appAccessToken = "EAAJyHBh5uiUBAIVsFKGnVljhKZCYpRZAL7Ng9plVUotFF6CwDO5FnkZBrrPVIKq2bkID8xvL1LVF1TUQ8iNXRBkGU0bkR4HlEFNUv5uC6nHM05ARtWJOGipvbsEthZCVAMYenWdDkVkzs1NKIJGzLsO4y2y3ubUlJjCHnn2KZCUGCOFrs32wXBsHDK2G7ltwZD";
//    }
}
