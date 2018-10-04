package com.arduino.cloud.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.arduino.cloud.R;
import com.arduino.cloud.util.Base64Util;
import com.arduino.cloud.util.SharedPreferencesHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private final static String TAG = "SplashActivity";
    //启动页停留时间
    private final static int SHOW_TIME = 5;
    private final static int TO_MAIN = -60324;
    private final static int TO_GUIDE = 67531;
    private boolean FIRST_RUN;
    //倒计时数值
    private int countDown = 5;
    private static final int ONE_SEC=1000;
    private Intent intent = new Intent();
    //定时更新倒计时
    private Timer timer;
    private Button btn_skip;
    private static final String FILE_NAME = Base64Util.encode("START_UP".getBytes());
    private SharedPreferencesHelper sharedPreferencesHelper;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TO_MAIN:
                    //启动activity的方式之一，在Manifest文件中声明Activity的Action
                    intent.setAction("android.intent.action.TO_MAIN");
                    break;
                case TO_GUIDE:
                    intent.setAction("android.intent.action.TO_GUID");
                    break;
            }
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "---onCreate---");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        checkVersion();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "---onRestart---");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "---onStart---");
        super.onStart();
    }

    //开始和用户交互
    @Override
    protected void onResume() {
        Log.i(TAG, "---onResume---");
        super.onResume();
        if (!FIRST_RUN) {
            btn_skip.setVisibility(Button.GONE);
            sharedPreferencesHelper.putBoolean("NOT_FIRST_RUN", true);
            mHandler.sendEmptyMessageDelayed(TO_GUIDE, SHOW_TIME * ONE_SEC);
        } else {
            updateCountDown();
            mHandler.sendEmptyMessageDelayed(TO_MAIN, SHOW_TIME * ONE_SEC);
        }
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "---onPause---");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "---onStop---");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "---onDestroy---");
        super.onDestroy();
    }

    //禁止返回按键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_BACK;
    }

    //初始化
    private void init() {
        ImageView ad_img = findViewById(R.id.splash_ad_img);
        btn_skip = findViewById(R.id.splash_btn_skip);
        sharedPreferencesHelper = new SharedPreferencesHelper(this.getApplication());
        sharedPreferencesHelper.open(FILE_NAME);
        FIRST_RUN = sharedPreferencesHelper.getBoolean("NOT_FIRST_RUN");
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeMessages(TO_MAIN);
                intent.setAction("android.intent.action.TO_MAIN");
                startActivity(intent);
                finish();
            }
        });
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("timg.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        ad_img.setImageBitmap(bitmap);
    }

    //检测是否有新版本
    private void checkVersion() {

    }

    private void updateCountDown() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        countDown--;//显示的秒的数值
                        final SpannableString spannableString = new SpannableString(countDown + "s 后跳过");
//                            Spanned.SPAN_INCLUSIVE_EXCLUSIVE 从起始下标到终了下标，包括起始下标
//                            Spanned.SPAN_INCLUSIVE_INCLUSIVE 从起始下标到终了下标，同时包括起始下标和终了下标
//                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE 从起始下标到终了下标，但都不包括起始下标和终了下标
//                            Spanned.SPAN_EXCLUSIVE_INCLUSIVE 从起始下标到终了下标，包括终了下标
                        //设置数字的前景色
                        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        //设置其余文本文本的前景色
                        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 2, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        btn_skip.setText(spannableString);
                        if (countDown < 0) {
                            //取消定时任务
                            timer.cancel();
                            //倒计时到0隐藏字体
                            btn_skip.setVisibility(View.GONE);
                        }
                    }
                });


            }
        }, ONE_SEC, ONE_SEC);
    }
}
