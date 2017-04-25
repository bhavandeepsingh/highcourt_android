package com.high.court.http.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    String designation;

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

    @SerializedName("blood_group")
    @Expose
    String blood_group;

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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getEnrollment_number() {
        return enrollment_number;
    }

    public void setEnrollment_number(String enrollment_number) {
        this.enrollment_number = enrollment_number;
    }

    public String getMembership_number() {
        return membership_number;
    }

    public void setMembership_number(String membership_number) {
        this.membership_number = membership_number;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getResidential_address() {
        return residential_address;
    }

    public void setResidential_address(String residential_address) {
        this.residential_address = residential_address;
    }

    public String getCourt_address() {
        return court_address;
    }

    public void setCourt_address(String court_address) {
        this.court_address = court_address;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getLat1() {
        return lat1;
    }

    public void setLat1(String lat1) {
        this.lat1 = lat1;
    }

    public String getLong1() {
        return long1;
    }

    public void setLong1(String long1) {
        this.long1 = long1;
    }

    public String getLat2() {
        return lat2;
    }

    public void setLat2(String lat2) {
        this.lat2 = lat2;
    }

    public String getLong2() {
        return long2;
    }

    public void setLong2(String long2) {
        this.long2 = long2;
    }
}
