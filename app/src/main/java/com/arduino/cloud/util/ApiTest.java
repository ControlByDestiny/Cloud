package com.arduino.cloud.util;

import android.app.Application;
import android.util.Log;

import com.chinamobile.iot.onenet.OneNetApi;
import com.chinamobile.iot.onenet.OneNetApiCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiTest {
    private static final String TAG = "ApiTest";
    private static ApiTest mInstance = null;
    private static Application mApplication = null;
    private OneNetApi mApi = new OneNetApi();

    private ApiTest() {
    }

    public static ApiTest getInstance() {
        if (mInstance == null) {
            synchronized (ApiTest.class) {
                if (mInstance == null) {
                    mInstance = new ApiTest();
                }
            }
        }
        return mInstance;
    }

    public void init(Application application) {
        mApplication = application;
        OneNetApi.init(mApplication, true);
    }

    public int getClientStaus() {
        String url = "http://api.heclouds.com/devices";

        OneNetApi.get(url, new OneNetApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.i(TAG,response);
//                    JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONArray("devices");
//                    for(int i=0;i<jsonArray.length();i++){
//                        JSONObject temp=jsonArray.getJSONObject(i);
//                        Log.i(TAG,"设备编号："+temp.get("id"));
//                        Log.i(TAG,"设备状态："+temp.get("online"));
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
        return 0;
    }
    public void  getData(){
        String url="http://api.heclouds.com/devices/38570211/datastreams/TEMP";
        OneNetApi.get(url, new OneNetApiCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(TAG,response+"");
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
