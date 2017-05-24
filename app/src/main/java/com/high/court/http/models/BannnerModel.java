package com.high.court.http.models;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.http_interface.BannerInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bhavan on 5/24/17.
 */

public class BannnerModel extends HighCourtModel {

    @SerializedName("list")
    @Expose
    List<Banner> banners;

    @SerializedName("banner_timing")
    @Expose
    int banner_timing = (1000 * 15);

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public int getBanner_timing() {
        return (1000 * banner_timing);
    }

    public void setBanner_timing(int banner_timing) {
        this.banner_timing = banner_timing;
    }

    public class Banner{

        @SerializedName("id")
        @Expose
        int id;

        @SerializedName("index")
        @Expose
        int index;

        @SerializedName("status")
        @Expose
        int status;

        @SerializedName("image_path")
        @Expose
        String image_path;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getImage_path() {
            return image_path;
        }

        public void setImage_path(String image_path) {
            this.image_path = image_path;
        }
    }

    public static void getBanner(final BannerInterface bannerInterface){
        RestAdapter.get().getBanner().enqueue(new Callback<BannnerModel>() {
            @Override
            public void onResponse(Call<BannnerModel> call, Response<BannnerModel> response) {
                if(response != null && response.body() != null){
                    bannerInterface.onSuccess(response.body());
                }else{
                    bannerInterface.onBannerError(new Throwable());
                }
            }

            @Override
            public void onFailure(Call<BannnerModel> call, Throwable t) {
                Log.d("Asd", "sds");
                bannerInterface.onBannerError(t);
            }
        });
    }
}
