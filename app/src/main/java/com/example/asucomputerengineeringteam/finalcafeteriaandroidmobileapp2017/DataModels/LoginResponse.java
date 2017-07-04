package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels;

public class LoginResponse
{
    private String access_token;
    private String token_type;
    private String userName;


    public LoginResponse()
    {
        access_token = "";
        token_type = "";
        userName = "";

    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
