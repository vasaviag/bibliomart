package com.bibliomart.BiblioMartRegistration.entity;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Table(name = "user1")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String email;
    private String password;
    private String image;
    private String name;
    private String gender;
    private String age;
    private String address;
    private int fb;
    private int gmail;
    private String timestamps;
    private String fbUserId;
    private String accessTokenFb; // (if logged in through fb)
    private String gmailUserId;
    private String accessTokenGmail; // (if logged in through gmail)


    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public int getFb() {
        return fb;
    }

    public int getGmail() {
        return gmail;
    }

    public String getTimestamps() {
        return timestamps;
    }

    public String getAccessTokenFb() {
        return accessTokenFb;
    }

    public String getAccessTokenGmail() {
        return accessTokenGmail;
    }
}
