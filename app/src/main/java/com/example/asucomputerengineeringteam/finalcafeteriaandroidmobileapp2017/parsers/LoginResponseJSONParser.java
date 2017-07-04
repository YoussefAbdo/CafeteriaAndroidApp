package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.parsers;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.LoginResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class LoginResponseJSONParser {

    public static List<LoginResponse> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<LoginResponse> loginResponseList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                LoginResponse loginResponse = new LoginResponse();

                loginResponse.setAccess_token(obj.getString("access_token"));
                loginResponse.setToken_type(obj.getString("token_type"));
                loginResponse.setUserName(obj.getString("userName"));


                loginResponseList.add(loginResponse);
            }

            return loginResponseList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

