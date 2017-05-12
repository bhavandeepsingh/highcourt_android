package com.high.court.http.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.high.court.helpers.DateHelper;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.http_interface.HolidayInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 5/3/2017.
 */

public class HolidaysModel extends HighCourtModel {

    @SerializedName("list")
    @Expose
    List<Holidays> holidaysList;

    public List<Holidays> getHolidaysList() {
        return holidaysList;
    }

    public void setHolidaysList(List<Holidays> holidaysList) {
        this.holidaysList = holidaysList;
    }

    public class Holidays{

        @SerializedName("id")
        @Expose
        int id;

        @SerializedName("title")
        @Expose
        String title;

        @SerializedName("description")
        @Expose
        String description;

        @SerializedName("date")
        @Expose
        String date;

        @SerializedName("status")
        @Expose
        int status;

        @SerializedName("highcourts")
        @Expose
        List<Highcourts> highcourtses;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<Highcourts> getHighcourtses() {
            return highcourtses;
        }

        public void setHighcourtses(List<Highcourts> highcourtses) {
            this.highcourtses = highcourtses;
        }

        public String getDisplayHighcourtName(){
            String name = "";
            if(getHighcourtses() != null && getHighcourtses().size() > 0){
                for(int i = 0; i < getHighcourtses().size(); i++){
                    name += ((name != null && name.length() > 0)?", ":"") + getHighcourtses().get(i).getName();
                }
            }
            return name;
        }

        public String getFormattedDate(){
            return DateHelper.getHolidayDate(getDate());
        }

        public class Highcourts{

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


    public static void getHolidays(final HolidayInterface holidayInterface, Map<String, String> params){
        if(params == null) params = new HashMap<>();

        RestAdapter.get().getHolidays(params).enqueue(new Callback<HolidaysModel>() {

            @Override
            public void onResponse(Call<HolidaysModel> call, Response<HolidaysModel> response) {
                if(response.body() != null){
                    holidayInterface.onHolidaysSuccess(response.body());
                }else{
                    holidayInterface.onHolidaysFailur();
                }
            }

            @Override
            public void onFailure(Call<HolidaysModel> call, Throwable t) {
                holidayInterface.onHolidaysFailur(t);
            }

        });

    }

}
