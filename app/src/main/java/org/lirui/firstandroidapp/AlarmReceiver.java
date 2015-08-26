package org.lirui.firstandroidapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    //    TodoEditActivity a = new TodoEditActivity();
private Notification notification;
    // 声明NotificationManager
    private NotificationManager mNotification;
    // Notification标示ID
    private static final int ID = 1;
    @Override
    public void onReceive(Context context, Intent intent) {


            mNotification = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

            notification = new Notification();
            int icon = notification.icon = android.R.drawable.stat_notify_chat;
            String tickerText = "Test Notification";
            long when = System.currentTimeMillis();
            notification.icon = icon;
            notification.tickerText = tickerText;
            notification.when = when;

            // 实例化Intent
            Intent i = new Intent();


            // 获得PendingIntent
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
            // 设置事件信息
            notification.setLatestEventInfo(context, "提醒", "时间到了", pi);
            // 发出通知
            mNotification.notify(ID, notification);


    }


}