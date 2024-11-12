package com.example.inschool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyServiceLog","OnStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("MyServiceLog","OnBind");
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public String getCurrentTime(){
        SimpleDateFormat sdg=new SimpleDateFormat("yyyyMMdd-hhmmss");
        return  sdg.format(new Date());
    }
    class MyBinder extends Binder{
        public MyService getMyService(){
            return MyService.this;
        }
    }

}