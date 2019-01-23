package com.example.anisha.mefyindividual.handler;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.example.anisha.mefyindividual.controller.HttpController;
import com.example.anisha.mefyindividual.iinterface.iHttpController;
import com.example.anisha.mefyindividual.iinterface.iHttpResultHandler;
import com.example.anisha.mefyindividual.model.CallIdModel;
import com.example.anisha.mefyindividual.model.CallModel;
import com.example.anisha.mefyindividual.model.RoomModel;


public class HttpHandler implements iHttpController
{

    private static HttpHandler _httpHandler;
    private RequestQueue _requestQueue;
    private HttpController _httpController;
    private iHttpResultHandler _resultHandler;

    public static final HttpHandler getInstance()
    {
        if(_httpHandler == null)
        {
            _httpHandler = new HttpHandler();
        }

        return _httpHandler;
    }

    public void goGarbge()
    {
        _httpController = null;
    }


    @Override
    public void placeCall(CallModel callModel, Context context, String operationFlag) {
        //System.out.println("HttpHandler | placeCall | callModel: "+callModel.getStatus());
        _httpController = HttpController.getInstance();
        _httpController.set_resultHandler(_resultHandler);
        _httpController.placeCall(callModel,context, operationFlag);
    }

    @Override
    public void saveCall(CallIdModel callIdModel, Context context, String operationFlag) {

        _httpController = HttpController.getInstance();
        _httpController.set_resultHandler(_resultHandler);
        _httpController.saveCall(callIdModel,context, operationFlag);
    }

    @Override
    public void roomCreation(RoomModel roomModel, Context context, String operationFlag) {
        _httpController = HttpController.getInstance();
        _httpController.set_resultHandler(_resultHandler);
        _httpController.roomCreation(roomModel,context, operationFlag);
    }

    @Override
    public void twilioToken(Context context,String operationFlag,String userName,String roomName) {
        _httpController = HttpController.getInstance();
        _httpController.set_resultHandler(_resultHandler);
        _httpController.twilioToken(context,operationFlag,userName,roomName);
    }

    public void set_resultHandler(iHttpResultHandler _resultHandler) {
        this._resultHandler = _resultHandler;
    }
}
