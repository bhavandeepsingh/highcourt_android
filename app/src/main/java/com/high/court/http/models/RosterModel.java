package com.high.court.http.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.http_interface.RosterInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 5/5/2017.
 */

public class RosterModel extends HighCourtModel {

    @SerializedName("list")
    @Expose
    List<Roster> rosterList;

    public List<Roster> getRosterList() {
        return rosterList;
    }

    public void setRosterList(List<Roster> rosterList) {
        this.rosterList = rosterList;
    }

    public class Roster {

        @SerializedName("title")
        @Expose
        String title;


        @SerializedName("description")
        @Expose
        String description;

        @SerializedName("bench_id")
        @Expose
        int bench_id;

        @SerializedName("type")
        @Expose
        int type;

        @SerializedName("date")
        @Expose
        String date;

        @SerializedName("created_at")
        @Expose
        String created_at;

        @SerializedName("updated_at")
        @Expose
        String updated_at;

        @SerializedName("bench")
        @Expose
        RosterBench rosterBench;

        @SerializedName("judge")
        @Expose
        List<JudgesModel.Judge> judgeList;

        public String getBenchNames() {
            if(getRosterBench() != null){
                return getRosterBench().getName();
            }
            return null;
        }

        public String getJugesName() {
            String judges_name = "";
            if(getJudgeList() != null && getJudgeList().size() > 0){
                for(int i =0; i < getJudgeList().size(); i++){
                    judges_name += ((judges_name.length() > 0)? "\n": "") + getJudgeList().get(i).getName();
                }
            }
            return judges_name;
        }

        public class RosterBench{

            @SerializedName("id")
            @Expose
            int id;

            @SerializedName("name")
            @Expose
            String name;

            @SerializedName("type")
            @Expose
            String type;

            @SerializedName("created_at")
            @Expose
            String created_at;

            @SerializedName("updated_at")
            @Expose
            String updated_at;

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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getBench_id() {
            return bench_id;
        }

        public int getType() {
            return type;
        }

        public String getDate() {
            return date;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public RosterBench getRosterBench() {
            return rosterBench;
        }

        public List<JudgesModel.Judge> getJudgeList() {
            return judgeList;
        }

        public void setJudgeList(List<JudgesModel.Judge> judgeList) {
            this.judgeList = judgeList;
        }
    }

    public static void getRoster(final RosterInterface rosterInterface, Map<String, String> stringStringMap, int page_no){
        if(stringStringMap == null) stringStringMap = new HashMap<>();
        RestAdapter.get().getRoster(stringStringMap, page_no).enqueue(new Callback<RosterModel>() {
            @Override
            public void onResponse(Call<RosterModel> call, Response<RosterModel> response) {
                if(response.body() != null){
                    rosterInterface.onRosterSuccess(response.body());
                }else{
                    rosterInterface.onRosterFailur();
                }
            }

            @Override
            public void onFailure(Call<RosterModel> call, Throwable t) {
                rosterInterface.onRosterFailur(t);
            }
        });
    }


}
