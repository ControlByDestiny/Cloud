package com.arduino.cloud.ui;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 主页页面切换的适配器
 */
public class MainPagerAdapter extends PagerAdapter {

    private static final String TAG = "MainPagerAdapter";

    private List<BasePager> pagers;

    public MainPagerAdapter(List<BasePager> pagers) {
        this.pagers = pagers;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        Log.i(TAG,"-------isViewFromObject-----------");
        return arg0 == arg1;
    }

    @Override
    public int getCount() {
        Log.i(TAG,"-------getCount-----------");
        if(pagers==null){
            return 0;
        }
        return pagers.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        Log.i(TAG,"-------destroyItem-----------position = " + position);
        container.removeView(pagers.get(position).mRootView);

    }

    @Override
    public int getItemPosition(Object object) {
        Log.i(TAG,"-------getItemPosition-----------");
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i(TAG,"-------instantiateItem-----------position = " + position);
        View view = pagers.get(position).mRootView;
        container.addView(view);
        return view;
    }
}
