package com.l555iu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import com.l555iu.adapter.TabPaperAdapter;
import com.l555iu.fragment.BaseTabFragment;
import com.l555iu.man.R;

/**
 * Created by jingxian.lzg on 14-3-25.
 */
public class TabHostActivity extends FragmentActivity {

	private ViewPager mViewPager;

	private TabHost mTabHost;

	private LayoutInflater mInflater;

	private FragmentPagerAdapter mAdapter;

	private TabWidget mTabWidget;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost_activity);
		mInflater = this.getLayoutInflater();
		initTabHost();
		initViewPager();
		bindListeners();
	}

	/**
	 * 初始化TAB容器
	 */
	private void initTabHost() {
		mTabHost = (TabHost) findViewById(R.id.tabhost);
		mTabHost.setup();
		mTabHost.addTab(mTabHost.newTabSpec("one").setIndicator(createTabView("one", "oneTitle")).setContent(android.R.id.tabcontent));
		mTabHost.addTab(mTabHost.newTabSpec("two").setIndicator(createTabView("two", "twoTitle")).setContent(android.R.id.tabcontent));
		adjustTabWidget();

		mTabWidget = mTabHost.getTabWidget();
		// 初始化TAB组件，将第一个元素样式改变为当前选中
		changeTabStyle(mTabWidget.getChildAt(0), true);
	}

	/**
	 * 创建TAB元素
	 */
	private View createTabView(String tag, String text) {
		View view = mInflater.inflate(R.layout.favorite_tab, null);
		view.setTag(tag);
		TextView tv = (TextView) view.findViewById(R.id.tab_base_info);
		tv.setText(text);
		return view;
	}

	/**
	 * 调整TAB控件
	 */
	private void adjustTabWidget() {
		TabWidget tabWidget = mTabHost.getTabWidget();
		if (tabWidget == null) {
			return;
		}
		View childView = tabWidget.getChildAt(0);
		if (childView == null) {
			return;
		}
		ViewGroup.LayoutParams lp = childView.getLayoutParams();
		if (lp != null) {
			lp.height = (int)(40 * getResources().getDisplayMetrics().density);
		}
	}

	/**
	 * 初始化ViewPager容器
	 */
	private void initViewPager() {
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mAdapter = new TabPaperAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mViewPager.setOffscreenPageLimit(2);
	}

	/**
	 * 绑定监听器
	 */
	private void bindListeners() {
		mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				changeTab(tabId);
				int currentId = mTabHost.getCurrentTab();
				mViewPager.setCurrentItem(currentId);
			}
		});
		mTabHost.setCurrentTab(0);
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int i, float v, int i2) {

			}

			@Override
			public void onPageSelected(int i) {
				mTabHost.setCurrentTab(i);
				BaseTabFragment tabFragment = (BaseTabFragment) mAdapter.instantiateItem(mViewPager, i);
				// 可用进行检查看是否需要进行初始化工作，公有接口可以定义在base里
			}

			@Override
			public void onPageScrollStateChanged(int i) {

			}
		});
	}

	/**
	 * 改变某个TAB头的样式
	 *
	 * @param view TAB头元素
	 *
	 * @param isCurrent 是否为当前TAB
	 */
	private void changeTabStyle(View view, boolean isCurrent) {
		TextView baseInfo = (TextView) view.findViewById(R.id.tab_base_info);
		// 选中时的样式
		TextView bottomLine = (TextView) view.findViewById(R.id.tab_bottom_line);
		// 未选中时的样式
		TextView normalBottomLine = (TextView) view.findViewById(R.id.tab_bottom_line_normal);
		if (isCurrent) {
			bottomLine.setVisibility(View.VISIBLE);
			normalBottomLine.setVisibility(View.GONE);
			baseInfo.setTextColor(this.getResources().getColor(R.color.total_tab_current_font));
		} else {
			bottomLine.setVisibility(View.GONE);
			normalBottomLine.setVisibility(View.VISIBLE);
			baseInfo.setTextColor(this.getResources().getColor(R.color.total_tab_font));
		}
	}

	/**
	 * 根据TAB标签来改变TAB样式
	 *
	 * @param tabId
	 */
	private void changeTab(String tabId) {
		for (int i = 0; i < mTabWidget.getChildCount(); i++) {
			View tab = mTabWidget.getChildAt(i);
			// 如果目前遍历的TAB标签等于当前使用的TAB标签，则更改显示样式
			if (tabId.equals(tab.getTag())) {
				changeTabStyle(tab, true);
			} else {
				changeTabStyle(tab, false);
			}
		}
	}
}
