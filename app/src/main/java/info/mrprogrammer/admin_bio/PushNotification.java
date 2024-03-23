package info.mrprogrammer.admin_bio;


import static info.mrprogrammer.admin_bio.ShowNotification.showNotification;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.jetbrains.annotations.NotNull;

public class PushNotification extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull @NotNull String s) {
        super.onNewToken(s);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull @NotNull RemoteMessage remoteMessage) {
        String title=remoteMessage.getNotification().getTitle();
        String msg=remoteMessage.getNotification().getBody();
        showNotification(getApplicationContext(),title,msg);
        super.onMessageReceived(remoteMessage);

    }


}
