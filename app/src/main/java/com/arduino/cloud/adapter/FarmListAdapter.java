package com.arduino.cloud.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.arduino.cloud.R;
import com.arduino.cloud.bean.FarmListItem;

import java.util.List;

public class FarmListAdapter extends BaseAdapter {
    private static final String TAG = "FarmListAdapter";
    private Context mContext;
    private List<FarmListItem> mDatas;

    public FarmListAdapter(Context context, List<FarmListItem> data) {
        this.mDatas = data;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.farm_item_layout, null);
            holder = new ViewHolder();
            holder.tv_name = convertView.findViewById(R.id.farm_item_tv_name);
            holder.rb_status = convertView.findViewById(R.id.farm_item_rbtn_status);
            holder.tv_id = convertView.findViewById(R.id.farm_item_tv_device_id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        FarmListItem farmListItem = mDatas.get(position);
        Log.i(TAG, "postion" + position);
        holder.tv_name.setText(farmListItem.getFarm_name());
        holder.tv_id.setText(farmListItem.getDevice_id());
        holder.rb_status.setText("离线");
        if (farmListItem.isOnline()) {
            holder.rb_status.setChecked(true);
            holder.rb_status.setText("在线");
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_name;
        TextView tv_id;
        RadioButton rb_status;
    }
}
