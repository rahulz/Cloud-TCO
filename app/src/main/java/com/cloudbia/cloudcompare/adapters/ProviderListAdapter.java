package com.cloudbia.cloudcompare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloudbia.cloudcompare.R;
import com.cloudbia.cloudcompare.contentProvider.Plan;

import java.util.ArrayList;

/**
 * Created by shakeeb on 12/05/15.
 */
public class ProviderListAdapter extends BaseAdapter {
    private ArrayList<Plan> planList;
    private Context context;
    public ProviderListAdapter(ArrayList<Plan> planList,Context context){
        this.planList=planList;
        this.context= context;
    }
    @Override
    public int getCount() {
        return planList.size();
    }

    @Override
    public Object getItem(int position) {
        return planList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(
                    R.layout.cloudcompare_listitem, null);
        }
        Plan plan=planList.get(position);
        TextView provider= (TextView) convertView.findViewById(R.id.cloud_provider);
        TextView ram= (TextView) convertView.findViewById(R.id.cloud_ram);
        TextView hdd= (TextView) convertView.findViewById(R.id.cloud_hdd);
        TextView subsc= (TextView) convertView.findViewById(R.id.cloud_subscription);
        TextView os= (TextView) convertView.findViewById(R.id.cloud_os);
        provider.setText(plan.serviceProvider);
        ram.setText(plan.memory +"GM");
        hdd.setText(plan.hdd + "GB");
        subsc.setText(plan.duration+" Years");
        os.setText(plan.os);
        return convertView;
    }
}
