package com.high.court.http.models.http_interface;

import com.high.court.http.models.ProfileModel;
import com.high.court.http.models.http_request.ExcecutiveMemberModel;

import java.util.List;

/**
 * Created by admin on 5/1/2017.
 */

public interface MemberInterface {

    void onProfileMembers(ExcecutiveMemberModel excecutiveMemberModel);

    void onProfileMemberSearch(ExcecutiveMemberModel excecutiveMemberModel);

    void onProfileMemberFailur(ExcecutiveMemberModel excecutiveMemberModel);

    void onProfileMemberFailur(Throwable t);

}
