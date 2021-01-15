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
    private int age;
    private String address;
    private int fb;
    private int gmail;
    private String timestamps;
    private String accessTokenFb; // (if logged in through fb)
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

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public int getFb() {
        return fb;
    }

    public void setFb(int fb) {
        this.fb = fb;
    }

    public void setGmail(int gmail) {
        this.gmail = gmail;
    }

    public void setAccessTokenFb(String accessTokenFb) {
        this.accessTokenFb = accessTokenFb;
    }

    public void setAccessTokenGmail(String accessTokenGmail) {
        this.accessTokenGmail = accessTokenGmail;
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

    public void setTimestamps(String timestamps) {
        this.timestamps = timestamps;
    }
}
