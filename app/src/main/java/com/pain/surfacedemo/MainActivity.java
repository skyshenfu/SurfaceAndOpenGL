package com.pain.surfacedemo;

import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.os.Build;
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
        testSupport();
    }

    private void testSupport() {
        boolean isEmulator = Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                && (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86"));
        final ActivityManager activityManager=(ActivityManager)getSystemService(ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo=activityManager.getDeviceConfigurationInfo();
        boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x2000;
        supportsEs2 = supportsEs2 || isEmulator;
        Log.e("TAG", "testSupport: "+supportsEs2);

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
