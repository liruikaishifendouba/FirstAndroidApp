package org.lirui.firstandroidapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView myTestView = (TextView)findViewById(R.id.myTestView);
        Button myButton =(Button)findViewById(R.id.myButton);
        myTestView.setText("我的第一个TestView");
        //myButton.setText("我的第一个Button" );
        myButton.setText("去第二个activity" );
        myButton.setOnClickListener(new myButtonOnclick());

    }
    class myButtonOnclick implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,Mainactivity2.class);
            startActivity(intent);
        }
    }


}






















