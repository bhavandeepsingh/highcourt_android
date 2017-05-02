package com.high.court.http.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.high.court.activities.ExicutiveMemberDetail;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.http_interface.BloodGroupInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Akshit on 30/04/2017.
 */

public class BloodGroupsModel extends HighCourtModel {

    @SerializedName("list")
    @Expose
    List<BloodGroup> bloodGroups;

    public List<BloodGroup> getBloodGroups() {
        return bloodGroups;
    }

    public void setBloodGroups(List<BloodGroup> bloodGroups) {
        this.bloodGroups = bloodGroups;
    }

    public static void getBloodGroupList(final BloodGroupInterface bloodGroupInterface) {
        RestAdapter.get().getBoodGroupList().enqueue(new Callback<BloodGroupsModel>() {
            @Override
            public void onResponse(Call<BloodGroupsModel> call, Response<BloodGroupsModel> response) {
                bloodGroupInterface.onBloodGroupSuccess(response.body());
            }

            @Override
            public void onFailure(Call<BloodGroupsModel> call, Throwable t) {
                bloodGroupInterface.onBloodGroupFailur(t);
            }
        });
    }

    public class BloodGroup{

        @SerializedName("id")
        @Expose
        int id;

        @SerializedName("name")
        @Expose
        String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
