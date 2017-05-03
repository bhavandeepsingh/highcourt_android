package com.high.court.http.models.http_interface;

import com.high.court.http.models.HolidaysModel;
import com.high.court.http.models.NotificationModel;

import butterknife.Optional;

/**
 * Created by admin on 5/3/2017.
 */

public interface HolidayInterface {

    void onHolidaysSuccess(HolidaysModel holidaysModel);

    void onHolidaysFailur();

    void onHolidaysFailur(Throwable t);


}
