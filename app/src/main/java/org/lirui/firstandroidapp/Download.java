package org.lirui.firstandroidapp;

import android.os.SystemClock;

/**
 * Created by mischa on 2015/8/7.
 */
public class Download {

    /*private DownloadCallback callback;

    public void downloadFile(String url) {
//        SystemClock.sleep(7000);
//        return true;
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(7000);

                if (callback != null) {
                    callback.onDownloadComplete(true);
 //                   callback.onDownloadComplete(false);
                }
            }
        }.start();
    }

    public void setDownloadCallback(DownloadCallback callback) {
        this.callback = callback;
    }*/
    private DownloadCallback dc;

    public void call(String name){
        new Thread(){
            @Override
            public void run(){
                SystemClock.sleep(7000);
                if(dc!=null){
                    dc.call(true);
                }
            }
        }.start();
    }
    public void setCallfuc(DownloadCallback dc){
        this.dc = dc;
    }
}
