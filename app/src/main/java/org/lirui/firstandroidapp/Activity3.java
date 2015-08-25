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

public class Activity3 extends Activity{
    private TextView myTestview;
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        myTestview = (TextView)findViewById(R.id.myTestView);
        System.out.print("Activity1------------->onCreate()");
    }
    protected void onStart() {
        System.out.print("Activity1------------->onStart()");
        super.onStart();}
    protected void onResume() {
        System.out.print("Activity1------------->onResume()");
        super.onResume();}
    protected void onRestart() {
        System.out.print("Activity1------------->onRestart()");
        super.onRestart();}
    protected void onDestroy() {
        System.out.print("Activity1------------->onDestroy()");
        super.onDestroy();}
    protected void onPause() {
        System.out.print("Activity1------------->onPause()");
        super.onPause();}
    protected void onStop() {
        System.out.print("Activity1------------->onStop()");
        super.onStop();}
}
