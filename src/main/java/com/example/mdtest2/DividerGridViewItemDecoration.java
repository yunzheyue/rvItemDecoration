package com.example.mdtest2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class DividerGridViewItemDecoration extends ItemDecoration {

	private Drawable mDivider;
	private int[] attrs= new int[]{
			android.R.attr.listDivider
	};

	public DividerGridViewItemDecoration(Context context) {
		TypedArray typedArray = context.obtainStyledAttributes(attrs);
		mDivider = typedArray.getDrawable(0);
		typedArray.recycle();
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, State state) {
		drawVertical(c,parent);
		drawHorizontal(c,parent);
	}

	private void drawHorizontal(Canvas c, RecyclerView parent) {
		// 绘制水平间隔线
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = parent.getChildAt(i);
			RecyclerView.LayoutParams params = (LayoutParams) child.getLayoutParams();
			int left = child.getLeft() - params.leftMargin;
			int right = child.getRight()+ params.rightMargin;
			int top = child.getBottom() + params.bottomMargin;
			int bottom = top + mDivider.getIntrinsicHeight();

			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}


	}

	private void drawVertical(Canvas c, RecyclerView parent) {
		//绘制垂直间隔线(垂直的矩形)
		int childCount = parent.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = parent.getChildAt(i);
			RecyclerView.LayoutParams params = (LayoutParams) child.getLayoutParams();
			int left = child.getRight() + params.rightMargin;
			int right = left + mDivider.getIntrinsicWidth();
			int top = child.getTop() - params.topMargin;
			int bottom = child.getBottom() + params.bottomMargin;

			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}

	}

	@Override
	@Deprecated
	public void getItemOffsets(Rect outRect, int itemPosition,
							   RecyclerView parent) {
		// 四个方向的偏移值
		int right = mDivider.getIntrinsicWidth();
		int bottom = mDivider.getIntrinsicHeight();

		outRect.set(0, 0, right, bottom);
	}


}
