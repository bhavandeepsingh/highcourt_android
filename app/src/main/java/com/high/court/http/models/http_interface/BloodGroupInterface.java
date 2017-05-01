package com.high.court.http.models.http_interface;

import com.high.court.http.models.BloodGroupsModel;

/**
 * Created by Akshit on 30/04/2017.
 */

public interface BloodGroupInterface {

    void onBloodGroupSuccess(BloodGroupsModel bloodGroupsModel);

    void onBloodGroupFailur(Throwable t);
}
