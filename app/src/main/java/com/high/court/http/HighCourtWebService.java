package com.high.court.http;

import com.high.court.http.models.AchievementModel;
import com.high.court.http.models.BannnerModel;
import com.high.court.http.models.BloodGroupsModel;
import com.high.court.http.models.CaseLawModel;
import com.high.court.http.models.HolidaysModel;
import com.high.court.http.models.JudgesModel;
import com.high.court.http.models.NotificationModel;
import com.high.court.http.models.PaymentsModel;
import com.high.court.http.models.RosterModel;
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

    @FormUrlEncoded
    @POST("holidays/list")
    Call<HolidaysModel> getHolidays(@FieldMap Map<String, String> stringStringMap);

    @FormUrlEncoded
    @POST("judges/list")
    Call<JudgesModel> getJudges(@FieldMap Map<String, String> stringRequestBodyMap, @Query("page") int page_no);

    @GET("case-law/list")
    Call<CaseLawModel> getCaseLaw(@Query("page") int page_no);

    @FormUrlEncoded
    @POST("roster/list")
    Call<RosterModel> getRoster(@FieldMap Map<String, String> stringStringMap, @Query("page") int page_no);

    @FormUrlEncoded
    @POST("notification/read")
    Call<NotificationModel> notificationUnRead(@FieldMap Map<String, Integer> stringStringMap);

    @GET("notification/un-read-count")
    Call<NotificationModel> notificationUnReadCount();

    @FormUrlEncoded
    @POST("case-law/un-read-count")
    Call<CaseLawModel> caseLawuUnead(@FieldMap Map<String, Integer> stringStringMap);

    @GET("payment-log/list")
    Call<PaymentsModel> getPayment();

    @GET("bannner/list-all")
    Call<BannnerModel> getBanner();

    @GET("achievements/list")
    Call<AchievementModel> getAchievement();

}