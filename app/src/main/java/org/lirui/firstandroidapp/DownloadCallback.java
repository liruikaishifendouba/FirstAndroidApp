package org.lirui.firstandroidapp;

/**
 * Created by mischa on 2015/8/7.
 */

public interface DownloadCallback {
    //void onDownloadComplete(boolean isSuccess);
    void call(boolean result);
}
