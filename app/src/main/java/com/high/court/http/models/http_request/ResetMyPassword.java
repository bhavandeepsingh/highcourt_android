package com.high.court.http.models.http_request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.HighCourtModel;
import com.high.court.http.models.UserLoginModel;
import com.high.court.http.models.http_interface.ResetMyPasswordInterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 4/26/2017.
 */

public class ResetMyPassword extends HighCourtModel {

    @SerializedName("requestSend")
    @Expose
    String requestSend;

    @SerializedName("license_no")
    @Expose
    String license_no;

    public String getRequestSend() {
        return requestSend;
    }

    public void setRequestSend(String requestSend) {
        this.requestSend = requestSend;
    }

    public String getLicense_no() {
        return license_no;
    }

    public void setLicense_no(String license_no) {
        this.license_no = license_no;
    }

    public static void resetPassword(String licence_no, final ResetMyPasswordInterface resetMyPasswordInterface){

        RestAdapter.get().resetMyPassword(makeCall(licence_no)).enqueue(new Callback<ResetMyPassword>() {
            @Override
            public void onResponse(Call<ResetMyPassword> call, Response<ResetMyPassword> response) {
                resetMyPasswordInterface.onResetPasswordSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResetMyPassword> call, Throwable t) {
                resetMyPasswordInterface.onResetPasswordError(t);
            }
        });

    }

    private static Map<String, String> makeCall(String licence_no) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("RequestResetPassword[license_no]", licence_no);
        return stringStringHashMap;
    }

}
