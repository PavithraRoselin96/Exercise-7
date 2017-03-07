package com.example.notificationmanager;
	 


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button; 

	public class MainActivity extends Activity {

		   private NotificationManager myNotificationManager;

		   private int notificationIdOne = 111;
		   private int notificationIdTwo = 112;

		   private int numMessagesOne = 0;

		   private int numMessagesTwo = 0;

		 

		   protected void onCreate(Bundle savedInstanceState) {

		      super.onCreate(savedInstanceState);

		      setContentView(R.layout.activity_main);

		 

		      Button notOneBtn = (Button) findViewById(R.id.notificationOne);

		      notOneBtn.setOnClickListener(new View.OnClickListener() {

		         public void onClick(View view) {

		            displayNotificationOne();

		         }

		      });

		    
		      Button notTwoBtn = (Button) findViewById(R.id.notificationTwo);

		      notTwoBtn.setOnClickListener(new View.OnClickListener() {

		         public void onClick(View view) {

		            displayNotificationTwo();

		         }

		      });
	   }
		   protected void displayNotificationOne() {

		      NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(this); 

		      mBuilder.setContentTitle("New Message with explicit intent");

		      mBuilder.setContentText("New message from javacodegeeks received");

		      mBuilder.setTicker("Explicit: New Message Received!");

		      mBuilder.setSmallIcon(R.drawable.ic_launcher);
		      mBuilder.setNumber(++numMessagesOne);
		      Intent resultIntent = new Intent(this, Notificationone.class);

		      resultIntent.putExtra("notificationId", notificationIdOne);
		      TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		      stackBuilder.addParentStack(Notificationone.class);
		      stackBuilder.addNextIntent(resultIntent);

		      PendingIntent resultPendingIntent =

		         stackBuilder.getPendingIntent(0,PendingIntent.FLAG_ONE_SHOT );
		      mBuilder.setContentIntent(resultPendingIntent);

		      myNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		      myNotificationManager.notify(notificationIdOne, mBuilder.build());
		   }

		   protected void displayNotificationTwo() {
	

		      NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(this); 

		      mBuilder.setContentTitle("New Message with implicit intent");
		      mBuilder.setContentText("New message from javacodegeeks received...");

		      mBuilder.setTicker("Implicit: New Message Received!");

		      mBuilder.setSmallIcon(R.drawable.ic_launcher);
		      NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
		       String[] events = new String[3];
		       events[0] = new String("1) Message from whatsapp");
		       events[1] = new String("2) big view Notification");
		       events[2] = new String("3) from Roselin!");
		       inboxStyle.setBigContentTitle("More Details:");
	       for (int i=0; i < events.length; i++) {
	          inboxStyle.addLine(events[i]);
		       }
		       mBuilder.setStyle(inboxStyle);
		      mBuilder.setNumber(++numMessagesTwo);
		      mBuilder.setAutoCancel(true);
		      Intent resultIntent = new Intent("com.example.javacodegeeks.TEL_INTENT",
		              Uri.parse("tel:123456789"));
		      resultIntent.putExtra("from", "javacodegeeks");
		      TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		      stackBuilder.addParentStack(NotificationTwo.class);
		      stackBuilder.addNextIntent(resultIntent);
		      PendingIntent resultPendingIntent =
		         stackBuilder.getPendingIntent(
		            0,PendingIntent.FLAG_ONE_SHOT
		         );

		      mBuilder.setContentIntent(resultPendingIntent);
	      myNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		 
	      myNotificationManager.notify(notificationIdTwo, mBuilder.build());  

		 

		   }

		}