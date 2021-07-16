package com.codeinger.technorizentask.model;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity()
public class UserModel {

    @PrimaryKey(autoGenerate = true)
    private long U_id;
    private String name;
    private String last_name;
    private String mobile_no;
    private String email;
    private String password;
    private Bitmap profile;

    public UserModel() {
    }

    public UserModel(long u_id, String name, String last_name, String mobile_no, String email, String password, Bitmap profile) {
        U_id = u_id;
        this.name = name;
        this.last_name = last_name;
        this.mobile_no = mobile_no;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "U_id=" + U_id +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }

    public long getU_id() {
        return U_id;
    }

    public void setU_id(int u_id) {
        U_id = u_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Bitmap getProfile() {
        return profile;
    }

    public void setProfile(Bitmap profile) {
        this.profile = profile;
    }
}
