package com.high.court.http.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.Streams;

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

    @SerializedName("error")
    @Expose
    public String error;

    public boolean is_error() {
        return is_error;
    }

    public void setIs_error(boolean is_error) {
        this.is_error = is_error;
    }

    public void setIs_success(boolean is_success) {
        this.is_success = is_success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
