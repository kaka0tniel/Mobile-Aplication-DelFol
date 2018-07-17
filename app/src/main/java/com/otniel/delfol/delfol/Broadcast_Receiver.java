package com.otniel.delfol.delfol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Broadcast_Receiver extends AppCompatActivity {

    private TextView batteryLevel;
    SharedPref sharedpref;
    private ProgressBar mBatteryLevelProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.darktheme);
        }
        else  setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast__receiver);
        batteryLevel = (TextView) findViewById(R.id.batteryLevel);
        mBatteryLevelProgress = (ProgressBar) findViewById(R.id.progressBar);
//Kode untuk meregister event yang akan ditangkap oleh BroadcastReceiver
//Bisa juga diset di AndroidManifest.xml file
        this.registerReceiver(this.myBatteryReceiver, new
                IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
    private BroadcastReceiver myBatteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int lvl = intent.getIntExtra("level", 0);
            batteryLevel.setText("Battery anda saat ini : " +String.valueOf(lvl) + "%");
            mBatteryLevelProgress.setProgress(lvl);
            if (lvl == 100) {
                Toast.makeText(context, "Battery Full.",
                        Toast.LENGTH_LONG).show();
            }
        }
    };
}
