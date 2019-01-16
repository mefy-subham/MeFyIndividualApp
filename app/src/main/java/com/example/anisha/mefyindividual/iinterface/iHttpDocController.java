package com.example.anisha.mefyindividual.iinterface;

import android.content.Context;

import com.example.anisha.mefyindividual.model.DocDataModel;

public interface iHttpDocController {

    void setDocData(DocDataModel docDataModel, Context context);
    void docData(DocDataModel docDataModel, Context context);
    void getDocData(String phoneNo, Context context);
}
