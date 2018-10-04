package com.arduino.cloud.activity;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.arduino.cloud.R;
import com.arduino.cloud.ui.BasePager;
import com.arduino.cloud.ui.ControlPage;
import com.arduino.cloud.ui.FarmPage;
import com.arduino.cloud.ui.HomePage;
import com.arduino.cloud.ui.MainPagerAdapter;
import com.arduino.cloud.ui.MinePage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ViewPager viewPager;
    private RadioGroup rg_guid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        viewPager=findViewById(R.id.main_vp_main);
        rg_guid=findViewById(R.id.act_rg_main);
        List<BasePager> views=new ArrayList<>();
        views.add(new HomePage(this));
        views.add(new FarmPage(this));
        views.add(new ControlPage(this));
        views.add(new MinePage(this));
        MainPagerAdapter viewPagerAdapter=new MainPagerAdapter(views);
        viewPager.setAdapter(viewPagerAdapter);
        rg_guid.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index = -1;
                switch (checkedId) {
                    case R.id.act_rbtn_hp:
                        index = 0;
                        break;
                    case R.id.act_rbtn_farm:
                        index = 1;
                        break;
                    case R.id.act_rbtn_control:
                        index = 2;
                        break;
                    case R.id.act_rbtn_mine:
                        index = 3;
                        break;
                }
                Log.i(TAG, "------onCheckedChanged------- index:" + index);
                if (index >= 0)
                    viewPager.setCurrentItem(index);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int index = -1;
                switch (position) {
                    case 0:
                        index = R.id.act_rbtn_hp;
                        break;
                    case 1:
                        index = R.id.act_rbtn_farm;
                        break;
                    case 2:
                        index = R.id.act_rbtn_control;
                        break;
                    case 3:
                        index = R.id.act_rbtn_mine;
                        break;
                }
                Log.i(TAG, "------onPageSelected------- index:" + index);
                if (index >= 0)
                    rg_guid.check(index);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
