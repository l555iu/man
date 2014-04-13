package com.l555iu.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.l555iu.man.R;

/**
 * Created by jingxian.lzg on 2014/3/31.
 */
public class CustomDialog {

	private Context context;
	private LayoutInflater inflater;

	public CustomDialog(Context context){
		this.context = context;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void show(){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		View viwe = inflater.inflate(R.layout.comment_dialog_menu,null);
		builder.setView(viwe);
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
		alertDialog.dismiss();
	}
}
