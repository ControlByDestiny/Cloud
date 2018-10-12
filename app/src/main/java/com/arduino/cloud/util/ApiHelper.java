package com.arduino.cloud.util;

import android.app.Activity;
import android.app.Application;

import android.util.Log;
import android.widget.Toast;

import com.arduino.cloud.bean.Device;
import com.chinamobile.iot.onenet.OneNetApi;
import com.chinamobile.iot.onenet.OneNetApiCallback;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ApiHelper {
    private static final String URL = "http://api.heclouds.com/devices";
    private static final String CMD = "http://api.heclouds.com/devices";
    private static final String KEY = "http://api.heclouds.com/keys";
    private static final String TAG = "ApiTest";
    private List<Device> mDevices = new ArrayList<>();
    private static ApiHelper mInstance = null;
    private static Application mApplication = null;
    private TaskCompleteListener mListener;

    private ApiHelper() {
    }

    public static ApiHelper getInstance() {
        if (mInstance == null) {
            synchronized (ApiTest.class) {
                if (mInstance == null) {
                    mInstance = new ApiHelper();
                }
            }
        }
        return mInstance;
    }

    public void init(Activity activity) {
        mApplication = activity.getApplication();
        OneNetApi.init(mApplication, true);
    }

    public void getAllDevice(TaskCompleteListener taskCompleteListener) {
        this.mListener = taskCompleteListener;
        OneNetApi.get(URL, new OneNetApiCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray jsonArray = new JSONObject(response).getJSONObject("data").getJSONArray("devices");
                    int count = jsonArray.length();
                    Device device = new Device();
                    JSONObject temp;
                    for (int i = 0; i < count; i++) {
                        temp = jsonArray.getJSONObject(i);
                        device.setDeviceId(temp.getString("id"));
                        device.setDeviceStatus(temp.getBoolean("online"));
                        device.setDeviceTitle(temp.getString("title"));
                        mDevices.add(device);
                    }
                    mListener.onComplete(mDevices);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(Exception e) {
                Toasty.error(mApplication.getApplicationContext(), "网络错误，请稍后再试!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logKey() {
        OneNetApi.get(KEY, new OneNetApiCallback() {
            @Override
            public void onSuccess(String response) {
                Log.i(TAG, response);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
    public void getDevices(OneNetApiCallback oneNetApiCallback){
        OneNetApi.get(URL, oneNetApiCallback);
    }
    public void sendCmd(String deviceID, String cmd) {

        try {
            JSONObject jsonObject=new JSONObject();
            JSONArray jsonArray=new JSONArray();
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("id","KEY");
            jsonArray.put(jsonObject1);
            JSONArray jsonArray1=new JSONArray();
            JSONObject jsonObject3=new JSONObject();
            jsonObject3.put("value","1");
            jsonArray1.put(jsonObject3);
            jsonObject1.put("datapoints",jsonArray1);

            jsonObject.put("datastreams",jsonArray);
            Log.i(TAG, jsonObject.toString());
            OneNetApi.addDataPoints(deviceID, jsonObject.toString(), new OneNetApiCallback() {
                @Override
                public void onSuccess(String response) {
                    Log.i(TAG, response);
                }

                @Override
                public void onFailed(Exception e) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        OneNetApi.sendCmdToDevice(deviceID, cmd, new OneNetApiCallback() {
//            @Override
//            public void onSuccess(String response) {
//                Log.i(TAG, response);
//            }
//
//            @Override
//            public void onFailed(Exception e) {
//                Log.i(TAG, e.toString());
//            }
//        });


    }

    public interface TaskCompleteListener {
        void onComplete(List<Device> devices);
    }
}
