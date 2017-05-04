package com.high.court.http.models;

import com.google.android.gms.common.stats.StatsEvent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.http_interface.JudgesModelInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Akshit on 04/05/2017.
 */

public class JudgesModel extends HighCourtModel {

    @SerializedName("list")
    @Expose
    List<Judge> judgeList;

    public List<Judge> getJudgeList() {
        return judgeList;
    }

    public void setJudgeList(List<Judge> judgeList) {
        this.judgeList = judgeList;
    }

    public class Judge{

        @SerializedName("id")
        @Expose
        int id;

        @SerializedName("name")
        @Expose
        String name;

        @SerializedName("address")
        @Expose
        String address;

        @SerializedName("landline")
        @Expose
        String landline;

        @SerializedName("dob")
        @Expose
        String dob;

        @SerializedName("ext_no")
        @Expose
        String ext_no;

        @SerializedName("court_room")
        @Expose
        String court_room;

        @SerializedName("date_of_appointment")
        @Expose
        String date_of_appointment;

        @SerializedName("date_of_retirement")
        @Expose
        String date_of_retirement;

        @SerializedName("bio_graphy")
        @Expose
        String bio_graphy;

        @SerializedName("created_at")
        @Expose
        String created_at;

        @SerializedName("updated_at")
        @Expose
        String updated_at;

        @SerializedName("image_src")
        @Expose
        String image_src;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLandline() {
            return landline;
        }

        public void setLandline(String landline) {
            this.landline = landline;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getExt_no() {
            return ext_no;
        }

        public void setExt_no(String ext_no) {
            this.ext_no = ext_no;
        }

        public String getCourt_room() {
            return court_room;
        }

        public void setCourt_room(String court_room) {
            this.court_room = court_room;
        }

        public String getDate_of_appointment() {
            return date_of_appointment;
        }

        public void setDate_of_appointment(String date_of_appointment) {
            this.date_of_appointment = date_of_appointment;
        }

        public String getDate_of_retirement() {
            return date_of_retirement;
        }

        public void setDate_of_retirement(String date_of_retirement) {
            this.date_of_retirement = date_of_retirement;
        }

        public String getBio_graphy() {
            return bio_graphy;
        }

        public void setBio_graphy(String bio_graphy) {
            this.bio_graphy = bio_graphy;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getImage_src() {
            return image_src;
        }

        public void setImage_src(String image_src) {
            this.image_src = image_src;
        }
    }

    public static void getJudges(final JudgesModelInterface judgesModelInterface, Map<String, RequestBody> stringRequestBodyMap, int page_no, final boolean is_search){
        RestAdapter.get().getJudges().enqueue(new Callback<JudgesModel>() {
            @Override
            public void onResponse(Call<JudgesModel> call, Response<JudgesModel> response) {
                if(response.body() != null){
                    if(is_search) judgesModelInterface.onJudgesSearch(response.body());
                    else judgesModelInterface.onJudgesSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<JudgesModel> call, Throwable t) {
                judgesModelInterface.onJudgesFailur(t);
            }
        });
    }
}
