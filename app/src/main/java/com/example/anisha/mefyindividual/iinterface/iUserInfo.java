package com.example.anisha.mefyindividual.iinterface;

import android.content.Context;

import com.example.anisha.mefyindividual.model.UserDataModel;


public interface iUserInfo {
    void setUserInfo(UserDataModel userDataModel, Context context);
    void getUserInfo(String id, Context context);
    void updateUserInfo(UserDataModel userDataModel, Context context);
}
