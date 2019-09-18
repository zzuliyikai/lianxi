package com.CustomerView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.rxjava2.test.R;

public class CountDownProgressBar extends View {

    /**
     * 进度条的最大值
     */
    private int maxValue = 1000;

    /**
     * 当前进度值
     */
    private int currentValue;

    /**
     * 每次扫过的角度，用来设置进度条圆弧所对应的圆心角，alphaAngle=(currentValue/maxValue)*360
     */
    private float alphaAngle;

    /**
     * 底部圆弧的颜色，默认为Color.LTGRAY
     */
    private int firstColor = Color.LTGRAY;

    /**
     * 进度条圆弧块的颜色
     */
    private int secondColor = Color.BLUE;
    /**
     * 中间文字颜色(默认蓝色)
     */
    private int centerTextColor = Color.BLUE;
    /**
     * 中间文字的字体大小(默认40dp)
     */
    private int centerTextSize = 20;

    /**
     * 圆环的宽度
     */
    private int circleWidth = 6;

    /**
     * 画圆弧的画笔
     */
    private Paint circlePaint;

    /**
     * 画文字的画笔
     */
    private Paint textPaint;
    /**
     * 是否使用渐变色
     */
    private boolean isShowGradient = true;

    /**
     * 渐变圆周颜色数组
     */
    private int[] colorArray = new int[]{Color.parseColor("#2773FF"),
            Color.parseColor("#27C0D2"), Color.parseColor("#40C66E")};
    private int duration;
    private OnFinishListener listener;
    private ValueAnimator animator;


    public CountDownProgressBar(Context context) {
        this(context, null);
    }

    public CountDownProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CountDownProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CountDownProgressBar, defStyleAttr, 0);

        int typedArrayLength = typedArray.getIndexCount();

        for (int i = 0; i < typedArrayLength; i++) {

            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.CountDownProgressBar_countDown_firstColor) {
                // 默认底色为亮灰色
                firstColor = typedArray.getColor(attr, Color.LTGRAY);
            } else if (attr == R.styleable.CountDownProgressBar_countDown_secondColor) {
                // 默认进度条颜色为蓝色
                secondColor = typedArray.getColor(attr, Color.BLUE);
            } else if (attr == R.styleable.CountDownProgressBar_countDown_centerTextSize) {
                // 默认中间文字字体大小为40dp
                centerTextSize = typedArray.getDimensionPixelSize(attr, (int) dip2px(40));
            } else if (attr == R.styleable.CountDownProgressBar_countDown_circleWidth) {
                // 默认圆弧宽度为6dp
                circleWidth = typedArray.getDimensionPixelSize(attr, (int) dip2px(6f));
            } else if (attr == R.styleable.CountDownProgressBar_countDown_centerTextColor) {
                // 默认中间文字颜色为蓝色
                centerTextColor = typedArray.getColor(attr, Color.BLUE);
            } else if (attr == R.styleable.CountDownProgressBar_countDown_isShowGradient) {
                // 默认不适用渐变色
                isShowGradient = typedArray.getBoolean(attr, false);
            }
        }
        typedArray.recycle();

        circlePaint = new Paint();
        // 抗锯齿
        circlePaint.setAntiAlias(true);
        // 防抖动
        circlePaint.setDither(true);
        //画笔宽度
        circlePaint.setStrokeWidth(circleWidth);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 分别获取期望的宽度和高度，并取其中较小的尺寸作为该控件的宽和高,并且不超过屏幕宽高
        //获取屏幕宽
        int widthPixels = this.getResources().getDisplayMetrics().widthPixels;
        //获取屏幕高
        int heightPixels = this.getResources().getDisplayMetrics().heightPixels;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int minWidth = Math.min(widthPixels, width);
        int minHeight = Math.min(heightPixels, height);
        setMeasuredDimension(Math.min(minWidth, minHeight), Math.min(minWidth, minHeight));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth() / 2;
        int radius = center - circleWidth;

        drawCircle(canvas, center, radius);
        drawText(canvas, center);
    }

    private void drawCircle(Canvas canvas, int center, int radius) {
        circlePaint.setShader(null);
        circlePaint.setColor(firstColor);
        //空心圆
        circlePaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(center, center, radius, circlePaint);
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);

        if (isShowGradient) {
            LinearGradient linearGradient = new LinearGradient(circleWidth, circleWidth, getMeasuredWidth()
                    - circleWidth, getMeasuredHeight() - circleWidth, colorArray, null, Shader.TileMode.MIRROR);
            circlePaint.setShader(linearGradient);
        }
        circlePaint.setShadowLayer(10, 10, 10, Color.BLUE);
        // 设置圆弧的颜色
        circlePaint.setColor(secondColor);
        // 把每段圆弧改成圆角的
        circlePaint.setStrokeCap(Paint.Cap.ROUND);
        // 计算每次画圆弧时扫过的角度，这里计算要注意分母要转为float类型，否则alphaAngle永远为0
        alphaAngle = currentValue * 360.0f / maxValue * 1.0f;
        canvas.drawArc(oval, -90, alphaAngle, false, circlePaint);

    }

    private void drawText(Canvas canvas, int center) {
        // 计算进度
        int result = ((maxValue - currentValue) * (duration / 1000) / maxValue);
        String percent;
        if (maxValue == currentValue) {
            percent = "完成";
            // 设置要绘制的文字大小
            textPaint.setTextSize(centerTextSize);
        } else {
            percent = (result / 60 < 10 ? "0" + result / 60 : result / 60) + ":" + (result % 60 < 10 ? "0" + result % 60 : result % 60);
            // 设置要绘制的文字大小
            textPaint.setTextSize(centerTextSize + centerTextSize / 3);
        }
        // 设置文字居中，文字的x坐标要注意
        textPaint.setTextAlign(Paint.Align.CENTER);
        // 设置文字颜色
        textPaint.setColor(centerTextColor);
        // 注意此处一定要重新设置宽度为0,否则绘制的文字会重叠
        textPaint.setStrokeWidth(0);
        // 文字边框
        Rect bounds = new Rect();
        // 获得绘制文字的边界矩形
        textPaint.getTextBounds(percent, 0, percent.length(), bounds);
        // 获取绘制Text时的四条线
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        // 计算文字的基线,方法见http://blog.csdn.net/harvic880925/article/details/50423762
        int baseline = center + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        // 绘制表示进度的文字
        canvas.drawText(percent, center, baseline, textPaint);
    }

    public interface OnFinishListener {
        void onFinish();
    }

    public void setOnFinishListener(OnFinishListener listener) {
        this.listener = listener;
    }


    /**
     * 设置中间文字字体大小
     *
     * @param centerTextSize 颜色数组，类型为int[]
     */
    public void setCenterTextSize(int centerTextSize) {
        this.centerTextSize = centerTextSize;
        //一般只是希望在View发生改变时对UI进行重绘。invalidate()方法系统会自动调用 View的onDraw()方法。
        invalidate();
    }

    /**
     * 设置进度条渐变色颜色数组
     *
     * @param colors 颜色数组，类型为int[]
     */
    public void setColorArray(int[] colors) {
        this.colorArray = colors;
        //一般只是希望在View发生改变时对UI进行重绘。invalidate()方法系统会自动调用 View的onDraw()方法。
        invalidate();
    }

    /**
     * 设置圆环的宽度
     *
     * @param width
     */
    public void setCircleWidth(int width) {
        this.circleWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, getResources()
                .getDisplayMetrics());
        circlePaint.setStrokeWidth(circleWidth);
        //一般只是希望在View发生改变时对UI进行重绘。invalidate()方法系统会自动调用 View的onDraw()方法。
        invalidate();
    }

    /**
     * 设置圆环的底色，默认为亮灰色LTGRAY
     *
     * @param color
     */
    public void setFirstColor(int color) {
        this.firstColor = color;
        circlePaint.setColor(firstColor);
        //一般只是希望在View发生改变时对UI进行重绘。invalidate()方法系统会自动调用 View的onDraw()方法。
        invalidate();
    }

    /**
     * 设置进度条的颜色，默认为蓝色<br>
     *
     * @param color
     */
    public void setSecondColor(int color) {
        this.secondColor = color;
        circlePaint.setColor(secondColor);
        //一般只是希望在View发生改变时对UI进行重绘。invalidate()方法系统会自动调用 View的onDraw()方法。
        invalidate();
    }


    /**
     * 按进度显示百分比，可选择是否启用数字动画
     *
     * @param duration 动画时长
     */
    public void setDuration(int duration, OnFinishListener listener) {
        this.listener = listener;
        this.duration = duration + 1000;
        if (animator != null) {
            animator.cancel();
        } else {
            animator = ValueAnimator.ofInt(0, maxValue);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentValue = (int) animation.getAnimatedValue();
                    //一般只是希望在View发生改变时对UI进行重绘。invalidate()方法系统会自动调用 View的onDraw()方法。
                    invalidate();
                    if (maxValue == currentValue && CountDownProgressBar.this.listener != null) {
                        CountDownProgressBar.this.listener.onFinish();
                        animator.cancel();
                    }
                }
            });
            animator.setInterpolator(new LinearInterpolator());
        }
        animator.setDuration(duration);
        animator.start();
    }



    private static float dip2px(float dipValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (dipValue * scale + 0.5f);
    }



}
