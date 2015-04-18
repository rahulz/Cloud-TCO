package com.cloudbia.cloudcompare;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
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
	
	public void showActivity(View view) {
		Intent i;
		switch (view.getId()) {
		case R.id.buttonPublic:
			i=new Intent(this, PublicCloudsActivity.class);
			startActivity(i);
			break;
		case R.id.buttonPrivateVsPublic:
			i=new Intent(this, PrivateVsPublicActivity.class);
			startActivity(i);
			break;
		default:
			break;
		}
	}
	
	
}
