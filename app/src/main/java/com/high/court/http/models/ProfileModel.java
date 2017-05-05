package com.high.court.http.models;

import android.view.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.high.court.helpers.UserHelper;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.http_interface.ProfileUpdateInterface;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

/**
 * Created by admin on 4/25/2017.
 */
public class ProfileModel extends HighCourtModel {

    @SerializedName("user_id")
    @Expose
    int user_id;

    @SerializedName("public_email")
    @Expose
    String email;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("location")
    @Expose
    String location;

    @SerializedName("website")
    @Expose
    String website;

    @SerializedName("bio")
    @Expose
    String bio;

    @SerializedName("timezone")
    @Expose
    String timezone;

    @SerializedName("designation")
    @Expose
    DesignationModel designation;

    @SerializedName("profile")
    @Expose
    String profile;

    @SerializedName("enrollment_number")
    @Expose
    String enrollment_number;

    @SerializedName("membership_number")
    @Expose
    String membership_number;

    @SerializedName("landline")
    @Expose
    String landline;

    @SerializedName("mobile")
    @Expose
    String mobile;

    @SerializedName("residential_address")
    @Expose
    String residential_address;

    @SerializedName("court_address")
    @Expose
    String court_address;


    @SerializedName("bloodGroup")
    @Expose
    BloodGroupsModel.BloodGroup blood_group_model;

    String blood_group;
    int blood_group_id;

    @SerializedName("lat1")
    @Expose
    String lat1;

    @SerializedName("long1")
    @Expose
    String long1;

    @SerializedName("lat2")
    @Expose
    String lat2;

    @SerializedName("long2")
    @Expose
    String long2;

    @SerializedName("profilePic")
    @Expose
    String profile_pic;

    String designation_name;

    public int getUser_id() {
        return user_id;
    }

    public ProfileModel setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ProfileModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProfileModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public ProfileModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public ProfileModel setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public DesignationModel getDesignation() {
        return designation;
    }

    public ProfileModel setDesignation(DesignationModel designation) {
        this.designation = designation;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public ProfileModel setProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public String getEnrollment_number() {
        return enrollment_number;
    }

    public ProfileModel setEnrollment_number(String enrollment_number) {
        this.enrollment_number = enrollment_number;
        return this;
    }

    public String getMembership_number() {
        return membership_number;
    }

    public ProfileModel setMembership_number(String membership_number) {
        this.membership_number = membership_number;
        return this;
    }

    public String getLandline() {
        return landline;
    }

    public ProfileModel setLandline(String landline) {
        this.landline = landline;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public ProfileModel setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getResidential_address() {
        return residential_address;
    }

    public ProfileModel setResidential_address(String residential_address) {
        this.residential_address = residential_address;
        return this;
    }

    public String getCourt_address() {
        return court_address;
    }

    public ProfileModel setCourt_address(String court_address) {
        this.court_address = court_address;
        return this;
    }

    public String getBlood_group() {
        if(getBlood_group_model() != null){ setBlood_group(getBlood_group_model().getName());}
        return blood_group;
    }

    public ProfileModel setBlood_group(String blood_group) {
        this.blood_group = blood_group;
        return this;
    }

    public String getLat1() {
        return lat1;
    }

    public ProfileModel setLat1(String lat1) {
        this.lat1 = lat1;
        return this;
    }

    public String getLong1() {
        return long1;
    }

    public ProfileModel setLong1(String long1) {
        this.long1 = long1;
        return this;
    }

    public String getLat2() {
        return lat2;
    }

    public ProfileModel setLat2(String lat2) {
        this.lat2 = lat2;
        return this;
    }

    public String getLong2() {
        return long2;
    }

    public ProfileModel setLong2(String long2) {
        this.long2 = long2;
        return this;
    }

    public BloodGroupsModel.BloodGroup getBlood_group_model() {
        return blood_group_model;
    }

    public void setBlood_group_model(BloodGroupsModel.BloodGroup blood_group_model) {
        this.blood_group_model = blood_group_model;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public ProfileModel setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
        return this;
    }

    public String getDesignation_name() {
        if(designation_name == null && getDesignation() != null) return getDesignation().getName();
        else return designation_name;
    }

    public ProfileModel setDesignation_name(String designation_name) {
        this.designation_name = designation_name;
        return this;
    }

    public int getBlood_group_id() {
        return blood_group_id;
    }

    public ProfileModel setBlood_group_id(int blood_group_id) {
        this.blood_group_id = blood_group_id;
        return this;
    }

    public static ProfileModel getLoginUserProfile() {
        return new ProfileModel()
        .setUser_id(UserHelper.getLoginId())
        .setName(UserHelper.getName())
        .setBio(UserHelper.getAppUserBio())
        .setCourt_address(UserHelper.getAppUserCourtAddress())
        .setDesignation_name(UserHelper.getAppUserDesignation())
        .setEmail(UserHelper.getEmail())
        .setProfile_pic(UserHelper.getAppUserProfilePic())
        .setResidential_address(UserHelper.getAppUserResidential())
        .setDesignation_name(UserHelper.getAppUserDesignation())
        .setBlood_group(UserHelper.getAppUserBloodGroup())
        .setEnrollment_number(UserHelper.getAppUserEnrollmentNumber())
        .setLandline(UserHelper.getAppUserLandline())
        .setMembership_number(UserHelper.getAppUserMemberShipNo())
        .setMobile(UserHelper.getAppUserMobile())
        .setProfile(UserHelper.getAppUserProfile())
        .setLat1(UserHelper.getAppUserLat01())
        .setLat2(UserHelper.getAppUserLat02())
        .setLong1(UserHelper.getAppUserLong01())
        .setLong2(UserHelper.getAppUserLong02())
        .setLocation(UserHelper.getAppUserLocation())
        .setBlood_group(UserHelper.getAppUserBloodGroup())
        .setBlood_group_id(Integer.parseInt(UserHelper.getAppUserBloodGroupId()));
    }

    public static void profileUpdate(Map<String, RequestBody> stringStringMap, MultipartBody.Part part, final ProfileUpdateInterface profileUpdateInterface){
        RestAdapter.get().profileUpdate(stringStringMap, part).enqueue(new Callback<UserLoginModel>() {
            @Override
            public void onResponse(Call<UserLoginModel> call, Response<UserLoginModel> response) {
                if(response.body() != null && response.body().is_success()){
                    profileUpdateInterface.onProfileSuccess(response.body());
                }else{
                    profileUpdateInterface.onProfileFailure(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserLoginModel> call, Throwable t) {
                profileUpdateInterface.onProfileError(t);
            }
        });
    }

}
