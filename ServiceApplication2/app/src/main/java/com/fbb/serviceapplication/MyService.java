package com.fbb.serviceapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private DownloadBinder mBinder =new DownloadBinder();
    private int step;
    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        step = intent.getIntExtra("step",1);
        return mBinder;
    }

    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("MyService:","startDownload executed");

        }
        public int getProgress(){
            Log.d("MyService","getProgress excuted"+step);
            int result = step*10;
            return result;
        }
    }
}