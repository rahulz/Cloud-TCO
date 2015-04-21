package com.cloudbia.cloudcompare.activity;

import com.cloudbia.cloudcompare.contentProvider.Plan;
import com.cloudbia.cloudcompare.R;
import com.cloudbia.cloudcompare.contentProvider.LocalDbProvider;


import android.support.v4.app.FragmentActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class PublicCloudActivty extends FragmentActivity {

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publiccloud);
        TextView tvCPU=(TextView) findViewById(R.id.TextViewValue1);
        TextView tv2=(TextView) findViewById(R.id.TextViewValue2);
        TextView tv3=(TextView) findViewById(R.id.TextViewValue3);
        TextView tv4=(TextView) findViewById(R.id.TextViewValue4);
        final SeekBar seekBarCPU=(SeekBar)findViewById(R.id.seekBar_CPU);
        SeekBar seekBar2=(SeekBar)findViewById(R.id.SeekBar2);
        SeekBar seekBar3=(SeekBar)findViewById(R.id.SeekBar3);
        SeekBar seekBar4=(SeekBar)findViewById(R.id.SeekBar4);
        seekBarCPU.setTag(tvCPU);
        seekBarCPU.setProgress(0);
        seekBarCPU.incrementProgressBy(8);
        seekBarCPU.setMax(64);

        seekBar2.setTag(tv2);
        seekBar3.setTag(tv3);
        seekBar4.setTag(tv4);

        SeekBar.OnSeekBarChangeListener ls=new SeekBar.OnSeekBarChangeListener()
        {

            public void onStopTrackingTouch(SeekBar bar)
            {
                int value = bar.getProgress(); // the value of the seekBar progress
            }

            public void onStartTrackingTouch(SeekBar bar)
            {

            }

            public void onProgressChanged(SeekBar bar,
                                          int progress, boolean paramBoolean)
            {
//                progress = progress / 8;
//                progress = progress * 8;
                progress = ((int)Math.round(progress/8))*8;
                seekBarCPU.setProgress(progress);
                //textview.setText(progress + "");
                TextView tv=(TextView)  bar.getTag();
                tv.setText(String.valueOf(progress));
//                tv.setText("" + paramInt + "%"); // here in textView the percent will be shown
            }
        };
        //Log.e("TEST","TEST");
        seekBarCPU.setOnSeekBarChangeListener(ls);


        seekBar2.setOnSeekBarChangeListener(ls);
        seekBar3.setOnSeekBarChangeListener(ls);
        seekBar4.setOnSeekBarChangeListener(ls);

        String[] projection=new String[]{
                Plan.PLAN_NAME,
                Plan.MEMORY,
                Plan.PROVIDER
        };
        //SELECT MIN(price) FROM data where provider='"+serviceProvider+"' AND cpu>="+cpu+" AND memory>="+memory+" AND hdd>="+hdd+" AND os='"+os
        //String[] s={cpu,memory,hdd,os};
        String[] s={"1","2","10","linux"};

        Plan p=getPlans("1","2","10","linux", 1).get(0);
        Log.e("-----------","-------------");
        Log.e("Provider",p.serviceProvider.toString());
        Log.e("Plan Name",p.planName.toString());
        Log.e("CPU",p.cpu.toString());
        Log.e("Memory",p.memory.toString());
        Log.e("HDD",p.hdd.toString());
        Log.e("OS",p.os.toString());
        Log.e("Duration",p.duration.toString());
        Log.e("Price",p.price.toString());

        p=getPlans("1","2","10","linux", 1).get(1);
        Log.e("---------","-------");
        Log.e("Provider",p.serviceProvider.toString());
        Log.e("Plan Name",p.planName.toString());
        Log.e("CPU",p.cpu.toString());
        Log.e("Memory",p.memory.toString());
        Log.e("HDD",p.hdd.toString());
        Log.e("OS",p.os.toString());
        Log.e("Duration",p.duration.toString());
        Log.e("Price",p.price.toString());

        p=getPlans("1","2","10","linux", 1).get(2);
        Log.e("-------------","---------------");
        Log.e("Provider",p.serviceProvider.toString());
        Log.e("Plan Name",p.planName.toString());
        Log.e("CPU",p.cpu.toString());
        Log.e("Memory",p.memory.toString());
        Log.e("HDD",p.hdd.toString());
        Log.e("OS",p.os.toString());
        Log.e("Duration",p.duration.toString());
        Log.e("Price",p.price.toString());

        p=getPlans("1","2","10","linux", 1).get(3);
        Log.e("----------------","--------------");
        Log.e("Provider",p.serviceProvider.toString());
        Log.e("Plan Name",p.planName.toString());
        Log.e("CPU",p.cpu.toString());
        Log.e("Memory",p.memory.toString());
        Log.e("HDD",p.hdd.toString());
        Log.e("OS",p.os.toString());
        Log.e("Duration",p.duration.toString());
        Log.e("Price",p.price.toString());

    }

    public ArrayList<Plan> getPlans(String cpu, String memory,String hdd,String os,int duration) {
        ArrayList<Plan> plans=new ArrayList<Plan>();
        String providers[]={"Google","Amazon EC2","Softlayer","HP","Azure"};
        Cursor resultSet = null;
        for (String provider : providers) {
            Log.i("Provider", provider);
            if(provider.matches("Google")){
                String selectionArgs[]={provider,cpu,memory,provider,cpu,memory};
                resultSet=getContentResolver().query(LocalDbProvider.PLAN_URI_RAW_GOOGLE, null, null, selectionArgs, null);
                if(resultSet.moveToFirst()){
                    String planName=resultSet.getString(resultSet.getColumnIndex(Plan.PLAN_NAME));
                    double memory2=resultSet.getDouble(resultSet.getColumnIndex(Plan.MEMORY));
                    int cpu2=resultSet.getInt(resultSet.getColumnIndex(Plan.CPU));
                    int hdd2=Integer.parseInt(hdd);
                    double price=resultSet.getDouble(resultSet.getColumnIndex(Plan.PRICE))*24*365*duration;
                    if(os.matches("linux"))
                        price=price+(Plan.PLAN_GOOGLE_OS_PRICE_LINUX*24*365*duration);
                    else
                        price=price+(Plan.PLAN_GOOGLE_OS_PRICE_WINDOWS*24*365*duration);
                    price=price+(12*duration*hdd2*Plan.PLAN_GOOGLE_HDD_PRICE);
                    Plan p=new Plan(provider, planName, os, cpu2, memory2, hdd2, price, duration);
                    plans.add(p);
                } else
                    Log.e("ERROR","NO DATA");
            } else if(provider.matches("Amazon EC2")){
                String selectionArgs[]={provider,cpu,memory,hdd,os,provider,cpu,memory,hdd,os};
                resultSet=getContentResolver().query(LocalDbProvider.PLAN_URI_RAW_AMAZON, null, null, selectionArgs, null);
                if(resultSet.moveToFirst()){
                    String planName=resultSet.getString(resultSet.getColumnIndex(Plan.PLAN_NAME));
                    double memory2=resultSet.getDouble(resultSet.getColumnIndex(Plan.MEMORY));
                    int cpu2=resultSet.getInt(resultSet.getColumnIndex(Plan.CPU));
                    int hdd2=resultSet.getInt(resultSet.getColumnIndex(Plan.HDD));
                    //int hdd2=Integer.parseInt(hdd);
                    double price=resultSet.getDouble(resultSet.getColumnIndex(Plan.PRICE))*24*365*duration;
                    Plan p=new Plan(provider, planName, os, cpu2, memory2, hdd2, price, duration);
                    plans.add(p);
                } else
                    Log.e("ERROR","NO DATA");
                String[] selectionArgs2={provider,cpu,memory,os,"EBS Only",provider,cpu,memory,os,"EBS Only"};
                resultSet=getContentResolver().query(LocalDbProvider.PLAN_URI_RAW_AMAZON_EBS, null, null, selectionArgs2, null);
                if(resultSet.moveToFirst()){
                    String planName2=resultSet.getString(resultSet.getColumnIndex(Plan.PLAN_NAME));
                    double memory22=resultSet.getDouble(resultSet.getColumnIndex(Plan.MEMORY));
                    int cpu22=resultSet.getInt(resultSet.getColumnIndex(Plan.CPU));
                    int hdd22=Integer.parseInt(hdd);
                    double price2=resultSet.getDouble(resultSet.getColumnIndex(Plan.PRICE))*24*365*duration;
                    price2=price2+(12*duration*hdd22*Plan.PLAN_AMAZON_HDD_PRICE);
                    Plan p=new Plan(provider, planName2, os, cpu22, memory22, hdd22, price2, duration);
                    plans.add(p);
                } else
                    Log.e("ERROR","NO DATA");
            } else if(provider.matches("HP")){
                String selectionArgs[]={provider,cpu,memory,hdd,os,provider,cpu,memory,hdd,os};
                resultSet=getContentResolver().query(LocalDbProvider.PLAN_URI_RAW, null, null, selectionArgs, null);
                if(resultSet.moveToFirst()){
                    String planName=resultSet.getString(resultSet.getColumnIndex(Plan.PLAN_NAME));
                    double memory2=resultSet.getDouble(resultSet.getColumnIndex(Plan.MEMORY));
                    int cpu2=resultSet.getInt(resultSet.getColumnIndex(Plan.CPU));
                    int hdd2=resultSet.getInt(resultSet.getColumnIndex(Plan.HDD));
                    double price=resultSet.getDouble(resultSet.getColumnIndex(Plan.PRICE))*24*365*duration;
                    Plan p=new Plan(provider, planName, os, cpu2, memory2, hdd2, price, duration);
                    plans.add(p);
                } else
                    Log.e("ERROR","NO DATA");
                //Netmagic and Azure have same data pattern
            } else if(provider.matches("Netmagic") || provider.matches("Windows Azure")){
                String selectionArgs[]={provider,cpu,memory,hdd,provider,cpu,memory,hdd};
                resultSet=getContentResolver().query(LocalDbProvider.PLAN_URI_RAW_AZURE, null, null, selectionArgs, null);
                if(resultSet.moveToFirst()){
                    String planName=resultSet.getString(resultSet.getColumnIndex(Plan.PLAN_NAME));
                    double memory2=resultSet.getDouble(resultSet.getColumnIndex(Plan.MEMORY));
                    int cpu2=resultSet.getInt(resultSet.getColumnIndex(Plan.CPU));
                    int hdd2=resultSet.getInt(resultSet.getColumnIndex(Plan.HDD));
                    double price=resultSet.getDouble(resultSet.getColumnIndex(Plan.PRICE))*24*365*duration;
                    Plan p=new Plan(provider, planName, os, cpu2, memory2, hdd2, price, duration);
                    plans.add(p);
                } else
                    Log.e("ERROR","NO DATA");

            }

        }


        return plans;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidetop_anim,R.anim.abc_slide_out_top);
    }

}
