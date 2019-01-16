package com.example.anisha.mefyindividual.iinterface;

import com.example.anisha.mefyindividual.model.TokenDataModel;

import org.json.JSONObject;

/**
 * Created by root on 18/7/18.
 */

public interface iHttpResultHandler
{
    void onSuccess(Object response, String operation_flag);
    void onToken(TokenDataModel tokenDataModel, String operation_flag);
    void onCancel(Object response, String operation_flag);
    void onError(Object response, String operation_flag);
    void inProgress(Object response, String operation_flag);
}
