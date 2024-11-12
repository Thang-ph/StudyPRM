package com.example.inschool;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ServiceDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_service_demo);
        Intent i=new Intent(this,MyService.class);
        bindService(i,connection,BIND_AUTO_CREATE);
    }
    MyService myService;
    ServiceConnection connection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myService=((MyService.MyBinder)iBinder).getMyService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    public void onStartService(View view){
        String time=myService.getCurrentTime();
        (TextView)findViewById(R.id.tvService).setText(time);
        Intent i =new Intent(this,MyService.class);
        startService(i); // khởi động 1 background service
//        startForegroundService(i); // khởi động 1 foreground
    }
}