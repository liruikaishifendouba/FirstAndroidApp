package org.lirui.firstandroidapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServiceTest extends Service {

    public static final String ACTION = "org.lirui.firstandroidapp.ServiceTest";
    private Intent intent;

    @Override
    public IBinder onBind(Intent intent) {
        this.intent = intent;
        final String str1 = intent.getStringExtra("key");
        new Thread() {
            // Runnable runnable = new Runnable(){
            @Override
            public void run(){
                while (true) {
                    System.out.println("------执行了---------onCreate");
                    try
                    {
                        Thread.sleep(1000);
                    }catch(InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    if(str1!=null) {
                        if (str1.equals("stop")) {
                            break;
                        }
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
        return super.onStartCommand(intent,flags,startId);
    }
}
