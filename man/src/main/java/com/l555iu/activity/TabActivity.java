package com.l555iu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import com.l555iu.man.R;
import com.squareup.picasso.Picasso;

/**
 * Created by jingxian.lzg on 14-3-19.
 */
public class TabActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity);
        imageView = (ImageView) findViewById(R.id.tab_image);
        Picasso.with(this).setDebugging(true);
        Picasso.with(this).load("http://img01.taobaocdn.com/bao/uploaded/i1/T1gZ5JFp4eXXb1upjX.jpg_180x180xz.jpg")
                .placeholder(R.drawable.images)
                .error(R.drawable.ic_launcher)
                .into(imageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
