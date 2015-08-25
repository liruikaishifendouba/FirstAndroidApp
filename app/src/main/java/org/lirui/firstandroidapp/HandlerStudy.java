package org.lirui.firstandroidapp;
import android.app.Activity;
import android.os.Handler;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.sip.SipAudioCall;
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

public class HandlerStudy extends Activity {
    private Button startButton;
    // private Button exitButton;
    private Handler handler = new Handler();
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler);
        startButton = (Button) findViewById(R.id.startButton);
        //  exitButton = (Button) findViewById(R.id.exitButton);
        startButton.setText("开始");
        //  exitButton.setText("退出");
        bar = (ProgressBar) findViewById(R.id.bar);
        startButton.setOnClickListener(new StartButtonListener());
        //   exitButton.setOnClickListener(new ExitButtonListener());

    }

    class StartButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //    handler.post(updateThread);
            bar.setVisibility(View.VISIBLE);
            handler.post(updateThread);
        }
    }

    /*
        class ExitButtonListener implements View.OnClickListener {

            @Override
            public void onClick(View v) {
                handler.removeCallbacks(updateThread);
            }

        }
        */
    Handler updateBarHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            bar.setProgress(msg.arg1);
            updateBarHandler.post(updateThread);
        }

    };
        Runnable updateThread = new Runnable() {
            int i = 0;

            @Override
            public void run() {
                System.out.println("启动线程UpdateThread");
                //handler.postDelayed(updateThread,3000);
                i = i + 10;
                Message msg = updateBarHandler.obtainMessage();
                msg.arg1 = i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateBarHandler.sendMessage(msg);
                if (i == 100) {
                    updateBarHandler.removeCallbacks(updateThread);
                }

            }
        };



}






