package com.example.anisha.mefyindividual.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anisha.mefyindividual.AppointmentActivity;
import com.example.anisha.mefyindividual.R;
import com.example.anisha.mefyindividual.constant.APPConstant;
import com.example.anisha.mefyindividual.handler.HttpHandler;
import com.example.anisha.mefyindividual.iinterface.iHttpResultHandler;
import com.example.anisha.mefyindividual.model.CallModel;
import com.example.anisha.mefyindividual.model.RoomModel;
import com.example.anisha.mefyindividual.model.TokenDataModel;

import org.json.JSONObject;

public class ConnectingUI extends AppCompatActivity {

    private Button _decline;
    private String room_name,status,fcm,u_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connecting_ui);
        Intent i = getIntent();
        room_name = i.getExtras().getString("room");
        status = i.getExtras().getString("status");
        fcm = i.getExtras().getString("fcm");
        u_name = i.getExtras().getString("u_name");
        System.out.println("ConnectingUI | room----------->>>>>>>" + room_name);
        System.out.println("ConnectingUI | status----------->>>>>>>" + status);
        System.out.println("ConnectingUI | fcm----------->>>>>>>" + fcm);
        System.out.println("ConnectingUI | u_name----------->>>>>>>" + u_name);
        _decline=(Button)findViewById(R.id.btn_decline);
        _decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //-----------Make The Room Destroy-------------

                CallModel callModel=new CallModel();
                callModel.setUserInfo(u_name);
                callModel.setRoomId(room_name);
                callModel.setCallee_fcmToken(fcm);
                callModel.setStatus(status);
                callModel.setType("decline");
                HttpHandler httpHandler = HttpHandler.getInstance();
                ServerResultHandler serverResultHandler = new ServerResultHandler(ConnectingUI.this);
                httpHandler.set_resultHandler(serverResultHandler);
                httpHandler.placeCall(callModel, ConnectingUI.this, APPConstant.SEND_FCM_NOTIFICATION_OPERATION);
                finish();
            }
        });
    }
    private class ServerResultHandler implements iHttpResultHandler {
        private Context _context;


        public ServerResultHandler(Context context) {
            this._context = context;
        }

        @Override
        public void onSuccess(Object response, String operation_flag) {

            if (operation_flag.equalsIgnoreCase(APPConstant.SEND_FCM_NOTIFICATION_OPERATION)) {

            }

        }

        @Override
        public void onRoom(RoomModel roomModel, String operation_flag) {

        }

        @Override
        public void onToken(TokenDataModel tokenDataModel, String operation_flag) {

        }

        @Override
        public void onCancel(Object response, String operation_flag) {

        }

        @Override
        public void onError(Object response, String operation_flag) {

        }

        @Override
        public void inProgress(Object response, String operation_flag) {

        }
    }

}
