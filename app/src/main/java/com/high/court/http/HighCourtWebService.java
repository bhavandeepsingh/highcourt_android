package com.high.court.http;

import com.high.court.http.models.UserLoginModel;
import com.high.court.http.models.http_interface.ChangePasswordModel;
import com.high.court.http.models.http_request.ExcecutiveMemberModel;

import com.high.court.http.models.http_request.ResetMyPassword;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by bhavan on 12/26/16.
 */

public interface HighCourtWebService {

    @FormUrlEncoded
    @POST("user/login")
    Call<UserLoginModel> userLogin(@FieldMap Map<String, String> maps);

    @FormUrlEncoded
    @POST("user/request-reset-password")
    Call<ResetMyPassword> resetMyPassword(@FieldMap Map<String, String> maps);

    @FormUrlEncoded
    @POST("user/reset-password")
    Call<ChangePasswordModel> changePassword(@FieldMap Map<String, String> maps);

    @GET("user/executive")
    Call<ExcecutiveMemberModel> getExcutiveMembers();


}