package com.arduino.cloud.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.arduino.cloud.R;
import com.arduino.cloud.customview.CustomTextView;

public class MinePage extends BasePager {
    private static final String TAG = "MinePage";
    private CustomTextView setting,message;
    public MinePage(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initViews() {
        setting=mRootView.findViewById(R.id.page_control_item_setting);
        message=mRootView.findViewById(R.id.page_control_item_message);
        setting.setOnItemClickListener(new CustomTextView.ItemClickListener() {
            @Override
            public void onItemClick(View v) {
                Log.i(TAG,"设置功能暂未开放");
                Toast.makeText(mContext,"设置功能暂未开放",Toast.LENGTH_SHORT).show();
            }
        });
        message.setOnItemClickListener(new CustomTextView.ItemClickListener() {
            @Override
            public void onItemClick(View v) {
                Log.i(TAG,"信息功能暂未开放");
                Toast.makeText(mContext,"信息功能暂未开放",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {

    }
    @Override
    protected int loadLayoutById() {
        return R.layout.page_mine;
    }
}
