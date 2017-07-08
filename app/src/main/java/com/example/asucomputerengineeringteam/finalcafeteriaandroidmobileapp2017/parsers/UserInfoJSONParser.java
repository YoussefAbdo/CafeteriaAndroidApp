package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.parsers;



import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.DataModels.UserInfo;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserInfoJSONParser
{

    public static List<UserInfo> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<UserInfo> userInfoList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                UserInfo userInfo = new UserInfo();

                userInfo.setEmail(obj.getString("Email"));
                userInfo.setHasRegistered(obj.getBoolean("HasRegistered"));
                userInfo.setLoginProvider(obj.getString("LoginProvider"));
                userInfo.setUserId(obj.getString("UserId"));


                userInfoList.add(userInfo);
            }

            return userInfoList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
