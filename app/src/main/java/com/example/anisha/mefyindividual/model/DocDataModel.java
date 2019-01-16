package com.example.anisha.mefyindividual.model;

public class DocDataModel {

    private String mobiletoken;

    private String individualId;
    private String room_name;
    private String user_info;
    private String type;
    private String status;

    public String getIndividualId() {
        return individualId;
    }

    public void setIndividualId(String individualId) {
        this.individualId = individualId;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getUser_info() {
        return user_info;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobiletoken() {
        return mobiletoken;
    }

    public void setMobiletoken(String mobiletoken) {
        this.mobiletoken = mobiletoken;
    }
}
