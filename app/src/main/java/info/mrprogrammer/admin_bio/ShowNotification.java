package info.mrprogrammer.admin_bio;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class ShowNotification {


    static public  void showNotification(Context context, String title, String msg) {
        String ID="HEADS_UP_NOTIFICATION";
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel= new NotificationChannel(ID,"Heads Up Notification",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
