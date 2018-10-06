package com.arduino.cloud.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.arduino.cloud.R;
import com.arduino.cloud.adapter.ViewPagerAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {
    private String[] imgs = {"guid_0.jpg", "guid_1.jpg", "guid_2.jpg"};
    ImageView guid_iv;
    Button btn_start;
    View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        ViewPager mViewPager = findViewById(R.id.guid_vp_main);
        LayoutInflater lf = getLayoutInflater();
        int count = imgs.length;
        List<View> views = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            mView = lf.inflate(R.layout.pager_guide, null);
            guid_iv = mView.findViewById(R.id.guid_iv_main);
            btn_start = mView.findViewById(R.id.guid_btn_start);
            InputStream inputStream = getAssets().open(imgs[i]);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            guid_iv.setImageBitmap(bitmap);
            if (i == count - 1) {
                btn_start.setVisibility(Button.VISIBLE);
            }
            views.add(mView);
        }
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(views);
        mViewPager.setAdapter(viewPagerAdapter);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("android.intent.action.TO_MAIN");
                startActivity(intent);
            }
        });

    }
}
