package com.arduino.cloud.ui;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.arduino.cloud.R;
import com.arduino.cloud.util.ApiHelper;
import com.arduino.cloud.util.ApiTest;

public class HomePage extends BasePager {
    Button btn_get_status;
    public HomePage(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initViews() {
        btn_get_status=mRootView.findViewById(R.id.getStatus);
        btn_get_status.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiHelper apiHelper=ApiHelper.getInstance();
                apiHelper.sendCmd("38570211","1");
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int loadLayoutById() {
        return R.layout.page_home;
    }

}
