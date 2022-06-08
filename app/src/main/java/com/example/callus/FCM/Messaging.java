package com.example.callus.FCM;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import com.example.callus.ReusableFunctions.Constants;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Messaging extends FirebaseMessagingService {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        String title = message.getNotification().getTitle();
        String text = message.getNotification().getBody();
        NotificationChannel channel = new NotificationChannel(Constants.CHANNEL_ID,"first notificarion",
                NotificationManager.IMPORTANCE_HIGH);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder builder = new Notification.Builder(this,Constants.CHANNEL_ID)
                .setContentTitle(title).setContentText(text).setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(1,builder.build());

    }
}
