package org.lirui.firstandroidapp;
import android.app.Activity;
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

public class Mainactivity2 extends Activity {
    private Button myButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myButton = (Button) findViewById(R.id.myButton);
       // myButton.setText(R.string.other);
        myButton.setText("回到第一个activity");
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //intent.putExtra("testIntent","236");
                intent.setClass(Mainactivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



}













