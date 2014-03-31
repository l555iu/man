package com.l555iu.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.l555iu.service.LocalWordService;

import java.util.Calendar;

/**
 * Created by jingxian.lzg on 14-3-4.
 */
public class MyScheduleReceiver extends BroadcastReceiver {

    private static final long REPEAT_TIME = 1000 * 30;

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("MyScheduleReceiver onReceive start=================");
//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        Intent i = new Intent(context,LocalWordService.class);
//        PendingIntent pendingIntent = PendingIntent.getService(context,0,i,PendingIntent.FLAG_CANCEL_CURRENT);
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.SECOND,30);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),REPEAT_TIME,pendingIntent);
        System.out.println("MyScheduleReceiver onReceive end=================");
    }
}
