package com.high.court.http.models.http_interface;

import com.high.court.http.models.JudgesModel;

import java.util.List;

/**
 * Created by Akshit on 04/05/2017.
 */

public interface JudgesModelInterface {

    void onJudgesSuccess(JudgesModel judgesModel);

    void onJudgesFailur(Throwable t);

    void onJudgesSearch(JudgesModel judgesModel);

}
