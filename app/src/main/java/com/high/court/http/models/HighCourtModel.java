package com.high.court.http.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 4/25/2017.
 */

public class HighCourtModel {

    @SerializedName("is_error")
    @Expose
    public boolean is_error;


    @SerializedName("is_success")
    @Expose
    public boolean is_success;

    public boolean is_success() {
        return is_success;
    }

}
