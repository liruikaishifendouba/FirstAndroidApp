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
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarTest extends Activity {
    private ProgressBar firstBar;
    private ProgressBar secondBar;
    private Button myButton;
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        firstBar = (ProgressBar)findViewById(R.id.firstBar);
        secondBar = (ProgressBar)findViewById(R.id.secondBar);
        myButton =(Button)findViewById(R.id.myButton);
        myButton.setOnClickListener(new ButtonListener());
    }
    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(i == 0){
                firstBar.setVisibility(View.VISIBLE);
                secondBar.setVisibility(View.VISIBLE);
            }
            if(i<100){
                firstBar.getProgress();
                firstBar.setSecondaryProgress(i+10);
                secondBar.setProgress(i);
            }
            else {
                firstBar.setVisibility(View.GONE);
                secondBar.setVisibility(View.GONE);
            }
            i=i+10;
        }
    }
}