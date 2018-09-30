package com.arduino.cloud.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.arduino.cloud.R;

public class SplashActivity extends AppCompatActivity {
    private final static String TAG="SplashActivity";
    private final static int SHOW_TIME=3;
    private final static int TO_MAIN=-60324;
    private final static int TO_GUIDE=67531;
    private Intent intent;
    Handler mHandler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case TO_MAIN:
                    intent=new Intent(SplashActivity.this,MainActivity.class);
                    break;
                case TO_GUIDE:
                    intent=new Intent(SplashActivity.this,GuideActivity.class);
                    break;
            }
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mHandler.sendEmptyMessageDelayed(TO_MAIN,SHOW_TIME*1000);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //开始和用户交互
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //禁止返回按键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode==KeyEvent.KEYCODE_BACK;
    }
}
