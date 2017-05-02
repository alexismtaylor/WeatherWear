package edu.fsu.cs.mobile.weatherwear;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.app.Notification;


public class AlarmReceiver extends BroadcastReceiver {

    //when an alarm was received, push this notification
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, WeatherActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context).setSmallIcon(
        R.drawable.logo).setContentIntent(contentIntent).setContentTitle("Wake up!").setContentText("View the weather!").setPriority
                (Notification.PRIORITY_MAX);
        mBuilder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });

        NotificationManager mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(0, mBuilder.build());

    }
}
