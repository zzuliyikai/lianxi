package com.lock;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2019/3/13.
 */

public class SimpleLayout extends ViewGroup {

    public SimpleLayout(Context context) {
        this(context,null);
    }

    public SimpleLayout(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public SimpleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureChild(getChildAt(0),widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

}
