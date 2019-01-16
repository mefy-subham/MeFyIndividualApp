package com.example.anisha.mefyindividual.views;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.anisha.mefyindividual.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class CallingUI extends AppCompatActivity {

    private Button _ans,_end;
    private Vibrator vib;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_ui);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // to wake up screen
        PowerManager pm = (PowerManager) getApplicationContext().getSystemService(Context.POWER_SERVICE);
        @SuppressLint("InvalidWakeLockTag") PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakeLock.acquire();



        // to release screen lock
        KeyguardManager keyguardManager = (KeyguardManager) getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("TAG");
        keyguardLock.disableKeyguard();

        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone ringtoneSound = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);
        System.out.println("Main | Play.onClick | ringtone:" +ringtoneSound);
        if (ringtoneSound != null) {
            ringtoneSound.play();
            vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vib.vibrate(500);
        }

        _ans=(Button)findViewById(R.id.btn_answer);
        _end=(Button)findViewById(R.id.btn_decline);

        _ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //---------Call Answer------------------

                if (ringtoneSound != null) {
                    ringtoneSound.stop();
                }
                finish();
            }
        });
        _end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //----------------Call Decline------------

                if (ringtoneSound != null) {
                    ringtoneSound.stop();
                }
                finish();
            }
        });

    }
}
