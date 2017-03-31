package com.pain.surfacedemo;

import android.graphics.ImageFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zty
 * 个人github地址：http://www.github.com/skyshenfu
 * 日期：2017/3/31
 * 版本：1.0.0
 * 描述：
 */

public class OpenGlDemoActivity extends AppCompatActivity {
    GLSurfaceView glSurfaceView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        glSurfaceView=new GLSurfaceView(this);
        glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        glSurfaceView.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
                gl.glClearColor(1f,0f,0f,0f);
            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
                gl.glViewport(0, 0, width, height);
            }

            @Override
            public void onDrawFrame(GL10 gl) {
                gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
            }
        });
        setContentView(glSurfaceView);
        Log.e("s", "onCreate: " );
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(glSurfaceView!=null){
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (glSurfaceView!=null){
            glSurfaceView.onResume();
        }
    }
}
