package com.high.court.http.models.http_interface;

import com.high.court.http.models.ProfileModel;
import com.high.court.http.models.http_request.ExcecutiveMemberModel;

import java.util.List;

/**
 * Created by admin on 4/27/2017.
 */

public interface ExceutiveMemberInterface {

    void onListMembers(List<ProfileModel> profileModels);
    void onListMemberFailur(ExcecutiveMemberModel excecutiveMemberModel);
    void onListMemberFailur(Throwable t);
}
