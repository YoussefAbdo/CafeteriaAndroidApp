package com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.Activities.MenuItemInterface;
import com.example.asucomputerengineeringteam.finalcafeteriaandroidmobileapp2017.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Esraa Hosny on 6/26/2017.
 */

public class MessagingService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMessageService";


    @Override

    public void onMessageReceived(RemoteMessage remoteMessage) {
        // super.onMessageReceived(remoteMessage);

//displaying data in log

        //it is optional

        Log.d(TAG, "From " + remoteMessage.getFrom());

        Log.d(TAG, "Notification message body " + remoteMessage.getNotification().getBody());
        Intent intent = new Intent(this, MenuItemInterface.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("New Message");

        builder.setContentText(remoteMessage.getNotification().getBody());

        builder.setAutoCancel(true);

        builder.setSmallIcon(R.drawable.app_logo_final);

        builder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, builder.build());


        //calling method to generate notification

        // sendNotification(remoteMessage.getNotification().getBody());

    }

   /* //this method to generate push notification

 private void sendNotification(String messageBody) {

    //main activity intent rigstration

  Intent intent = new Intent(this, SMSActivity.class);

     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

   PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);


  //take notification sound

  Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        //generate the notification

   NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentTitle("firebase push notification");
        notificationBuilder.setContentText(messageBody);

  notificationBuilder.setAutoCancel(true);

 notificationBuilder.setSound(defaultSoundUri);

   notificationBuilder.setContentIntent(pendingIntent);


    NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

  //create push notifiation

 notificationManager.notify(0,notificationBuilder.build());*/
}


