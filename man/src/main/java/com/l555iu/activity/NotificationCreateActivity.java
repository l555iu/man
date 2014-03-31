package com.l555iu.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import com.l555iu.man.R;

/**
 * Created by jingxian.lzg on 14-3-6.
 */
public class NotificationCreateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_create);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onClick(View view){
        Intent intent = new Intent(this,NotificationResultActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new Notification.Builder(this)
                            .setContentTitle("a notify from jingxian.lzg")
                            .setContentText("hello world,to go on!")
                            .setContentIntent(pendingIntent)
                            .setSmallIcon(R.drawable.ic_launcher)
                            .setAutoCancel(true)
                            .addAction(R.drawable.images,"see",pendingIntent)
                            .addAction(R.drawable.images,"null",pendingIntent)
                            .addAction(R.drawable.images,"cancel",pendingIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,notification);
    }
}
