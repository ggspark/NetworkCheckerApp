package com.ggspark.networktest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scheduleStatusCheck(30);
    }

    private void scheduleStatusCheck(final int seconds) {
        if (seconds > 0) {
            Intent intent = new Intent(this, CheckIntentService.class);
            PendingIntent pending = PendingIntent.getService(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), seconds * 1000, pending);
        }
    }



}
