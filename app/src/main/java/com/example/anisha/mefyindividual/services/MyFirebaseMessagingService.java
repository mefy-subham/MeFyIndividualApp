package com.example.anisha.mefyindividual.services;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.anisha.mefyindividual.constant.APPConstant;
import com.example.anisha.mefyindividual.controller.UiController;
import com.example.anisha.mefyindividual.handler.UserHandler;
import com.example.anisha.mefyindividual.manager.SharedPreferenceManager;
import com.example.anisha.mefyindividual.model.UserDataModel;
import com.example.anisha.mefyindividual.views.CallingUI;
import com.example.anisha.mefyindividual.views.VideoActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;



public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    Activity context;

    @Override
    public void onCreate() {
        System.out.println("Service Has been Created");
        super.onCreate();
    }
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        System.out.println("MessageReceived:" +remoteMessage);
         // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        System.out.println("RemoteMessage | 1");
        System.out.println("RemoteMessage | Data Size: " +remoteMessage.getData().size());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            System.out.println("RemoteMessage | remoteMessage.getData(): "+remoteMessage.getData());
            String name = remoteMessage.getData().get("userInfo").toString();
            String room = remoteMessage.getData().get("roomId").toString();
            System.out.println("MyFirebaseMessagingService | remoteMessage.getData() | Name: "+name);
            System.out.println("MyFirebaseMessagingService | remoteMessage.getData() | Room: "+room);
            String type = remoteMessage.getData().get(APPConstant.type);
            if(remoteMessage.getData().get("type").toString().equalsIgnoreCase("decline")){
                System.out.println("MyFirebaseMessagingService | decline | :"+ remoteMessage.getData().get("type").toString());
                UiController uiController=UiController.getInstance();
                uiController.notifyObservers(remoteMessage.getData().get("type").toString());
            }else {

                System.out.println("RemoteMessage | remoteMessage.getData(): " + remoteMessage.getData());

                Intent intent = new Intent(this, VideoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(APPConstant.caller_fcmToken, remoteMessage.getData().get(APPConstant.caller_fcmToken).toString());
                intent.putExtra(APPConstant.caller_image_url, remoteMessage.getData().get(APPConstant.caller_image_url).toString());
                intent.putExtra(APPConstant.recording_url, remoteMessage.getData().get(APPConstant.recording_url).toString());
                intent.putExtra(APPConstant.userInfo, remoteMessage.getData().get(APPConstant.userInfo).toString());
                intent.putExtra(APPConstant.roomId, remoteMessage.getData().get(APPConstant.roomId).toString());
                intent.putExtra(APPConstant.type, remoteMessage.getData().get(APPConstant.roomId).toString());
                intent.putExtra(APPConstant.status, remoteMessage.getData().get(APPConstant.roomId).toString());
                getApplicationContext().startActivity(intent);

            }



        }


    }
    // [END receive_message]


    // [START on_new_token]

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        System.out.println("onNewToken | Refreshed token:" + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }
    // [END on_new_token]

    /**
     * Schedule a job using FirebaseJobDispatcher.
     */


    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.

        SharedPreferenceManager.setFcmTokenSharedPreference(this,token);
        System.out.println("FireBase | sendRegistrationToServer | token:" + token);

        if(UserHandler.getInstance().getUserData() != null)
        {
            UserDataModel userDataModel = UserHandler.getInstance().getUserData();
            userDataModel.setMobiletoken(token);
            UserHandler.getInstance().setUserData(userDataModel);
            UserHandler.getInstance().updateUserInfo(userDataModel,this);
        }else
        {
            UserDataModel userDataModel = new UserDataModel();
            userDataModel.setMobiletoken(token);
            UserHandler.getInstance().setUserData(userDataModel);
            UserHandler.getInstance().updateUserInfo(userDataModel,this);
        }
    }

    @Override
    public void onDestroy() {
        System.out.println("Service has been Destroyed");
        //super.onDestroy();
    }
}