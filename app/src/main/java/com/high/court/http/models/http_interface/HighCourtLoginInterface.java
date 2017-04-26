package com.high.court.http.models.http_interface;

import com.high.court.http.models.UserLoginModel;

/**
 * Created by admin on 4/25/2017.
 */

public interface HighCourtLoginInterface {

    void onLoginSuccess(UserLoginModel userLoginModel);

    void onLoginError(Throwable t);

    void onLoginFailure(UserLoginModel userLoginModel);

}
