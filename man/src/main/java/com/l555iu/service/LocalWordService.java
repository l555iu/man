package com.l555iu.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by jingxian.lzg on 14-3-4.
 */
public class LocalWordService extends Service {

    private final IBinder mBinder = new MyBinder();
    private List<String> list = new ArrayList<String>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("LocalWordService onStartCommand start=================");
        Random random = new Random();
        if (random.nextBoolean()) {
            list.add("Linux");
        }
        if (random.nextBoolean()) {
            list.add("Android");
        }
        if (random.nextBoolean()) {
            list.add("iPhone");
        }
        if (random.nextBoolean()) {
            list.add("Windows7");
        }
        if (list.size() >= 20) {
            list.remove(0);
        }
        Intent intent1 = new Intent();
        System.out.println("LocalWordService onStartCommand end=================");
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder{

        public LocalWordService getService(){
            return LocalWordService.this;
        }
    }

    public List<String> getWordList() {
        return list;
    }
}
