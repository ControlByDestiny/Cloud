package com.arduino.cloud.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.arduino.cloud.R;
import com.arduino.cloud.bean.FarmListItem;
import com.arduino.cloud.util.ApiHelper;
import com.suke.widget.SwitchButton;
import com.xw.repo.BubbleSeekBar;

import java.util.List;

public class ControlListAdapter extends BaseAdapter {
    private static final String TAG = "ControlListAdapter";
    private Context mContext;
    private List<String> mDatas;
    private ApiHelper mApihHelper=ApiHelper.getInstance();

    public ControlListAdapter(Context context, List<String> data) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.control_item_layout, null);
            viewHolder.tv_id = convertView.findViewById(R.id.control_item_device_id);
            viewHolder.sb_beam = convertView.findViewById(R.id.control_item_sb_beam);
            viewHolder.sb_fan = convertView.findViewById(R.id.control_item_sw_fan);
            viewHolder.sb_water = convertView.findViewById(R.id.control_item_sw_water);
            viewHolder.sb_spray = convertView.findViewById(R.id.control_item_sw_spray);
            viewHolder.sb_heat = convertView.findViewById(R.id.control_item_sw_heat);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_id.setText(mDatas.get(position));

        viewHolder.sb_beam.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            int current_progress;
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
//                Log.i(TAG, position + "---" + "onProgressChanged:进度：" + progress);
//                current_progress= viewHolder.sb_beam.getProgress();
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

                current_progress=progress;
                Log.i(TAG, position + "---" + "getProgressOnActionUp:进度：" + progress);
            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
//                if (current_progress != progress && progress%2==0)
                    Log.i(TAG, position + "---"+ "getProgressOnFinally:进度：" + progress);
            }
        });
        viewHolder.sb_fan.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                mApihHelper.sendCmd(mDatas.get(position),"1");
                Log.i(TAG, position + "-" + view.getId() + "选中：" + isChecked);
                Toast.makeText(mContext, view.getId() + "选中：" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.sb_water.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                Log.i(TAG, position + "-" + view.getId() + "选中：" + isChecked);
                Toast.makeText(mContext, view.getId() + "选中：" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.sb_spray.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                Log.i(TAG, position + "-" + view.getId() + "选中：" + isChecked);
                Toast.makeText(mContext, view.getId() + "选中：" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.sb_heat.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                Log.i(TAG, position + "-" + view.getId() + "选中：" + isChecked);
                Toast.makeText(mContext, view.getId() + "选中：" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView tv_id;
        SwitchButton sb_fan, sb_water, sb_spray, sb_heat;
        BubbleSeekBar sb_beam;
    }
}
