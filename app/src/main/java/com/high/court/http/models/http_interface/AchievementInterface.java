package com.high.court.http.models.http_interface;

import com.high.court.http.models.AchievementModel;

/**
 * Created by bhavan on 5/24/17.
 */

public interface AchievementInterface {

    void onAchievementSuccess(AchievementModel achievementModel);

    void onAchievementError(Throwable t);
}
