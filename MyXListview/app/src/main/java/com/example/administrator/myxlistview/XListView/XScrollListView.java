package com.example.administrator.myxlistview.XListView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ListView;

public class XScrollListView extends ListView{

	public XScrollListView(Context context) {
		super(context);
	}
	
	public XScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, mExpandSpec);
	}
}
