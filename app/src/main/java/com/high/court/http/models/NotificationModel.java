package com.high.court.http.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.http_interface.NotificationInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 4/29/2017.
 */

public class NotificationModel extends HighCourtModel {

    public static int NOTIFAICATION_HAS_ATTACHMENT = 1;

    @SerializedName("list")
    @Expose
    List<Notifications> notificationses;

    public List<Notifications> getNotificationses() {
        return notificationses;
    }

    public void setNotificationses(List<Notifications> notificationses) {
        this.notificationses = notificationses;
    }

    public static void getNotificationList(final NotificationInterface notificantionInterface) {
        RestAdapter.get().getNotificationList().enqueue(new Callback<NotificationModel>() {
            @Override
            public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {
                notificantionInterface.onNotificationSuccess(response.body());
            }

            @Override
            public void onFailure(Call<NotificationModel> call, Throwable t) {
                notificantionInterface.onNotificationFailur(t);
            }
        });
    }

    public class Notifications {
        @SerializedName("id")
        @Expose
        int id;

        @SerializedName("title")
        @Expose
        String title;

        @SerializedName("description")
        @Expose
        String description;

        @SerializedName("sender_id")
        @Expose
        int sender_id;

        @SerializedName("reciever_id")
        @Expose
        int reciever_id;

        @SerializedName("is_file")
        @Expose
        int is_file;

        @SerializedName("created_at")
        @Expose
        String created_at;

        @SerializedName("updated_at")
        @Expose
        String updated_at;

        @SerializedName("notification_src")
        @Expose
        String notification_src;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getSender_id() {
            return sender_id;
        }

        public void setSender_id(int sender_id) {
            this.sender_id = sender_id;
        }

        public int getReciever_id() {
            return reciever_id;
        }

        public void setReciever_id(int reciever_id) {
            this.reciever_id = reciever_id;
        }

        public int getIs_file() {
            return is_file;
        }

        public void setIs_file(int is_file) {
            this.is_file = is_file;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getNotification_src() {
            return notification_src;
        }

        public void setNotification_src(String notification_src) {
            this.notification_src = notification_src;
        }


    }

}
