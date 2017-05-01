package com.high.court.http.models.http_request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.HighCourtModel;
import com.high.court.http.models.ProfileModel;
import com.high.court.http.models.http_interface.ExceutiveMemberInterface;
import com.high.court.http.models.http_interface.MemberInterface;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 4/27/2017.
 */

public class ExcecutiveMemberModel extends HighCourtModel {

    @SerializedName("list")
    @Expose
    List<ProfileModel> profileModels;

    public List<ProfileModel> getProfileModels() {
        return profileModels;
    }

    public void setProfileModels(List<ProfileModel> profileModels) {
        this.profileModels = profileModels;
    }

    public static void getExecutiveMembers(final ExceutiveMemberInterface exceutiveMemberInterface){
        RestAdapter.get().getExcutiveMembers().enqueue(new Callback<ExcecutiveMemberModel>() {
            @Override
            public void onResponse(Call<ExcecutiveMemberModel> call, Response<ExcecutiveMemberModel> response) {
                if(response.body() != null) {
                    exceutiveMemberInterface.onListMembers(response.body().getProfileModels());
                }else{
                    exceutiveMemberInterface.onListMemberFailur(response.body());
                }
            }
            @Override
            public void onFailure(Call<ExcecutiveMemberModel> call, Throwable t) {
                exceutiveMemberInterface.onListMemberFailur(t);
            }
        });
    }


    public static void getMembersList(final MemberInterface memberInterface, Map<String, RequestBody> stringRequestBodyMap, int page_no, final boolean is_search){

        RestAdapter.get().getMembers(stringRequestBodyMap, page_no).enqueue(new Callback<ExcecutiveMemberModel>() {
            @Override
            public void onResponse(Call<ExcecutiveMemberModel> call, Response<ExcecutiveMemberModel> response) {
                if(response.body() != null) {

                    if(is_search) memberInterface.onProfileMemberSearch(response.body());
                    else{
                        memberInterface.onProfileMembers(response.body());
                    }
                }else{
                    memberInterface.onProfileMemberFailur(response.body());
                }
            }
            @Override
            public void onFailure(Call<ExcecutiveMemberModel> call, Throwable t) {
                memberInterface.onProfileMemberFailur(t);
            }
        });
    }

}
