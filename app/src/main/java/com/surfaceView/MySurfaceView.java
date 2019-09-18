package com.surfaceView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {


    private MyThread mMyThread;
    private int mWidth;
    private int mHeight;

    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        mMyThread = new MyThread(surfaceHolder);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mMyThread.isRunning = true;
        mMyThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        mMyThread.isRunning = false;
        //这块的意思其实就是，等待DrawThread执行完，上面的isRunning其实也达到这个目的咯
        try {
            mMyThread.join();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

    }


     class MyThread extends Thread{

        SurfaceHolder mSurfaceHolder;
        boolean isRunning;
        int radius = 10;
        Paint mPaintCirlce;
        Paint mPaintText;

        public MyThread(SurfaceHolder surfaceHolder) {
            this.mSurfaceHolder = surfaceHolder;
            isRunning = false;
            mPaintCirlce = new Paint();
            mPaintCirlce.setStrokeWidth(4);
            mPaintCirlce.setColor(Color.YELLOW);
            mPaintCirlce.setStyle(Paint.Style.FILL_AND_STROKE);

            mPaintText = new Paint();
            mPaintText.setTextSize(24);
        }

        @Override
        public void run() {
            Canvas c = null;

            while (isRunning){

                try {
                    synchronized (mSurfaceHolder) {

                        c = mSurfaceHolder.lockCanvas(null);
                        doDraw(c);
                        //通过它来控制帧数执行一次绘制后休息500ms,实际上，我们知道，ondraw如果保证60FPS的话，看到的画面会比较流畅
                        //因此，这里，可能就是16ms咯，但是，我们这里完全可以设置的比16ms都小，知道surface的好处了吧。
                        Thread.sleep(500);
                    }
                }catch (InterruptedException e ) {
                    e.printStackTrace();
                } finally {
                    mSurfaceHolder.unlockCanvasAndPost(c);
                }


            }


        }

        private void doDraw(Canvas c) {

            c.drawColor(Color.WHITE);
            //这里将画笔放到view的中间
            c.translate(mWidth / 2, mHeight / 2);

            c.drawCircle(0, 0, radius++, mPaintCirlce);

            String text = "这里是surface测试ooo";

            c.drawText(text.substring(0,radius % text.length()+1), -mPaintText.measureText(text.substring(0,radius % text.length()+1)) / 2, 0f, mPaintText);

            Log.d("yikai","长度 = "+radius % text.length()+1);
            Log.d("yikai","内容 =" +text.substring(0,radius % text.length()+1));
            Log.d("yikai","x =" + -mPaintText.measureText(text.substring(0,radius % text.length()+1)) / 2);


            if (radius > mWidth / 2) {
                radius = 10;
            }
        }
    }
}
