package com.example.lokhandmanav.remind;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/**
 * Created by LokhandManav on 05-11-2017.
 */

public class NotificationPush {

Uri uri;
    public NotificationPush(String activity, int id)
    {
         uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (Build.VERSION.SDK_INT < 26) {
            pushNotification(activity,id );
        }
        else
        {
            pushNotificationChannel(activity, id);
        }
    }
    @TargetApi(12)
    public void pushNotification(String activity, int id)
    {
        NotificationManager manager = (NotificationManager) START.appContext.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(START.appContext)
                        .setSound(uri)
                        .setAutoCancel(true)
                        .setColor(START.appContext.getResources().getColor(R.color.colorPrimary))
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("You have an Activity to do")
                        .setContentText(activity);

                manager.notify(id, builder.build());

    }

    @TargetApi(26)
      public void pushNotificationChannel(String activity , int id)
      {
        NotificationManager notificationManager = (NotificationManager) START.appContext.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel channel = new NotificationChannel(Integer.toString(id), "Channel name", NotificationManager.IMPORTANCE_DEFAULT);
          channel.setDescription("Reminder");
          channel.setLightColor(Color.CYAN);
          channel.canShowBadge();
          channel.setShowBadge(true);
          notificationManager.createNotificationChannel(channel);

          Notification n= new Notification.Builder(START.appContext ,Integer.toString(id))
                  .setContentTitle("You have an activity to do")
                  .setContentText(activity)
                  .setBadgeIconType(R.mipmap.ic_launcher)
                  .setNumber(5)
                  .setSmallIcon(R.mipmap.ic_launcher_round)
                  .setAutoCancel(true)
                  .build();
        }

}
