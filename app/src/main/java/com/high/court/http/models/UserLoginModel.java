package com.high.court.http.models;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.http_interface.HighCourtLoginInterface;
import com.high.court.http.models.http_request.UserLoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 4/25/2017.
 */

public class UserLoginModel extends HighCourtModel{

    @SerializedName("user")
    @Expose
    ProfileModel profileModel;

    public ProfileModel getProfileModel() {
        return profileModel;
    }

    public void setProfileModel(ProfileModel profileModel) {
        this.profileModel = profileModel;
    }

    public static void login(String enrollment_number, String password, final HighCourtLoginInterface highCourtLoginInterface){
        RestAdapter.get().userLogin(UserLoginRequest.makeLoginRequest(enrollment_number, password)).enqueue(new Callback<UserLoginModel>() {
            @Override
            public void onResponse(Call<UserLoginModel> call, Response<UserLoginModel> response) {
                highCourtLoginInterface.onLoginSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserLoginModel> call, Throwable t) {
                highCourtLoginInterface.onLoginError(t);
            }
        });
    }

}
