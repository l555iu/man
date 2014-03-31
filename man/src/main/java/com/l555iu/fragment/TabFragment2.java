package com.l555iu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.l555iu.man.R;

/**
 * Created by jingxian.lzg on 14-3-25.
 */
public class TabFragment2 extends BaseTabFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.tab_fragment_2,null);
	}
}
