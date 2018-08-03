package com.accuragroup.eg.VirAdmin;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.accuragroup.eg.VirAdmin.activities.DashBoardActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Apex on 3/21/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Intent intent =new Intent(this, DashBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationsBuilder =new NotificationCompat.Builder(this);
        notificationsBuilder.setContentTitle("VIR Business");
        notificationsBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationsBuilder.setAutoCancel(true);
        notificationsBuilder.setSmallIcon(R.drawable.trensparent_icon);
        notificationsBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_viradmin));
        notificationsBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationsBuilder.build());
    }
}
