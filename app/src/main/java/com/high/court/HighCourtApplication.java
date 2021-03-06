package com.high.court;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.high.court.activities.HighCourtActivity;
import com.high.court.helpers.UILApplication;
import com.high.court.http.models.AchievementModel;
import com.high.court.http.models.BloodGroupsModel;
import com.high.court.http.models.CaseLawModel;
import com.high.court.http.models.HolidaysModel;
import com.high.court.http.models.JudgesModel;
import com.high.court.http.models.NotificationModel;
import com.high.court.http.models.PaymentsModel;
import com.high.court.http.models.ProfileModel;
import com.high.court.http.models.RosterModel;

import java.util.List;

/**
 * Created by gurpreetsingh on 24/04/17.
 */

public class HighCourtApplication extends UILApplication {

    static HighCourtApplication highCourtApplication;

    static List<ProfileModel> profileModels;
    static List<NotificationModel.Notifications> notificationsList;
    static BloodGroupsModel bloodGroupsModel;
    static HolidaysModel holidaysModel;
    static JudgesModel judgesModel;
    static CaseLawModel caseLawModel;
    static RosterModel rosterModel;
    static PaymentsModel paymentsModel;
    static AchievementModel achievementModel;

    static boolean paymentRecreateStatus = false;

    @Override
    public void onCreate() {
        super.onCreate();
        adjustFontScale(getApplicationContext(),getApplicationContext().getResources().getConfiguration());
        highCourtApplication = this;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // In some cases modifying newConfig leads to unexpected behavior,
        // so it's better to edit new instance.
        Configuration configuration = new Configuration(newConfig);
        adjustFontScale(getApplicationContext(), configuration);
    }

    public static void adjustFontScale(Context context, Configuration configuration) {
        if (configuration.fontScale != 1) {
            configuration.fontScale = 1;
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            context.getResources().updateConfiguration(configuration, metrics);
        }
    }

    public static HighCourtApplication getHighCourtApplication() {
        return highCourtApplication;
    }

    public void setHighCourtApplication(HighCourtApplication highCourtApplication) {
        this.highCourtApplication = highCourtApplication;
    }

    public static Context getHighCourtApplicationContext(){ return getHighCourtApplication().getApplicationContext(); }

    public static HighCourtActivity getHighCourtActivity(Context context){
        return (HighCourtActivity) context;
    }

    public static List<ProfileModel> getProfileModels() {
        return profileModels;
    }

    public static void setProfileModels(List<ProfileModel> profileModels) {
        HighCourtApplication.profileModels = profileModels;
    }

    public static void setNotifcationList(List<NotificationModel.Notifications> notificationses) {
        HighCourtApplication.notificationsList = notificationses;
    }

    public static List<NotificationModel.Notifications> getNotificationsList() {
        return notificationsList;
    }

    public static BloodGroupsModel getBloodGroupsModel() {
        return bloodGroupsModel;
    }

    public static void setBloodGroupsModel(BloodGroupsModel bloodGroupsModel) {
        HighCourtApplication.bloodGroupsModel = bloodGroupsModel;
    }

    public static void setNotificationsList(List<NotificationModel.Notifications> notificationsList) {
        HighCourtApplication.notificationsList = notificationsList;
    }

    public static HolidaysModel getHolidaysModel() {
        return holidaysModel;
    }

    public static void setHolidaysModel(HolidaysModel holidaysModel) {
        HighCourtApplication.holidaysModel = holidaysModel;
    }

    public static JudgesModel getJudgesModel() {
        return judgesModel;
    }

    public static void setJudgesModel(JudgesModel judgesModel) {
        HighCourtApplication.judgesModel = judgesModel;
    }

    public static CaseLawModel getCaseLawModel() {
        return caseLawModel;
    }

    public static void setCaseLawModel(CaseLawModel caseLawModel) {
        HighCourtApplication.caseLawModel = caseLawModel;
    }

    public static RosterModel getRosterModel() {
        return rosterModel;
    }

    public static void setRosterModel(RosterModel rosterModel) {
        HighCourtApplication.rosterModel = rosterModel;
    }

    public static PaymentsModel getPaymentsModel() {
        return paymentsModel;
    }

    public static void setPaymentsModel(PaymentsModel paymentsModel) {
        HighCourtApplication.paymentsModel = paymentsModel;
    }

    public static boolean isPaymentRecreateStatus() {
        return paymentRecreateStatus;
    }

    public static void setPaymentRecreateStatus(boolean paymentRecreateSattus) {
        HighCourtApplication.paymentRecreateStatus = paymentRecreateSattus;
    }

    public static AchievementModel getAchievementModel() {
        return achievementModel;
    }

    public static void setAchievementModel(AchievementModel achievementModel) {
        HighCourtApplication.achievementModel = achievementModel;
    }
}

