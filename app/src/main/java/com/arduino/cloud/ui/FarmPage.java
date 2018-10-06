package com.arduino.cloud.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.arduino.cloud.R;
import com.arduino.cloud.adapter.FarmListAdapter;
import com.arduino.cloud.bean.FarmListItem;

import java.util.ArrayList;
import java.util.List;

public class FarmPage extends BasePager {
    private static final String TAG = "FarmPage";
    private ListView mListView;
    private List<FarmListItem> mDatas;
    ;

    public FarmPage(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initViews() {
        mListView = mRootView.findViewById(R.id.page_lv_main);
        mDatas = new ArrayList<>();
        mListView.setFocusable(true);
        for (int i = 0; i < 5; i++) {
            FarmListItem farmListItem = new FarmListItem();
            farmListItem.setDevice_id("10086" + i);
            farmListItem.setFarm_name("我的花园" + i);
            farmListItem.setOnline(false);
            if (i % 2 == 0){
                farmListItem.setOnline(true);
            }
            mDatas.add(farmListItem);
        }
        FarmListAdapter farmListAdapter = new FarmListAdapter(mContext, mDatas);
        mListView.setAdapter(farmListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "---position:" + position + " ---id:" + id);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int loadLayoutById() {
        return R.layout.page_farm;
    }
}
