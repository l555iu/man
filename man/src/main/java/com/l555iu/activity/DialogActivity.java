package com.l555iu.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.l555iu.fragment.CustomDialog;
import com.l555iu.man.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingxian.lzg on 2014/3/28.
 */
public class DialogActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_btn_activity);
		Button button = (Button) findViewById(R.id.btn);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomDialog customDialog = new CustomDialog(DialogActivity.this);
				customDialog.show();
			}
		});
	}
}
