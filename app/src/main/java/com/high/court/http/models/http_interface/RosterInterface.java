package com.high.court.http.models.http_interface;

import com.high.court.http.models.RosterModel;

/**
 * Created by admin on 5/5/2017.
 */

public interface RosterInterface {

    void onRosterSuccess(RosterModel rosterModel);

    void onRosterFailur(Throwable t);

    void onRosterFailur();

}
