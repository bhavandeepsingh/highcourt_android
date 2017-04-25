package com.high.court.helpers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.high.court.HighCourtApplication;
import com.high.court.http.models.UserLoginModel;

/**
 * Created by admin on 4/25/2017.
 */

public class UserHelper {

    static String APP_USER_LOGIN_ID = "APP_USER_LOGIN_ID";
    static String APP_USER_NAME = "APP_USER_NAME";
    static String APP_USER_PHONE = "APP_USER_PHONE";
    static String APP_USER_EMAIL = "APP_USER_EMAIL";
    static String APP_USER_ACCESS_TOKEN = "APP_USER_ACCESS_TOKEN";
    static String APP_USER_STATUS = "APP_USER_STATUS";
    static String APP_USER_CREATED_AT = "APP_USER_CREATED_AT";
    static String APP_USER_UPDATED_AT = "APP_USER_UPDATED_AT";

    public static boolean login(UserLoginModel userLoginModel){
        if(userLoginModel == null) return false;
        return setSharedPreferences(userLoginModel);
    }

    public static boolean logout(){
        return updateAppUser(APP_USER_LOGIN_ID, "0");
    }

    public static boolean getState(){
        if(getId() > 0) return true;
        return false;
    }

    public static long getId(){
        return Long.valueOf(getSharedPreferences().getString(APP_USER_LOGIN_ID, "0"));
    }

    public static String getName(){
        return getSharedPreferences().getString(APP_USER_NAME, "");
    }

    public static String getEmail(){
        return getSharedPreferences().getString(APP_USER_EMAIL, "");
    }

    public static String getAppUserPhone(){
        return getSharedPreferences().getString(APP_USER_PHONE, "");
    }

    public static boolean getAppUserStatus(){
        return  Boolean.getBoolean(getSharedPreferences().getString(APP_USER_STATUS, "false"));
    }

    public static String getUserPhone(){
        return getSharedPreferences().getString(APP_USER_PHONE, "");
    }

    static boolean setSharedPreferences(UserLoginModel userLoginModel){
        /*if(!updateAppUser(APP_USER_LOGIN_ID, String.valueOf(userLoginModel.getApp_user_id()))) return false;
        if(!updateAppUser(APP_USER_NAME, userLoginModel.getName())) return false;
        if(!updateAppUser(APP_USER_EMAIL, userLoginModel.getEmail())) return false;
        if(!updateAppUser(APP_USER_PHONE, userLoginModel.getPhone())) return false;
        if(!updateAppUser(APP_USER_STATUS, String.valueOf(userLoginModel.getStatus()))) return false;
        if(!updateAppUser(APP_USER_CREATED_AT, userLoginModel.getCreated_at())) return false;
        if(!updateAppUser(APP_USER_UPDATED_AT, userLoginModel.getUpdated_at())) return false;*/
        return true;
    }


    static boolean updateAppUser(String name, String value){
        SharedPreferences.Editor editor = getEditor();
        editor.putString(name, value);
        return editor.commit();
    }

    static SharedPreferences.Editor getEditor(){
        return getSharedPreferences().edit();
    }

    static SharedPreferences getSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(HighCourtApplication.getHighCourtApplicationContext());
    }


}
