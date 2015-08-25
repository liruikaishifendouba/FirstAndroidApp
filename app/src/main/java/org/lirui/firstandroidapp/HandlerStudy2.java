package org.lirui.firstandroidapp;
import android.app.Activity;
import android.os.Handler;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.sip.SipAudioCall;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ValueCallback;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.security.PrivateKey;
import java.util.logging.LogRecord;

public class HandlerStudy2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler);
        System.out.println("Activity------->" + Thread.currentThread().getId());
        System.out.println("Activity------->" + Thread.currentThread().getName());
        HandlerThread handlerThread = new HandlerThread("handler_thread");
        handlerThread.start();
        MyHandler myHandler = new MyHandler(handlerThread.getLooper());
        Message msg = myHandler.obtainMessage();
        msg.sendToTarget();
    }

    class MyHandler extends Handler{
        public MyHandler() {
        }
        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
            public void handleMessage(Message msg){
                System.out.println("Handler------->" + Thread.currentThread().getId());
                System.out.println("Handler------->" + Thread.currentThread().getName());
            }
    }
}
