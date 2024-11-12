package com.example.inschool;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

public class NotificationDemo extends AppCompatActivity {
    public final String CHANNEL_ID="first_chanel";
    NotificationChannel c= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification_demo);
        Bundle b= RemoteInput.getResultsFromIntent(getIntent());
        if(b!=null){
            String data=b.getString("my_data");
        }
     findViewById(R.id.btn_Noti).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             pushNotification();

         }
     });

    }
    private void pushNotification(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            Intent i=new Intent(this, NotificationDemo.class);
            PendingIntent pi=PendingIntent.getActivity(this,100,i,PendingIntent.FLAG_MUTABLE);
            RemoteInput rm=new RemoteInput.Builder("my_data").setLabel("Input your data").build();
            NotificationCompat.Action action=new NotificationCompat.Action
                    .Builder(android.R.drawable.ic_dialog_alert,"Reply",pi)
                    .addRemoteInput(rm)
                    .build();
            c = new NotificationChannel(CHANNEL_ID,"First channel", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager mng=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mng.createNotificationChannel(c);
            Notification n=new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_alert)
                    .setContentTitle("Thong bao")
                    .setContentText("Wowy toi de gap anh Minh")
                    .setContentIntent(pi)
                         .build();
            mng.notify(1,n);
        }
    }
}