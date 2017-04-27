package com.high.court.http.models.http_interface;

import com.high.court.http.models.UserLoginModel;

/**
 * Created by Akshit on 27/04/2017.
 */

public interface HighCourtChangePasswordInterface {

    void onPasswordChangeSuccess(ChangePasswordModel changePasswordModel);

    void onPasswordChangeError(Throwable t);

    void onPasswordChangeFailure(ChangePasswordModel changePasswordModel);

}
