package com.l555iu.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by jingxian.lzg on 14-3-5.
 */
public class DownloadService extends IntentService {

    private int result = Activity.RESULT_CANCELED;
    public static final String URL = "urlpath";
    public static final String FILENAME = "filename";
    public static final String FILEPATH = "filepath";
    public static final String RESULT = "result";
    public static final String NOTIFICATION = "com.l555iu.receiver.downloadReceiver";

    public DownloadService(){
        super("download service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String urlPath = intent.getStringExtra(URL);
        String fileName = intent.getStringExtra(FILENAME);
        File outputFile = new File(fileName);
        if (outputFile.exists()){
            outputFile.delete();
        }
        InputStream is = null;
        FileOutputStream fos = null;
        try{
            java.net.URL url = new URL(urlPath);
            is = url.openConnection().getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            fos = new FileOutputStream(outputFile);
            int next = -1;
            while ((next = isr.read()) != -1){
                fos.write(next);
            }
            result = Activity.RESULT_OK;
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if (is != null){
                try{
                    is.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (fos != null){
                try{
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        publishResults(outputFile.getAbsolutePath(),result);
    }

    private void publishResults(String outputPath, int result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(FILEPATH, outputPath);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}
