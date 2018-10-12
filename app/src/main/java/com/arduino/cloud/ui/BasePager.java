package com.arduino.cloud.ui;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

public abstract class BasePager extends ViewPager {
    private static final String TAG = "BasePager";
    public Context mContext;
    public View mRootView;
    public Activity mActivity;
    protected LayoutInflater mLayoutInflater;

    public BasePager(@NonNull Context context) {
        this(context, null);
    }

    public BasePager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mActivity = context2activity(context);
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mRootView = mLayoutInflater.inflate(loadLayoutById(), null);
        initView();
    }

    private void initView() {
        initViews();
    }

    private Activity context2activity(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            ContextWrapper wrapper = (ContextWrapper) context;
            return context2activity(wrapper.getBaseContext());
        } else {
            throw new IllegalArgumentException("Context Illegal");
        }
    }

    protected abstract void initViews();

    protected abstract void initData();

    protected int loadLayoutById() {
        return 0;
    }
}
