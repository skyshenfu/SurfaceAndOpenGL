package com.pain.surfacedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    MySurfaceView mySurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySurfaceView= (MySurfaceView) findViewById(R.id.mysurface);
    }

    @Override
    protected void onDestroy() {
        Log.e("TAG", "onDestroy: ");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mySurfaceView.surfaceDestroyed(mySurfaceView.getHolder());
        super.onPause();
    }
}
