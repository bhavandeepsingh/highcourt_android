package com.high.court.http;

import com.high.court.http.models.BloodGroupsModel;
import com.high.court.http.models.NotificationModel;
import com.high.court.http.models.UserLoginModel;
import com.high.court.http.models.http_interface.ChangePasswordModel;
import com.high.court.http.models.http_request.ExcecutiveMemberModel;

import com.high.court.http.models.http_request.ResetMyPassword;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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

    @GET("user/executive")
    Call<ExcecutiveMemberModel> getExcutiveMembers();

    @FormUrlEncoded
    @POST("user/members")
    Call<ExcecutiveMemberModel> getMembers(@FieldMap Map<String, String> request, @Query("page") int page_no);


    @GET("user/blood-group-list")
    Call<BloodGroupsModel> getBoodGroupList();


    @GET("notification/list")
    Call<NotificationModel> getNotificationList();

    @Multipart
    @POST("profile/update")
    Call<UserLoginModel> profileUpdate(@PartMap Map<String, RequestBody> stringStringMap, @Part MultipartBody.Part part);


}