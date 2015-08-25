package org.lirui.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class otheractivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otheractivity);
        Intent intent = getIntent();
        String value = intent.getStringExtra("testIntent");
        TextView myTestView = (TextView) findViewById(R.id.myTestView);

        myTestView.setText(value);

    }

}