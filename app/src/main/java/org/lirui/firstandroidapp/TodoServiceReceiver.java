//package org.lirui.firstandroidapp;
//
//
//
//import android.app.Activity;
//import android.app.Notification;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.support.v4.widget.TextViewCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.Html;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.webkit.ValueCallback;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.security.PrivateKey;
//public class TodoService_ACTION extends BroadcastReceiver {
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        Intent i = new Intent();
//        i.setClass(context,TodoService.class);
//        i.putExtra("key1", "1");
//        context.startService(new Intent(TodoService.ACTION));
//
//    }
//
//
//}