package org.lirui.firstandroidapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends Activity {
    private  Button myButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        myButton =(Button)findViewById(R.id.myButton);

        myButton.setText("打开activity3" );
        myButton.setOnClickListener(new myButtonListener());
    }
    protected void onStart() {
        System.out.print("Activity2------------->onStart()");
        super.onStart();}
    protected void onResume() {
        System.out.print("Activity2------------->onResume()");
        super.onResume();}
    protected void onRestart() {
        System.out.print("Activity2------------->onRestart()");
        super.onRestart();}
    protected void onDestroy() {
        System.out.print("Activity2------------->onDestroy()");
        super.onDestroy();}
    protected void onPause() {
        System.out.print("Activity2------------->onPause()");
        super.onPause();}
    protected void onStop() {
        System.out.print("Activity2------------->onStop()");
        super.onStop();}
    class myButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent first = new Intent();

            first.setClass(Activity2.this,Activity3.class);
            Activity2.this.startActivity(first);
        }
    }


}
