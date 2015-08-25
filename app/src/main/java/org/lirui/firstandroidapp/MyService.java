package org.lirui.firstandroidapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 24/08/2015.
 */
public class MyService extends Service {


    @Nullable
    boolean result = true;
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }



}
