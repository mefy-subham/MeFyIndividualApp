package com.example.anisha.mefyindividual.constant;

import com.twilio.video.OpusCodec;
import com.twilio.video.Vp8Codec;

public class APPConstant {
    public static final String SEND_FCM_NOTIFICATION_OPERATION = "send_fcm_notification_operation";
    public static final String ROOM_CREATION_OPERATION = "room_creation_operation";
    public static final String TWILIO_TOKEN_OPERATION = "twilio_token_operation";

    public static final String USER_FCM_TOKEN = "USER_FCM_TOKEN";
    public static final String GET_USER_UPDATED_INFO = "";
    public static final String PREF_AUDIO_CODEC = "audio_codec";
    public static final String PREF_AUDIO_CODEC_DEFAULT = OpusCodec.NAME;
    public static final String PREF_VIDEO_CODEC = "video_codec";
    public static final String PREF_VIDEO_CODEC_DEFAULT = Vp8Codec.NAME;
    public static final String PREF_VP8_SIMULCAST = "vp8_simulcast";
    public static final boolean PREF_VP8_SIMULCAST_DEFAULT = false;

}
