package com.example.anisha.mefyindividual.handler;

import android.content.Context;

import com.example.anisha.mefyindividual.controller.HttpDocController;
import com.example.anisha.mefyindividual.iinterface.iDocInfo;
import com.example.anisha.mefyindividual.iinterface.iHttpResultHandler;
import com.example.anisha.mefyindividual.model.DocDataModel;

public class DocHandler implements iDocInfo {

    private static DocHandler _docHandler;
    private DocDataModel docDataModel ;
    private iHttpResultHandler _iHttpResultHandler;
    private HttpDocController _httpDocController;

    public static DocHandler getInstance()
    {
        if(_docHandler == null)
            _docHandler = new DocHandler();

        return _docHandler;
    }

    public  void  gogarbage()
    {
        if (_docHandler != null)
        {
            _docHandler = null;
        }
    }

    public void setDocDataModel(DocDataModel docDataModel) {
        this.docDataModel = docDataModel;
    }

    public DocDataModel getDocDataModel() {
        return docDataModel;
    }

    @Override
    public void setDocInfo(DocDataModel docDataModel, Context context) {

    }

    @Override
    public void getDocInfo(String id, Context context) {

    }

    @Override
    public void docInfo(DocDataModel docDataModel, Context context) {
        _httpDocController = HttpDocController.getInstance();
        _httpDocController.set_iHttpResultHandler(_iHttpResultHandler);
        _httpDocController.docData(docDataModel,context);
    }
}
