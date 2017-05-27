package com.high.court.http.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.http_interface.AchievementInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bhavan on 5/24/17.
 */

public class AchievementModel extends HighCourtModel {

    @SerializedName("achievement")
    @Expose
    Achievement achievement;

    public Achievement getAchievement() {
        return achievement;
    }

    public void setAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public class Achievement{

        @SerializedName("id")
        @Expose
        int id;

        @SerializedName("title")
        @Expose
        String title;

        @SerializedName("description")
        @Expose
        String description;

        @SerializedName("achievement_year")
        @Expose
        int achievement_year;

        @SerializedName("image")
        @Expose
        String image;

        @SerializedName("destination")
        @Expose
        String destination;

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

        public int getAchievement_year() {
            return achievement_year;
        }

        public void setAchievement_year(int achievement_year) {
            this.achievement_year = achievement_year;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }
    }

    public static void getAchievement(final AchievementInterface achievementInterface){
        RestAdapter.get().getAchievement().enqueue(new Callback<AchievementModel>() {
            @Override
            public void onResponse(Call<AchievementModel> call, Response<AchievementModel> response) {
                if(response != null && response.body() != null){
                    achievementInterface.onAchievementSuccess(response.body());
                }else{
                    achievementInterface.onAchievementError(new Throwable());
                }
            }

            @Override
            public void onFailure(Call<AchievementModel> call, Throwable t) {
                achievementInterface.onAchievementError(t);
            }
        });
    }

}
