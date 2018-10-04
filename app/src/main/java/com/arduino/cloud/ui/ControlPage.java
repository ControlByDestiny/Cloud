package com.arduino.cloud.ui;

import android.content.Context;
import android.support.annotation.NonNull;

import com.arduino.cloud.R;

public class ControlPage extends BasePager {
    public ControlPage(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int loadLayoutById() {
        return R.layout.page_control;
    }
}
