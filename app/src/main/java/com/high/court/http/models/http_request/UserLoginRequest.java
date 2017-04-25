package com.high.court.http.models.http_request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by admin on 4/25/2017.
 */

public class UserLoginRequest {

    public static Map<String, String> makeLoginRequest(String enrollment_number, String password){
        Map<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("LoginForm[enrollment_number]", enrollment_number);
        stringStringHashMap.put("LoginForm[password]", password);
        return stringStringHashMap;
    }

}
