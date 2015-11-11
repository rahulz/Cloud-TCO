package com.cloudbia.cloudcompare.activity;

import android.app.LoaderManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListView;

import com.cloudbia.cloudcompare.R;
import com.cloudbia.cloudcompare.adapters.ProviderListAdapter;
import com.cloudbia.cloudcompare.contentProvider.LocalDbProvider;
import com.cloudbia.cloudcompare.contentProvider.Plan;

import java.util.ArrayList;

public class ComplareDisplayActivity extends FragmentActivity{
    private ListView compareListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complare_display);
        compareListView = (ListView) findViewById(R.id.comparedisplaylistview);
        Bundle bundle= getIntent().getExtras();
        ArrayList<Plan> planArrayList = getPlans(bundle.getString(Plan.CPU),bundle.getString(Plan.MEMORY),bundle.getString(Plan.HDD),bundle.getString(Plan.OS),bundle.getInt(Plan.SUBSCRIPTION));
        ProviderListAdapter providerListAdapter= new ProviderListAdapter(planArrayList,this);
        compareListView.setAdapter(providerListAdapter);
    }

    private ArrayList<Plan> getPlans(String cpu, String memory,String hdd,String os,int duration) {
        Log.i("cpu", cpu);
        Log.i("memmory", memory);
        Log.i("hdd",hdd);
        Log.i("os",os);
        Log.i("duration",duration+"");
        ArrayList<Plan> plans=new ArrayList<Plan>();
        String providers[]={"Google","Amazon EC2","Softlayer","HP","Azure"};
        Cursor resultSet;
        for (String provider : providers) {
//            Log.i("Provider", provider);
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
                    resultSet.close();
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
                    resultSet.close();
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
                    resultSet.close();
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
                    resultSet.close();
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
                    resultSet.close();
                } else
                    Log.e("ERROR","NO DATA");
            }
        }
        return plans;
    }



}
