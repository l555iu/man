package com.l555iu.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.l555iu.service.LocalWordService;

/**
 * Created by jingxian.lzg on 14-3-4.
 */
public class MyStartServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("MyStartServiceReceiver onReceive start=================");
        Intent gotoService = new Intent(context, LocalWordService.class);
        context.startService(gotoService);
        System.out.println("MyStartServiceReceiver onReceive end=================");
    }
}
