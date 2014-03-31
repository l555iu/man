package com.l555iu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.l555iu.man.R;

/**
 * Created by jingxian.lzg on 2014/3/28.
 */
public class PopMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pop_menu_activity);

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new String[]{"a","b","c","d"});
		ListView listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(arrayAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				CommentPopMenu commentPopMenu = new CommentPopMenu(PopMenuActivity.this);
				commentPopMenu.addMenu(1, "回复","回复成功");
				commentPopMenu.addMenu(2, "删除","删除成功");
				commentPopMenu.addMenu(3, "复制","复制成功");
				commentPopMenu.show(view);
				commentPopMenu.setOnMenuItemClickListener(new CommentPopMenu.OnMenuItemClickListener() {
					@Override
					public void onMenuItemClick(CommentPopMenu.MenuItem menuItem) {
						Toast.makeText(PopMenuActivity.this,(String)menuItem.getThroughParam(),Toast.LENGTH_LONG).show();
					}
				});
			}
		});
	}
}
