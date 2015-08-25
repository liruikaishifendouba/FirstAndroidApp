package org.lirui.firstandroidapp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;

import static android.content.Intent.getIntent;

public class TodoService extends Service {

    public static final String ACTTION = "org.lirui.firstandroidapp.TodoService";

    public MyServiceReceiver myServiceReceiver;
    private Intent intent;
    boolean result = true;
    public class MyServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            result = false;
        }


    }
    @Override
    public IBinder onBind(Intent intent) {


        Intent i = new Intent();
        i.setAction(Todo.TODO_ACTION);
        sendBroadcast(i);



        new Thread() {
            @Override
            public void run(){
                while (result) {

                    System.out.println("------执行了---------onBind");
                    try
                    {
                        Thread.sleep(1000);
                    }catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        myServiceReceiver = new MyServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Todo.TodoService_ACTION);
        registerReceiver(myServiceReceiver, filter);
    }



    @Override
    public void onStart(Intent intent,int startId){
        System.out.println("------执行了---------onStart");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        System.out.println("------执行了---------onStartCommand");
       return 2;
       // return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        if(myServiceReceiver!=null) {
            unregisterReceiver(myServiceReceiver);
        }

        super.onDestroy();
    }
}
