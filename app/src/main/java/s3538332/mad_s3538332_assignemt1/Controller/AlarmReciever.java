package s3538332.mad_s3538332_assignemt1.Controller;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by tamlam on 8/10/17.
 */

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context)
                // Set Icon
                // Set Ticker Message
                .setTicker("NA")
                // Set Title
                .setContentTitle("notification")
                // Set Text
                .setContentText("text")
                // Add an Action Button below Notification
                // Set PendingIntent into Notification
                // Dismiss Notification
                .setAutoCancel(true);

        NotificationManager notificationmanager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());
    }
}
