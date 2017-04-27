package com.high.court.http.models.http_interface;

import com.high.court.http.models.UserLoginModel;
import com.high.court.http.models.http_request.ResetMyPassword;

/**
 * Created by admin on 4/26/2017.
 */

public interface ResetMyPasswordInterface {

    void onResetPasswordSuccess(ResetMyPassword resetMyPassword);

    void onResetPasswordError(Throwable t);

    void onResetPasswordFailure(ResetMyPassword resetMyPassword);

}
