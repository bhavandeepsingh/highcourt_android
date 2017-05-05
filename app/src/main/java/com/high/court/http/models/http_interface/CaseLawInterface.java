package com.high.court.http.models.http_interface;

import com.high.court.http.models.CaseLawModel;

/**
 * Created by admin on 5/5/2017.
 */

public interface CaseLawInterface {

    void onCaseLawSuccess(CaseLawModel caseLawModel);

    void onCaseLawFailur(Throwable t);


}
