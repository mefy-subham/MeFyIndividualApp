package com.example.anisha.mefyindividual.constant;

/**
 * Created by root on 17/7/18.
 */

public class APIConstant {
    //public static final String SEND_FCM_NOTIFICATION = "https://us-central1-frbexp.cloudfunctions.net/sendFCMNotification";

    public static final String SEND_FCM_NOTIFICATION = "https://us-central1-mefy-1c490.cloudfunctions.net/sendFCMNotification";
    public static final String CREATE_ROOM = "http://ec2-13-232-207-92.ap-south-1.compute.amazonaws.com:5023/api/User/joinroom";
    public static final String TWILIO_TOKEN = "http://ec2-13-232-207-92.ap-south-1.compute.amazonaws.com:5023/api/User/twilloToken?username=";
    public static final String CALL_HISTORY = "http://ec2-13-232-207-92.ap-south-1.compute.amazonaws.com:5023/api/callhistory/savecall";

    public static final String SEND_FCM_TOKEN = "http://ec2-13-232-207-92.ap-south-1.compute.amazonaws.com:5023/api/individual/profile/2ab9b6fa-89bb-4ed5-ab24-98f9b4873e3d";
    public static final String PARAM_SET_FCM = "mobiletoken";
    public static final String FCMTOKEN = "fcmToken";
    public static final String ROOM_NAME = "roomId";
    public static final String USER_INFO = "userInfo";
    public static final String TYPE = "type";
    public static final String STATUS = "status";

    //Room Creation
    public static final String NAME = "name";

}
