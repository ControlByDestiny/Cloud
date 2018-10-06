package com.arduino.cloud.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.arduino.cloud.R;

public class MinePage extends BasePager {
    public MinePage(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int loadLayoutById() {
        return R.layout.page_mine;
    }
}
