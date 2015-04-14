package com.cloudbia.cloudcompare;

import java.io.IOException;

import com.cloudbia.cloudcompare.contentProvider.LocalDbProvider;


import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;


public class Home extends ActionBarActivity {

	SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView tv1=(TextView) findViewById(R.id.TextViewValue1);
        TextView tv2=(TextView) findViewById(R.id.TextViewValue2);
        TextView tv3=(TextView) findViewById(R.id.TextViewValue3);
        TextView tv4=(TextView) findViewById(R.id.TextViewValue4);        
        SeekBar seekBar1=(SeekBar)findViewById(R.id.SeekBar1);        
        SeekBar seekBar2=(SeekBar)findViewById(R.id.SeekBar2);
        SeekBar seekBar3=(SeekBar)findViewById(R.id.SeekBar3);
        SeekBar seekBar4=(SeekBar)findViewById(R.id.SeekBar4);
        seekBar1.setTag(tv1);
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
                    int paramInt, boolean paramBoolean)
            {
                TextView tv=(TextView)  bar.getTag();
                tv.setText("" + paramInt + "%"); // here in textView the percent will be shown
            }
        };        
        //Log.e("TEST","TEST");
        seekBar1.setOnSeekBarChangeListener(ls);
        seekBar2.setOnSeekBarChangeListener(ls);
        seekBar3.setOnSeekBarChangeListener(ls);
        seekBar4.setOnSeekBarChangeListener(ls);
 /*       MySQLiteHelper myDbHelper=new MySQLiteHelper(this);
        try {
        	 
        	myDbHelper.createDataBase();
 
 	} catch (IOException ioe) {
 
 		throw new Error("Unable to create database");
 
 	}
 
 	try {
 
 		myDbHelper.openDataBase();
 		myDbHelper.getPlan("Google", 5, 2,1, "Windows" ,10);
 
 	}catch(SQLException sqle){
 		sqle.printStackTrace(); 
 	}
 	*/
        String[] projection=new String[]{
        	Plan.PLAN_NAME,
        	Plan.MEMORY,
        	Plan.PROVIDER
        };
        //SELECT MIN(price) FROM data where provider='"+serviceProvider+"' AND cpu>="+cpu+" AND memory>="+memory+" AND hdd>="+hdd+" AND os='"+os
        String[] selectionArgs=new String[]{
        		"Amazon EC2",
        		"1",
				"2",
				"10",
				"linux",
				"Amazon EC2",
				"1",
				"2",
				"10",
				"linux"
				};
        Cursor resultSet=getContentResolver().query(LocalDbProvider.PLAN_URI_RAW, null, null, selectionArgs, null);

/*        		Cursor resultSet=getContentResolver().query(LocalDbProvider.PLAN_URI,null , 
        											    Plan.CPU + ">=? AND "+
        											    Plan.MEMORY + ">=? AND " +
        											    Plan.HDD + ">=? AND " +
        											    Plan.OS + "=? AND " +
        											    Plan.PROVIDER + "=? " +
        											    Plan.PRICE + "=(SELECT MIN(" + Plan.PRICE+") FROM data where " +
        											    Plan.CPU + ">=? AND "+
        											    Plan.MEMORY + ">=? AND " +
        											    Plan.HDD + ">=? AND " +
        											    Plan.OS + "=? AND " +
        											    Plan.PROVIDER + "=?)",
        											    new String[]{
															"1",
															"2",
															"10",
															"linux",
															"Amazon EC2",
															"1",
															"2",
															"10",
															"linux",
															"Amazon EC2"
        													}, null);*/
        Boolean rs=resultSet.moveToFirst();
        Log.e("rs",rs+"");
        if(resultSet.moveToFirst())
    		Log.e("DB",resultSet.getString(resultSet.getColumnIndexOrThrow(Plan.PROVIDER)));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
