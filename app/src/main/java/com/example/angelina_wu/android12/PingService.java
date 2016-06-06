package com.example.angelina_wu.android12;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class PingService extends IntentService {
    public static final int DEFAULT_TIMER_DURATION = 10000;
    public static final String ACTION_SNOOZE = "com.example.angelina_wu.android12.ACTION_SNOOZE";
    public static final String ACTION_DISMISS = "com.example.angelina_wu.android12.ACTION_DISMISS";
    public static final String ACTION_PING = "com.example.angelina_wu.android12.ACTION_PING";
    public static final String EXTRA_MESSAGE= "com.example.angelina_wu.android12.EXTRA_MESSAGE";
    public static final String EXTRA_TIMER = "com.example.angelina_wu.android12.EXTRA_TIMER";
    public static final int NOTIFICATION_ID = 001;
    public static final String DEBUG_TAG = "tag";

    private String mMessage;
    private int mMillis;

    public PingService() {
        super("com.example.angelina_wu.android12");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        mMessage = intent.getStringExtra(EXTRA_MESSAGE);
        mMillis = intent.getIntExtra(EXTRA_TIMER,DEFAULT_TIMER_DURATION);

        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        switch(action){
            case ACTION_PING :
                action ();
                break;
            case ACTION_SNOOZE :
                manager.cancel(NOTIFICATION_ID);
                action();
                break;
            case ACTION_DISMISS:
                manager.cancel(NOTIFICATION_ID);
                break;
            default:
                break;
        }
    }

    protected void action (){

        NotificationManager notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Intent dismissIntent = new Intent(this, PingService.class);
        dismissIntent.setAction(ACTION_DISMISS);
        PendingIntent pendingIntent_Dismiss = PendingIntent.getService(this, 0, dismissIntent, 0);

        Intent snoozeIntent = new Intent(this, PingService.class);
        snoozeIntent.setAction(ACTION_SNOOZE);
        snoozeIntent.putExtra(EXTRA_MESSAGE, mMessage);
        snoozeIntent.putExtra(EXTRA_TIMER, mMillis);
        PendingIntent pendingIntent_Snooze = PendingIntent.getService(this, 0, snoozeIntent, 0);


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(getString(R.string.notification_title))
                        .setContentText(getString(R.string.notification_text))
                        .setDefaults(Notification.DEFAULT_LIGHTS)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(mMessage))
                        .addAction (R.drawable.icon_dismiss, getString(R.string.dismiss), pendingIntent_Dismiss)
                        .addAction (R.drawable.icon_snooze, getString(R.string.snooze), pendingIntent_Snooze);

        Intent resultIntent = new Intent(this, ResultActivity.class);
        resultIntent.putExtra(EXTRA_MESSAGE, mMessage);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        builder.setContentIntent(resultPendingIntent);

        try {
            Thread.sleep(mMillis);

        } catch (InterruptedException e) {
            Log.d(DEBUG_TAG, "Sleep failure");
        }

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
