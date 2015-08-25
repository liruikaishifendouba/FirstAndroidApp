package org.lirui.firstandroidapp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import static android.content.Intent.getIntent;

public class TodoService extends Service {

    public static final String ACTTION = "org.lirui.firstandroidapp.TodoService";
    public static final String TodoServiceReceiver = "org.lirui.firstandroidapp.TodoService_ACTION";
    IntentFilter filter = new IntentFilter();
            filter.addAction(TodoServiceReceiver);
            registerReceiver(TodoServiceReceiver, filter);
    private Intent intent;
    boolean result = true;
    public String TodoReceiver = "org.lirui.firstandroidapp.TodoReceiver";
    public class TodoServiceReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            result = false;
        }


    }
    @Override
    public IBinder onBind(Intent intent) {
        Intent i = new Intent();
        i.setAction(TodoReceiver);
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
}
