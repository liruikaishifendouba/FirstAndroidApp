package org.lirui.firstandroidapp;
import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
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

public class ServiceTestMain extends Activity {
    private Button sendButton;
    private Button onBind;
    private Button btn;
    // 定义Broadcast Receiver action
    private static final String MY_ACTION = "org.lirui.firstandroidapp.MY_ACTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicetest);
        sendButton = (Button) findViewById(R.id.sendButton);
        onBind = (Button)findViewById(R.id.onBind);
        sendButton.setOnClickListener(new sendButtonListener());
        onBind.setOnClickListener(new onBindListener());
        bindService(new Intent(ServiceTest.ACTION), conn, BIND_AUTO_CREATE);
        // 实例化Button
        btn = (Button)findViewById(R.id.Button1);
        // 添加事件监听器
        btn.setOnClickListener(new btnListener());

    }
    // 创建事件监听器
    class btnListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            // 实例化Intent
            Intent intent = new Intent();
            // 设置Intent action属性
            intent.setAction(MY_ACTION);
            // 发起广播
            sendBroadcast(intent);
        }
    }
    class sendButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            startService(new Intent(ServiceTest.ACTION));
        }

    }
    private class onBindListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
    }
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("onServiceConnected");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println( "onServiceDisconnected");
        }
    };
    @Override
    public  void onDestroy(){
        System.out.println("------执行了---------onDestroy");

        unbindService(conn);
        super.onDestroy();
    }

}