package com.high.court.helpers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.high.court.HighCourtApplication;
import com.high.court.http.models.ProfileModel;
import com.high.court.http.models.UserLoginModel;

/**
 * Created by admin on 4/25/2017.
 */

public class UserHelper {

    static String APP_USER_LOGIN_ID = "APP_USER_LOGIN_ID";
    static String APP_USER_NAME = "APP_USER_NAME";
    static String APP_USER_PHONE = "APP_USER_PHONE";
    static String APP_USER_EMAIL = "APP_USER_EMAIL";
    static String APP_USER_LOCATION = "APP_USER_LOCATION";
    static String APP_USER_WEB_SITE = "APP_USER_WEB_SITE";
    static String APP_USER_BIO = "APP_USER_BIO";
    static String APP_USER_TIME_ZONE = "APP_USER_TIME_ZONE";
    static String APP_USER_DESIGNATION = "APP_USER_TIME_DESIGNATION";
    static String APP_USER_PROFILE = "APP_USER_TIME_PROFILE";
    static String APP_USER_ENROLLMENT_NUMBER = "APP_USER_TIME_ENROLLMENT_NUMBER";
    static String APP_USER_MEMBER_SHIP_NO = "APP_USER_TIME_MEMBER_SHIP_NO";
    static String APP_USER_LANDLINE= "APP_USER_LANDLINE";
    static String APP_USER_MOBILE= "APP_USER_MOBILE";
    static String APP_USER_RESIDENTIAL= "APP_USER_RESIDENTIAL";
    static String APP_USER_COURT_ADDRESS= "APP_USER_COURT_ADDRESS";
    static String APP_USER_BLOOD_GROUP= "APP_USER_BLOOD_GROUP";
    static String APP_USER_LAT_01= "APP_USER_LAT_01";
    static String APP_USER_LONG_01= "APP_USER_LONG_01";
    static String APP_USER_LAT_02 = "APP_USER_LAT_02";
    static String APP_USER_LONG_02 = "APP_USER_LONG_02";
    static String APP_USER_LOGIN_STATUS = "APP_USER_LOGIN_STATUS";
    static String APP_USER_PROFILE_PIC = "APP_USER_PROFILE_PIC";

    public static boolean login(UserLoginModel userLoginModel){
        if(userLoginModel == null) return false;
        return setSharedPreferences(userLoginModel.getProfileModel());
    }

    public static boolean logout(){
        return updateAppUser(APP_USER_LOGIN_STATUS, "0");
    }

    public static boolean getState(){
        if(getAppUserLoginStatus().equals("1")) return true;
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


    public static String getUserPhone(){
        return getSharedPreferences().getString(APP_USER_PHONE, "");
    }

    public static String getAppUserLoginId() {
        return getSharedPreferences().getString(APP_USER_LOGIN_ID, "");
    }

    public static int getLoginId() {
        return Integer.parseInt(getSharedPreferences().getString(APP_USER_LOGIN_ID, ""));
    }

    public static void setAppUserLoginId(String appUserLoginId) {
        APP_USER_LOGIN_ID = appUserLoginId;
    }

    public static String getAppUserName() {
        return getSharedPreferences().getString(APP_USER_NAME, "");
    }

    public static void setAppUserName(String appUserName) {
        APP_USER_NAME = appUserName;
    }

    public static void setAppUserPhone(String appUserPhone) {
        APP_USER_PHONE = appUserPhone;
    }

    public static String getAppUserEmail() {
        return getSharedPreferences().getString(APP_USER_EMAIL, "");
    }

    public static void setAppUserEmail(String appUserEmail) {
        APP_USER_EMAIL = appUserEmail;
    }

    public static String getAppUserLocation() {
        return getSharedPreferences().getString(APP_USER_LOCATION, "");
    }

    public static void setAppUserLocation(String appUserLocation) {
        APP_USER_LOCATION = appUserLocation;
    }

    public static String getAppUserWebSite() {
        return getSharedPreferences().getString(APP_USER_WEB_SITE, "");
    }

    public static void setAppUserWebSite(String appUserWebSite) {
        APP_USER_WEB_SITE = appUserWebSite;
    }

    public static String getAppUserBio() {
        return getSharedPreferences().getString(APP_USER_BIO, "");
    }

    public static void setAppUserBio(String appUserBio) {
        APP_USER_BIO = appUserBio;
    }

    public static String getAppUserTimeZone() {
        return getSharedPreferences().getString(APP_USER_TIME_ZONE, "");
    }

    public static void setAppUserTimeZone(String appUserTimeZone) {
        APP_USER_TIME_ZONE = appUserTimeZone;
    }

    public static String getAppUserDesignation() {
        return getSharedPreferences().getString(APP_USER_DESIGNATION, "");
    }

    public static void setAppUserDesignation(String appUserDesignation) {
        APP_USER_DESIGNATION = appUserDesignation;
    }

    public static String getAppUserProfile() {
        return getSharedPreferences().getString(APP_USER_PROFILE, "");
    }

    public static void setAppUserProfile(String appUserProfile) {
        APP_USER_PROFILE = appUserProfile;
    }

    public static String getAppUserEnrollmentNumber() {
        return getSharedPreferences().getString(APP_USER_ENROLLMENT_NUMBER, "");
    }

    public static void setAppUserEnrollmentNumber(String appUserEnrollmentNumber) {
        APP_USER_ENROLLMENT_NUMBER = appUserEnrollmentNumber;
    }

    public static String getAppUserMemberShipNo() {
        return getSharedPreferences().getString(APP_USER_MEMBER_SHIP_NO, "");
    }

    public static void setAppUserMemberShipNo(String appUserMemberShipNo) {
        APP_USER_MEMBER_SHIP_NO = appUserMemberShipNo;
    }

    public static String getAppUserLandline() {
        return getSharedPreferences().getString(APP_USER_LANDLINE, "");
    }

    public static void setAppUserLandline(String appUserLandline) {
        APP_USER_LANDLINE = appUserLandline;
    }

    public static String getAppUserMobile() {
        return getSharedPreferences().getString(APP_USER_MOBILE, "");
    }

    public static void setAppUserMobile(String appUserMobile) {
        APP_USER_MOBILE = appUserMobile;
    }

    public static String getAppUserResidential() {
        return getSharedPreferences().getString(APP_USER_RESIDENTIAL, "");
    }

    public static void setAppUserResidential(String appUserResidential) {
        APP_USER_RESIDENTIAL = appUserResidential;
    }

    public static String getAppUserCourtAddress() {
        return getSharedPreferences().getString(APP_USER_COURT_ADDRESS, "");
    }

    public static void setAppUserCourtAddress(String appUserCourtAddress) {
        APP_USER_COURT_ADDRESS = appUserCourtAddress;
    }

    public static String getAppUserBloodGroup() {
        return getSharedPreferences().getString(APP_USER_BLOOD_GROUP, "");
    }

    public static void setAppUserBloodGroup(String appUserBloodGroup) {
        APP_USER_BLOOD_GROUP = appUserBloodGroup;
    }

    public static String getAppUserLat01() {
        return getSharedPreferences().getString(APP_USER_LAT_01, "");
    }

    public static void setAppUserLat01(String appUserLat01) {
        APP_USER_LAT_01 = appUserLat01;
    }

    public static String getAppUserLong01() {
        return getSharedPreferences().getString(APP_USER_LONG_01, "");
    }

    public static void setAppUserLong01(String appUserLong01) {
        APP_USER_LONG_01 = appUserLong01;
    }

    public static String getAppUserLat02() {
        return getSharedPreferences().getString(APP_USER_LAT_02, "");
    }

    public static void setAppUserLat02(String appUserLat02) {
        APP_USER_LAT_02 = appUserLat02;
    }

    public static String getAppUserLong02() {
        return getSharedPreferences().getString(APP_USER_LONG_02, "");
    }

    public static void setAppUserLong02(String appUserLong02) {
        APP_USER_LONG_02 = appUserLong02;
    }

    public static String getAppUserLoginStatus() {
        return getSharedPreferences().getString(APP_USER_LOGIN_STATUS, "0");
    }

    public static void setAppUserLoginStatus(String appUserLoginStatus) {
        APP_USER_LOGIN_STATUS = appUserLoginStatus;
    }

    public static String getAppUserProfilePic() {
        return getSharedPreferences().getString(APP_USER_PROFILE_PIC, "");
    }

    public static void setAppUserProfilePic(String appUserProfilePic) {
        APP_USER_PROFILE_PIC = appUserProfilePic;
    }

    static boolean setSharedPreferences(ProfileModel profileModel){
        if(!updateAppUser(APP_USER_LOGIN_ID, String.valueOf(profileModel.getUser_id()))) return false;
        if(!updateAppUser(APP_USER_NAME, profileModel.getName())) return false;
        if(!updateAppUser(APP_USER_EMAIL, profileModel.getEmail())) return false;
        if(!updateAppUser(APP_USER_PHONE, profileModel.getMobile())) return false;
        if(!updateAppUser(APP_USER_LOCATION, profileModel.getLocation())) return false;
        if(!updateAppUser(APP_USER_WEB_SITE, profileModel.getWebsite())) return false;
        if(!updateAppUser(APP_USER_BIO, profileModel.getBio())) return false;
        if(!updateAppUser(APP_USER_TIME_ZONE, profileModel.getTimezone())) return false;
        if(!updateAppUser(APP_USER_DESIGNATION, profileModel.getDesignation().getName())) return false;
        if(!updateAppUser(APP_USER_PROFILE, profileModel.getProfile())) return false;
        if(!updateAppUser(APP_USER_ENROLLMENT_NUMBER, profileModel.getEnrollment_number())) return false;
        if(!updateAppUser(APP_USER_MEMBER_SHIP_NO, profileModel.getMembership_number())) return false;
        if(!updateAppUser(APP_USER_LANDLINE, profileModel.getLandline())) return false;
        if(!updateAppUser(APP_USER_LANDLINE, profileModel.getLandline())) return false;
        if(!updateAppUser(APP_USER_MOBILE, profileModel.getMobile())) return false;
        if(!updateAppUser(APP_USER_RESIDENTIAL, profileModel.getResidential_address())) return false;
        if(!updateAppUser(APP_USER_COURT_ADDRESS, profileModel.getCourt_address())) return false;
        if(!updateAppUser(APP_USER_BLOOD_GROUP, profileModel.getBlood_group())) return false;
        if(!updateAppUser(APP_USER_LAT_01, profileModel.getLat1())) return false;
        if(!updateAppUser(APP_USER_LONG_01, profileModel.getLong1())) return false;
        if(!updateAppUser(APP_USER_LAT_02, profileModel.getLat2())) return false;
        if(!updateAppUser(APP_USER_LONG_02, profileModel.getLong2())) return false;
        if(!updateAppUser(APP_USER_LOGIN_STATUS, "1")) return false;
        if(!updateAppUser(APP_USER_PROFILE_PIC, profileModel.getProfile_pic())) return false;
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
