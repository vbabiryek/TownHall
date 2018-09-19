package com.example.vivianbabiryekulumba.townhall.room_persistence;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class UserProfile {

    @PrimaryKey
    private int user_profile_id;

    @ColumnInfo(name = "user_profile_name")
    private String user_profile_name;

    @ColumnInfo(name = "user_profile_password")
    private String user_profile_password;

    public String getUser_profile_name() {
        return user_profile_name;
    }

    public void setUser_profile_name(String user_profile_name) {
        this.user_profile_name = user_profile_name;
    }

    public String getUser_profile_password() {
        return user_profile_password;
    }

    public void setUser_profile_password(String user_profile_password) {
        this.user_profile_password = user_profile_password;
    }
}
