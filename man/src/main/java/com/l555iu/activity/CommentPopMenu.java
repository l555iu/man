package com.l555iu.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.l555iu.man.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingxian.lzg on 2014/3/28.
 */
public class CommentPopMenu {

	private Context context;

	private LinearLayout mView;

	private WindowManager windowManager;
	private PopupWindow popupWindow;
	private LayoutInflater inflater;
	private float mScale;
	private List<MenuItem> menuItemList;
	private OnMenuItemClickListener mListener;

	public CommentPopMenu(Context context) {
		this.context = context;
		popupWindow = new PopupWindow(context);
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		mScale = displayMetrics.scaledDensity;
		popupWindow.setTouchInterceptor(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popupWindow.dismiss();
					return true;
				}
				return false;
			}
		});
	}

	private void init() {
		mView = (LinearLayout) inflater.inflate(R.layout.pop_menu, null);
		if (menuItemList != null && menuItemList.size() > 0) {
			for (final MenuItem item : menuItemList) {
				String itemTitle = item.getTitle();
				if (itemTitle != null & itemTitle.trim().length() > 0) {
					TextView menuItemView = new TextView(context);
					menuItemView.setText(itemTitle);
					LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (70 * mScale), (int) (44 * mScale));
					menuItemView.setLayoutParams(layoutParams);
					menuItemView.setGravity(Gravity.CENTER);
					menuItemView.setTextColor(context.getResources().getColor(R.color.comment_pop_menu_text_color));
					menuItemView.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							if (mListener != null) {
								mListener.onMenuItemClick(item);
							}
							popupWindow.dismiss();
						}
					});
					mView.addView(menuItemView);
					LinearLayout lineSplit = new LinearLayout(context);
					LinearLayout.LayoutParams lineLayoutParams = new LinearLayout.LayoutParams(1, (int) (44 * mScale));
					lineSplit.setLayoutParams(lineLayoutParams);
					lineSplit.setBackgroundColor(Color.WHITE);
					mView.addView(lineSplit);
				}
			}
			// 后面都会跟一个取消的menu
			TextView cancelMenuItemView = new TextView(context);
			cancelMenuItemView.setText("取消");
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (44 * mScale), (int) (44 * mScale));
			cancelMenuItemView.setLayoutParams(layoutParams);
			cancelMenuItemView.setGravity(Gravity.CENTER);
			cancelMenuItemView.setTextColor(context.getResources().getColor(R.color.comment_pop_menu_text_color));
			cancelMenuItemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					popupWindow.dismiss();
				}
			});
			mView.addView(cancelMenuItemView);
		} else {
			throw new IllegalStateException("PopupMenu#add was not called with a menu item to display.");
		}
		popupWindow.setContentView(mView);
		popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
		popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
		popupWindow.setTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
//		popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
	}

	/**
	 * 添加一个菜单项
	 *
	 * @param itemId 菜单项唯一标识id
	 * @param title  菜单标题
	 */
	public void addMenu(int itemId, String title) {
		addMenu(itemId, title, null);
	}

	/**
	 * 添加一个菜单项
	 *
	 * @param itemId       菜单项唯一标识id
	 * @param title        菜单标题
	 * @param throughParam 透传参数
	 */
	public void addMenu(int itemId, String title, Object throughParam) {
		if (menuItemList == null) {
			menuItemList = new ArrayList<MenuItem>();
		}
		menuItemList.add(new MenuItem(itemId, title, throughParam));
	}

	public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
		mListener = listener;
	}

	/**
	 * Show popup menu.
	 */
	public void show() {
		show(null);
	}

	/**
	 * Show popup menu.
	 *
	 * @param anchor Anchor view for this popup. The popup will appear below the anchor if there
	 *               is room, or above it if there is not.
	 */
	public void show(View anchor) {
		init();
		if (anchor == null) {
			View parent = ((Activity) context).getWindow().getDecorView();
			popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
			return;
		}
		int xPos, yPos;
		int[] location = new int[2];
		anchor.getLocationOnScreen(location);
		Rect anchorRect = new Rect(location[0], location[1], location[0] + anchor.getWidth(), location[0] + anchor.getHeight());
		mView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		mView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

		int rootHeight = mView.getMeasuredHeight();
		int screenHeight = windowManager.getDefaultDisplay().getHeight();

		// Set x-coordinate to display the popup menu
		xPos = anchorRect.centerX() - popupWindow.getWidth() / 2;

		int dyTop = anchorRect.top;
		int dyBottom = screenHeight + rootHeight;
		boolean onTop = dyTop > dyBottom;
		// Set y-coordinate to display the popup menu
		if (onTop) {
			yPos = anchorRect.top - rootHeight;
		} else {
			if (anchorRect.bottom > dyTop) {
				yPos = anchorRect.bottom - 20;
			} else {
				yPos = anchorRect.top - anchorRect.bottom + 50;
			}
		}
		popupWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, xPos, yPos);
	}

	public interface OnMenuItemClickListener {

		public void onMenuItemClick(MenuItem menuItem);
	}

	class MenuItem {

		private int itemId;

		private String title;
		/**
		 * 透传参数存储位置，可以在menu点击事件中拿到需要的参数
		 */
		private Object throughParam;

		public MenuItem(int itemId, String title) {
			this(itemId, title, null);
		}

		public MenuItem(int itemId, String title, Object throughtParam) {
			this.itemId = itemId;
			this.title = title;
			this.throughParam = throughtParam;
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Object getThroughParam() {
			return throughParam;
		}

		public void setThroughParam(Object throughParam) {
			this.throughParam = throughParam;
		}
	}
}
