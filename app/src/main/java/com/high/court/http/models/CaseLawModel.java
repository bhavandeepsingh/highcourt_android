package com.high.court.http.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.http_interface.CaseLawInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 5/4/2017.
 */

public class CaseLawModel extends HighCourtModel {

    @SerializedName("list")
    @Expose
    List<CaseLaw> caseLawList;

    public List<CaseLaw> getCaseLawList() {
        return caseLawList;
    }

    public void setCaseLawList(List<CaseLaw> caseLawList) {
        this.caseLawList = caseLawList;
    }

    public class CaseLaw{

        @SerializedName("id")
        @Expose
        int id;

        @SerializedName("title")
        @Expose
        String title;

        @SerializedName("discription")
        @Expose
        String discription;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDiscription() {
            return discription;
        }

        public void setDiscription(String discription) {
            this.discription = discription;
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

    public static void getCaseLaw(final CaseLawInterface caseLawInterface, int page_no){
        RestAdapter.get().getCaseLaw(page_no).enqueue(new Callback<CaseLawModel>() {
            @Override
            public void onResponse(Call<CaseLawModel> call, Response<CaseLawModel> response) {
                if(response.body() != null) {
                    caseLawInterface.onCaseLawSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<CaseLawModel> call, Throwable t) {
                caseLawInterface.onCaseLawFailur(t);
            }
        });
    }

}
