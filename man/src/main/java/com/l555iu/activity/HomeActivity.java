package com.l555iu.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.l555iu.helper.NetworkStatusManager;
import com.l555iu.man.R;
import com.l555iu.service.LocalWordService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingxian.lzg on 14-3-4.
 */
public class HomeActivity extends ListActivity {

    private LocalWordService s;
    private ArrayAdapter<String> adapter;
    private List<String> wordList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        wordList = new ArrayList<String>();
        NetworkStatusManager.init(this);
        wordList.add("is wifi:" + NetworkStatusManager.getInstance().isWifi());
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,wordList);
        setListAdapter(adapter);
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 1000;i++){
                    System.out.println("wo da:"+i);
                }
            }
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this,LocalWordService.class);
        bindService(intent,mConnection,BIND_AUTO_CREATE);
        System.out.println(adapter.getItem(0));
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalWordService.MyBinder myBinder = (LocalWordService.MyBinder)service;
            s = myBinder.getService();
            Toast.makeText(HomeActivity.this, "Connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            s = null;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
    }

    public void onClick(View view) {

        if (s != null) {
            Toast.makeText(this, "Number of elements" + s.getWordList().size(),Toast.LENGTH_SHORT).show();
            wordList.clear();
//            wordList.addAll(s.getWordList());
            wordList.add("is wifi:" + NetworkStatusManager.getInstance().isWifi());
            adapter.notifyDataSetChanged();
        }
    }
}
