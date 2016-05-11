package com.ggspark.networktest;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;

/**
 * @author Gaurav Gupta <gaurav@thegauravgupta.com>
 * @since 11/May/2016
 */


public class CheckIntentService extends IntentService {

    public CheckIntentService() {
        super("CheckIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (!haveNetworkConnection()) {
            sendNotification("Sim Card is not working properly");
        } else {
            removeNotification();
        }
    }


    private void sendNotification(String message) {
        final NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("NetworkChecker")
                        .setContentText(message)
                        .setTicker(message)
                        .setOnlyAlertOnce(false)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setColor(0x000)
                        .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                        .setAutoCancel(false);

        final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(873, builder.build());
    }

    private void removeNotification() {
        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.cancel(873);
    }

    private boolean haveNetworkConnection() {
        int simState = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).getSimState();
        switch (simState) {
            case (TelephonyManager.SIM_STATE_ABSENT):
                System.out.println("*******************************************Sim State absent******************************");
                break;
            case (TelephonyManager.SIM_STATE_NETWORK_LOCKED):
                System.out.println("*******************************************SIM_STATE_NETWORK_LOCKED******************************");
                break;
            case (TelephonyManager.SIM_STATE_PIN_REQUIRED):
                System.out.println("*******************************************SIM_STATE_PIN_REQUIRED******************************");
                break;
            case (TelephonyManager.SIM_STATE_PUK_REQUIRED):
                System.out.println("*******************************************SIM_STATE_PUK_REQUIRED******************************");
                break;
            case (TelephonyManager.SIM_STATE_UNKNOWN):
                System.out.println("*******************************************SIM_STATE_UNKNOWN******************************");
                break;
            case (TelephonyManager.SIM_STATE_READY):
                return true;
            default:
                return false;
        }
        return false;
    }
}
