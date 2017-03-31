package com.pain.surfacedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/31
 * 版本：1.0.0
 * 描述：
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback2{
    private DrawThread mThread;
     SurfaceHolder surfaceHolder;

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    //♀苏菲创建的时候调用的
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread = new DrawThread(getContext(), holder);
        mThread.start();
    }

    private void init() {
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    //♀苏菲改变的时候调用的
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    //♀苏菲销毁的时候调用的
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.stopThread();

    }

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {

    }


    class DrawThread extends Thread {
        SurfaceHolder surfaceHolder;
        Context context;
        Paint paint;
        private boolean isRunning = true;
        float r = 10;
        float diff = 0;

        public DrawThread(Context context, SurfaceHolder holder) {
            this.context = context;
            this.surfaceHolder = holder;
            paint = new Paint();
            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
        }

        @Override
        public void run() {
            while (isRunning) {
                synchronized (surfaceHolder) {
                    if (surfaceHolder != null) {
                        Canvas canvas = surfaceHolder.lockCanvas();
                        draw(canvas);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }
        public void draw(Canvas canvas) {
            if (r < 30) {
                diff = 10;
            } else if (r > 150) {
                diff = -10;
            }
            r += diff;
            if (canvas!=null)
            {
                canvas.drawColor(Color.WHITE);
                canvas.translate(200, 200);
                canvas.drawCircle(0, 0, r, paint);
                Log.i("iii", "draw thread id: " + Thread.currentThread().getId());
            }else {
                Log.i("iii", "draw finished " + Thread.currentThread().getId());
            }

        }

        private void stopThread() {
            Log.i("iii", "stopThread()");
            isRunning = false;
        }

    }

}
