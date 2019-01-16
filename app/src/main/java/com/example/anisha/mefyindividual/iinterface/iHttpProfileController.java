package com.example.anisha.mefyindividual.iinterface;

import android.content.Context;

import com.example.anisha.mefyindividual.model.UserDataModel;


public interface iHttpProfileController {

    void setProfileData(UserDataModel profileData, Context context);
    void updateProfileData(UserDataModel profileData, Context context);
    void getProfileData(String phoneNo, Context context);
}
