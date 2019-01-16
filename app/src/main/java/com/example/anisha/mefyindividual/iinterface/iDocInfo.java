package com.example.anisha.mefyindividual.iinterface;

import android.content.Context;

import com.example.anisha.mefyindividual.model.DocDataModel;


public interface iDocInfo {

    void setDocInfo(DocDataModel docDataModel, Context context);
    void getDocInfo(String id, Context context);
    void docInfo(DocDataModel docDataModel, Context context);
}
