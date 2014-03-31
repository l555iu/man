package com.l555iu.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.l555iu.man.R;
import com.l555iu.service.DownloadService;

/**
 * Created by jingxian.lzg on 14-3-5.
 */
public class DownloadActivity extends Activity {

    private TextView textView;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                String filePath = bundle.getString(DownloadService.FILEPATH);
                int result = bundle.getInt(DownloadService.RESULT);
                if (result == RESULT_OK){
                    Toast.makeText(DownloadActivity.this,"download success,filePath: " + filePath,Toast.LENGTH_SHORT).show();
                    textView.setText("download success.");
                }else{
                    Toast.makeText(DownloadActivity.this,"download fail,errorCode: " + result,Toast.LENGTH_SHORT).show();
                    textView.setText("download fail.");
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver,new IntentFilter(DownloadService.NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);
        textView = (TextView) findViewById(R.id.status);
    }

    public void onClick(View view){
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra(DownloadService.URL,"http://www.baidu.com/index.html");
        intent.putExtra(DownloadService.FILENAME,"baiduindex.html");
        startService(intent);
        textView.setText("Service started.");
    }
}
