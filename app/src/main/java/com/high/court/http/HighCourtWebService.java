package com.high.court.http;

import com.high.court.http.models.UserLoginModel;
import com.high.court.http.models.http_interface.ChangePasswordModel;
import com.high.court.http.models.http_request.ResetMyPassword;
import com.high.court.http.models.http_request.UserLoginRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


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

}