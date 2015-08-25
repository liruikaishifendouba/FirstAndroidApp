package org.lirui.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static org.lirui.firstandroidapp.R.id.result;


public class resultactivity extends Activity {
    private TextView resultview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultactivity);
        resultview = (TextView)findViewById(R.id.result);
        Intent intent = getIntent();
        String factorOneStr = intent.getStringExtra("one");
        String factorTwoStr = intent.getStringExtra("two");
        int factorOneInt = Integer.parseInt(factorOneStr);
        int factorTwoInt = Integer.parseInt(factorTwoStr);
        int result = factorOneInt * factorTwoInt;
        resultview.setText(result + "");
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", result);
        setResult(RESULT_OK,resultIntent);
        finish();


    }


}