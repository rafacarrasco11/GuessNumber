package com.example.guessnumber;

import android.app.Application;

public class GuessNumberApplication extends Application {

    private final int USERNAME_MAXLENGHT = 20;
    private String userName;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName.length() > USERNAME_MAXLENGHT) {
            userName = userName.substring(0,20);
        }
        this.userName = userName;
    }
}
