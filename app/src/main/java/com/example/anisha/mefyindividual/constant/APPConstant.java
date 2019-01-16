package com.example.anisha.mefyindividual.constant;

import com.twilio.video.G722Codec;
import com.twilio.video.H264Codec;
import com.twilio.video.IsacCodec;
import com.twilio.video.OpusCodec;
import com.twilio.video.PcmaCodec;
import com.twilio.video.PcmuCodec;
import com.twilio.video.Vp8Codec;
import com.twilio.video.Vp9Codec;

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
    public static final String PREF_SENDER_MAX_AUDIO_BITRATE = "sender_max_audio_bitrate";
    public static final String PREF_SENDER_MAX_AUDIO_BITRATE_DEFAULT = "0";
    public static final String PREF_SENDER_MAX_VIDEO_BITRATE = "sender_max_video_bitrate";
    public static final String PREF_SENDER_MAX_VIDEO_BITRATE_DEFAULT = "0";
    public static final String PREF_VP8_SIMULCAST = "vp8_simulcast";
    public static final boolean PREF_VP8_SIMULCAST_DEFAULT = false;

    private static final String[] VIDEO_CODEC_NAMES = new String[] {
            Vp8Codec.NAME, H264Codec.NAME, Vp9Codec.NAME
    };

    private static final String[] AUDIO_CODEC_NAMES = new String[] {
            IsacCodec.NAME, OpusCodec.NAME, PcmaCodec.NAME, PcmuCodec.NAME, G722Codec.NAME
    };

}
