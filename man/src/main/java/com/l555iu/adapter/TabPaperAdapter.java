package com.l555iu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.l555iu.fragment.TabFragment1;
import com.l555iu.fragment.TabFragment2;

/**
 * Created by jingxian.lzg on 14-3-25.
 */
public class TabPaperAdapter extends FragmentPagerAdapter {

	public TabPaperAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = null;
		switch (i) {
			case 0:
				fragment = new TabFragment1();
				break;
			case 1:
				fragment = new TabFragment2();
				break;
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 2;
	}
}
