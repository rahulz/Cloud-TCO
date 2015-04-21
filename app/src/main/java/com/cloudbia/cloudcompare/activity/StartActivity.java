package com.cloudbia.cloudcompare.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cloudbia.cloudcompare.R;

public class StartActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void CloudActions(View view) {
        Intent intent = new Intent(getApplicationContext(),CompareCloudActivity.class);
        Intent compareintent = new Intent(getApplicationContext(),PublicCloudActivty.class);
        switch (view.getId()){
            case R.id.publiccloudImage:
                startActivity(compareintent);
                overridePendingTransition(R.anim.slidein_anim,R.anim.fadout_anim);

                break;
            case R.id.comparecloudImage:

                startActivity(intent);
                overridePendingTransition(R.anim.slidetop_anim,R.anim.fadout_anim);

                break;

        }
    }
}
