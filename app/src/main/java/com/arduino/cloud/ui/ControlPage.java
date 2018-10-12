package com.arduino.cloud.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ListView;

import com.arduino.cloud.R;
import com.arduino.cloud.adapter.ControlListAdapter;
import com.arduino.cloud.adapter.FarmListAdapter;
import com.arduino.cloud.bean.Device;
import com.arduino.cloud.bean.FarmListItem;
import com.arduino.cloud.util.ApiHelper;
import com.chinamobile.iot.onenet.OneNetApiCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ControlPage extends BasePager {
    private static final String TAG = "ControlPage";
    private ListView mListView;
    private List<String> mDatas;
    private ApiHelper apiHelper;

    public ControlPage(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void initViews() {
        mListView = mRootView.findViewById(R.id.page_lv_control);
        apiHelper = ApiHelper.getInstance();
        mDatas = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("command", "V");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        apiHelper.sendCmd("38570211", jsonObject.toString());
        apiHelper.getDevices(new OneNetApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray jsonArray = new JSONObject(response).getJSONObject("data").getJSONArray("devices");
                    int count = jsonArray.length();
                    Device device = new Device();
                    JSONObject temp;
                    for (int i = 0; i < count; i++) {
                        temp = jsonArray.getJSONObject(i);
                        mDatas.add(temp.getString("id"));
                    }
                    ControlListAdapter controlListAdapter = new ControlListAdapter(mContext, mDatas);
                    mListView.setAdapter(controlListAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });

//        apiHelper.init(mActivity);
//        apiHelper.getAllDevice(new ApiHelper.TaskCompleteListener() {
//            @Override
//            public void onComplete(List<Device> devices) {
//                for (Device device : devices) {
//                    mDatas.add(device.getDeviceId());
//                }
//                ControlListAdapter controlListAdapter = new ControlListAdapter(mContext, mDatas);
//                mListView.setAdapter(controlListAdapter);
//            }
//        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int loadLayoutById() {
        return R.layout.page_control;
    }
}
