package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;


public class UserInfo
{
    private String Email;
    private boolean HasRegistered;
    private String LoginProvider;
    private String UserId;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean getHasRegistered() {
        return HasRegistered;
    }

    public void setHasRegistered(boolean hasRegistered) {
        HasRegistered = hasRegistered;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getLoginProvider() {
        return LoginProvider;
    }

    public void setLoginProvider(String loginProvider) {
        LoginProvider = loginProvider;
    }
}
