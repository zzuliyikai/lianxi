package com.lock;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class OohlinkAdLayout extends ViewGroup {

    public OohlinkAdLayout(Context context) {
        this(context, null);
    }

    public OohlinkAdLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OohlinkAdLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();

        int maxHeight = 0;
        int maxWidth = 0;

        // Find out how big everyone wants to be
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        // Find rightmost and bottom-most child
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                int childRight;
                int childBottom;

                OohlinkAdLayout.LayoutParams lp =
                        (OohlinkAdLayout.LayoutParams) child.getLayoutParams();

                childRight = lp.x + child.getMeasuredWidth();
                childBottom = lp.y + child.getMeasuredHeight();

                maxWidth = Math.max(maxWidth, childRight);
                maxHeight = Math.max(maxHeight, childBottom);
            }
        }

        // Check against minimum height and width
        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
                resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            OohlinkAdLayout.LayoutParams lp =
                    (OohlinkAdLayout.LayoutParams) child.getLayoutParams();
            child.layout(lp.x, lp.y, lp.x + child.getMeasuredWidth(),
                    lp.y + child.getMeasuredHeight());
        }
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof OohlinkAdLayout.LayoutParams;
    }

    @Override
    protected OohlinkAdLayout.LayoutParams generateDefaultLayoutParams() {
        return new OohlinkAdLayout.LayoutParams(OohlinkAdLayout.LayoutParams.WRAP_CONTENT,
                OohlinkAdLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public OohlinkAdLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new OohlinkAdLayout.LayoutParams(getContext(), attributeSet);
    }

    @Override
    protected OohlinkAdLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new OohlinkAdLayout.LayoutParams(p);
    }

    public static class LayoutParams extends ViewGroup.LayoutParams {
        private int x;
        private int y;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public void setPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
