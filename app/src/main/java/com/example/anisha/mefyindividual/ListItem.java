package com.example.anisha.mefyindividual;

public class ListItem {

    private String name;
    private String desc;
    private String img;

    public ListItem(String name , String desc , String img){
        this.name = name;
        this.desc = desc;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getImg() {
        return img;
    }
}
