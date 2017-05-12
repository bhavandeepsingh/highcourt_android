package com.high.court.http.models.http_interface;

import com.high.court.http.models.PaymentsModel;

/**
 * Created by bhavan on 5/12/17.
 */

public interface PaymentsInterface {

    void onPaymentSuccess(PaymentsModel paymentsModel);

    void onPaymentFailur(Throwable t);

}
