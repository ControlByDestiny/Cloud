package com.arduino.cloud.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class DashBoard extends View {
    public DashBoard(Context context) {
        this(context,null);
    }

    public DashBoard(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DashBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
