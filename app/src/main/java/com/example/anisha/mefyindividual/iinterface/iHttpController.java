package com.example.anisha.mefyindividual.iinterface;

import android.content.Context;

import com.example.anisha.mefyindividual.model.CallModel;
import com.example.anisha.mefyindividual.model.RoomModel;


public interface iHttpController {
    void placeCall(CallModel callModel, Context context, String operationFlag);
    void roomCreation(RoomModel roomModel,Context context,String operationFlag);
    void twilioToken(Context context,String operationFlag,String userName);
}
