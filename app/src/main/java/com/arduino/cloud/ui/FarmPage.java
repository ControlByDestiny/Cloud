package com.arduino.cloud.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.arduino.cloud.R;
import com.arduino.cloud.adapter.FarmListAdapter;
import com.arduino.cloud.bean.Device;
import com.arduino.cloud.bean.FarmListItem;
import com.arduino.cloud.util.ApiHelper;
import com.chinamobile.iot.onenet.OneNetApiCallback;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FarmPage extends BasePager {
    private static final String TAG = "FarmPage";
    private ListView mListView;
    private List<FarmListItem> mDatas;
    private ApiHelper apiHelper;

    public FarmPage(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initViews() {
        mListView = mRootView.findViewById(R.id.page_lv_main);
        mDatas=new ArrayList<>();
        apiHelper = ApiHelper.getInstance();
        apiHelper.init(mActivity);
        apiHelper.getDevices(new OneNetApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray jsonArray = new JSONObject(response).getJSONObject("data").getJSONArray("devices");
                    int count = jsonArray.length();
                    JSONObject temp;
                    for (int i = 0; i < count; i++) {
                        temp = jsonArray.getJSONObject(i);
                        FarmListItem farmListItem = new FarmListItem();
                        farmListItem.setDevice_id("设备ID:" + temp.getString("id"));
                        farmListItem.setFarm_name(temp.getString("title"));
                        farmListItem.setOnline(temp.getBoolean("online"));
                        mDatas.add(farmListItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mListView.setFocusable(true);
                FarmListAdapter farmListAdapter = new FarmListAdapter(mContext, mDatas);
                mListView.setAdapter(farmListAdapter);

            }

            @Override
            public void onFailed(Exception e) {

            }
        });
//        apiHelper.getAllDevice(new ApiHelper.TaskCompleteListener() {
//            @Override
//            public void onComplete(List<Device> devices) {
//                mDatas = new ArrayList<>();
//                for (Device device : devices) {
//                    FarmListItem farmListItem = new FarmListItem();
//                    farmListItem.setDevice_id("设备ID:"+device.getDeviceId());
//                    farmListItem.setFarm_name(device.getDeviceTitle());
//                    farmListItem.setOnline(device.getDeviceStatus());
//                    mDatas.add(farmListItem);
//                }
//                mListView.setFocusable(true);
//                FarmListAdapter farmListAdapter = new FarmListAdapter(mContext, mDatas);
//                mListView.setAdapter(farmListAdapter);
//            }
//        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
