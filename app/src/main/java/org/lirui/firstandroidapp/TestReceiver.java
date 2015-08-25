package org.lirui.firstandroidapp;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.Button;
import android.widget.TextView;

import java.security.PrivateKey;
public class TestReceiver extends BroadcastReceiver{
    public TestReceiver(){

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setClass(context, SecondActivity.class);
        // 启动Activity显示通知
        context.startActivity(i);
    }


}
