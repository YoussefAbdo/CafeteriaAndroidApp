package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.parsers;

/**
 * Created by Abdelrahman_Attia on 10/07/2017.
 */


import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserJSONParser
{
    public static List<User> parseFeed(String content)
    {
        try {
            JSONArray ar = new JSONArray(content);
            List<User> userList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                User user = new User();

                user.setId(obj.getString("Id"));
                user.setEmail(obj.getString("Email"));
                user.setEmailConfirmed(obj.getBoolean("EmailConfirmed"));
                user.setPasswordHash(obj.getString("PasswordHash"));
                user.setSecurityStamp(obj.getString("SecurityStamp"));
                user.setPhoneNumber(obj.getString("PhoneNumber"));
                user.setPhoneNumberConfirmed(obj.getBoolean("PhoneNumberConfirmed"));
                user.setTwoFactorEnabled(obj.getBoolean("TwoFactorEnabled"));
                user.setLockoutEndDateUtc(obj.getString("LockoutEndDateUtc"));
                user.setLockoutEnabled(obj.getBoolean("LockoutEnabled"));
                user.setAccessFailedCount(obj.getInt("AccessFailedCount"));
                user.setUserName(obj.getString("UserName"));
                user.setFirstName(obj.getString("FirstName"));
                user.setLastName(obj.getString("LastName"));
                user.setImageData(obj.getString("ImageData"));
                user.setRoles(obj.getString("Roles"));
                userList.add(user);
            }

            return userList;
        } catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
