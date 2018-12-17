package com.example.anisha.mefyindividual;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Appointlistview extends ArrayAdapter<String>{
    private Integer[] appoint_img;
    private String[] appointdoc_name;
    private String[] appointdoc_desc;
    private Activity context;

    public Appointlistview(@NonNull Activity context, Integer[] appoint_img, String[] appointdoc_name) {
        super(context, R.layout.appointlist,appointdoc_name);

        this.context = context;
        this.appointdoc_name = appointdoc_name;
//        this.appointdoc_desc = appointdoc_desc;
        this.appoint_img = appoint_img;
    }



}
