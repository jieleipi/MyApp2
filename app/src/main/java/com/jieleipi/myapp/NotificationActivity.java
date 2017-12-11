package com.jieleipi.myapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.NotificationCompat;

public class NotificationActivity extends AppCompatActivity implements  View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        Button sendNotice=(Button)findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.send_notice:
                NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification notification=new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")
                        .setContentText("Thsi is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.logo)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.logo))
                        .build();
                manager.notify(1,notification);
                break;
            default:
                break;
        }
    }
}
