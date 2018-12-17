package com.example.anisha.mefyindividual;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private ViewPager mSlideViewPager;
private LinearLayout mDotLayout;
private TextView[] mDots;
private Button mnext;
private SliderAdaptor sliderAdaptor;
    public static final String MyPREFERENCES = "MyPrefs" ;
    String userid,role,individualId,myUserId;
private int mCurrentPage;
    private int position;
    public static SharedPreferences.Editor bundle;
    public static Bundle myBundle = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        mnext = (Button) findViewById(R.id.nextbtn);
        sliderAdaptor = new SliderAdaptor(this);
        mSlideViewPager.setAdapter(sliderAdaptor);
        Bundle myBundle = new Bundle();
        addDotIndicator(position);
        mSlideViewPager.addOnPageChangeListener(viewListner);
        mnext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
//        Intent myIntent = new Intent(getBaseContext(),   LoginActivity.class);
//        startActivity(myIntent);
        userIdvalidate();

    }
});
        System.out.println("main activity------------->>>>>");

        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        individualId = prefs.getString("individualId", "");
        userid = prefs.getString("userId","");
        myUserId = prefs.getString("myUserId","");
        role = "individual";
        System.out.println("id------------------------->>"+individualId);
        System.out.println("userId-------------->>>>>>>>"+userid);
        System.out.println("myUserId---------->>>>>>>>>>>>>>"+myUserId);
    }
        public void userIdvalidate(){
//        String getUserid = null;
//           if (getUserid.equals(myUserId)){
//               Intent myIntent = new Intent(getBaseContext(),   LoginActivity.class);
//               startActivity(myIntent);
//           }
//           else {
//               Intent myIntent = new Intent(getBaseContext(),   DashboardActivity.class);
//               startActivity(myIntent);
//           }
            final String getuserId = myUserId;
            if (getuserId.equals("")){
             Intent myIntent = new Intent(getBaseContext(),   LoginActivity.class);
               startActivity(myIntent);
            }
            else {
               Intent myIntent = new Intent(getBaseContext(),   DashboardActivity.class);
               startActivity(myIntent);
           }
        }


    // SLIDER DOTS SECTION

    public void addDotIndicator(int position){
        mDots = new TextView[4];
        mDotLayout.removeAllViews();
        for(int i = 0;i < mDots.length;i++){
        mDots[i] = new TextView(this);
        mDots[i].setText(Html.fromHtml("&#8226"));
        mDots[i].setTextSize(35);
        mDots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        mDotLayout.addView(mDots[i]);
        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
        addDotIndicator(i);
        mCurrentPage = i;

        if(i == 0){
            mnext.setEnabled(false);
            mnext.setText("");
        }
        else if(i == mDots.length - 1){
            mnext.setEnabled(true);
            mnext.setText("Let's Go");
            mnext.setVisibility(View.VISIBLE);
        }
        else {
            mnext.setEnabled(false);

            mnext.setText("");

        }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
