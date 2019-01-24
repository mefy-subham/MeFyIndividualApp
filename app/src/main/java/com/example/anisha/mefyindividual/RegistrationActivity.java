package com.example.anisha.mefyindividual;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.AsyncTask;
//import android.support.v4.app.Fragment;
import android.provider.Settings;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.regex.Pattern;

import static com.example.anisha.mefyindividual.R.*;

public class RegistrationActivity extends AppCompatActivity {
    public static SharedPreferences.Editor bundle;
    public static Bundle myBundle = new Bundle();
    Button firstbtn,secbtn,thirdbtn,fourthbtn,fifthbtn,sixthbtn,seventhbtn,eighthbtn;
    EditText textfirst,textthird,textfourth,textfifth;
    TextView text1,text2,text3,text4,text5,text9,text10,text11,
            text12,text13,text14,text15,text16,textsecond,text17,
            text18,text19,text20,text21,devid;
    LinearLayout layoutMain,layoutSec,layoutthird,layoutfour,layoutfive,
            layoutsix,layoutseven,layouteight;
    RadioGroup gender;
    RadioButton radiosex;
    private String getName;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "RegistrationActivity";
    String deviceid;
    boolean otpres=false;
    ProgressDialog progress;
    public static final String MyPREFERENCES = "MyPrefs" ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_registration);

        Bundle myBundle = new Bundle();

        firstbtn = (Button) findViewById(id.firstbtn);
        secbtn = (Button) findViewById(id.secbtn);
        thirdbtn = (Button) findViewById(id.thirdbtn);
        fourthbtn= (Button)findViewById(id.fourthbtn);
        fifthbtn = (Button)findViewById(id.fifthbtn);
        sixthbtn = (Button)findViewById(id.sixthbtn);
        seventhbtn = (Button)findViewById(id.seventhbtn);
        eighthbtn = (Button)findViewById(id.eighthbtn);
        gender = (RadioGroup) findViewById(id.gender);

        textfirst = (EditText) findViewById(id.textfirst);
        textthird = (EditText) findViewById(id.textthird);
        textfourth= (EditText) findViewById(id.textfourth);
        textfifth = (EditText) findViewById(id.textfifth);

        textsecond = (TextView)findViewById(id.textsecond);
        text1 = (TextView)findViewById(id.text1);
        text2 = (TextView)findViewById(id.text2);
        text3 = (TextView)findViewById(id.text3);
        text4 = (TextView)findViewById(id.text4);
        text5 = (TextView)findViewById(id.text5);
        text9 = (TextView)findViewById(id.text9);
        text10 = (TextView)findViewById(id.text10);
        text11 = (TextView)findViewById(id.text11);
        text12 = (TextView)findViewById(id.text12);
        text13 = (TextView)findViewById(id.text13);
        text14 = (TextView)findViewById(id.text14);
        text15 = (TextView)findViewById(R.id.text15);
        text16 = (TextView)findViewById(id.text16);
        text17 = (TextView)findViewById(id.text17);
        text18 = (TextView)findViewById(id.text18);
        text19 = (TextView)findViewById(id.text19);
        text20 = (TextView)findViewById(id.text20);
        text21 = (TextView)findViewById(id.text21);
        devid = (TextView)findViewById(id.devid);

        layoutMain = (LinearLayout) findViewById(id.layoutMain);
        layoutSec = (LinearLayout) findViewById(id.layoutSec);
        layoutthird = (LinearLayout) findViewById(id.layoutthird);
        layoutfour = (LinearLayout) findViewById(id.layoutfour);
        layoutfive = (LinearLayout)findViewById(id.layoutfive);
        layoutsix = (LinearLayout)findViewById(id.layoutsix);
        layoutseven = (LinearLayout)findViewById(id.layoutseven);
        layouteight = (LinearLayout)findViewById(id.layouteight);

        //System.out.println("entered 3rd activity---------->>>>>>>>>>>>>>>>");


       deviceid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

//     ----------------- NAME ON CLICK VALIDATE --------------------

        firstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validates());
                String getName = textfirst.getText().toString();
                text4.setText(getName);
                //System.out.println("1st data--------->>>>>>" +textfirst);
            }
        });

//       ------------------ GENDER ON CLICK VALIDATE --------------------

        secbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radiovalidate());
                String getNames = ((EditText) findViewById(R.id.textfirst)).getText().toString();
                text9.setText(getNames);
                //System.out.println("2nd data--------->>>>>>" +radiosex);

            }
        });

//      -------------------  BIRTH ON CLICK VALIDATE -----------------

        thirdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("2nd data--------->>>>>>" );
                if(datevalidate());
                String getNames = ((EditText) findViewById(R.id.textfirst)).getText().toString();
                text11.setText(getNames);
                //System.out.println("3rd data--------->>>>>>" +textsecond);
            }
        });

//       @@@@@@@@@@@@@ DATE PICKER DIALOG FOR BIRTH ON CLICK @@@@@@@@@@@@

        textsecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(RegistrationActivity.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            month = month+1;
                Log.d(TAG, "onDateSet: mm/dd/yyy:" + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                textsecond.setText(date);
            }
        };

//       ------------------- CITY ON CLICK VALIDATE ------------------

        fourthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cityvalidate());
                String getNames = ((EditText) findViewById(R.id.textfirst)).getText().toString();
                text11.setText(getNames);
                //System.out.println("4th data--------->>>>>>" +textthird);
             }
        });

//     ========= PHONE NUMBER ON CLICK VALIDATE AND PRE-REGISTRATION API CALL============

        fifthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckConnectionStatus();

                String getNames = ((EditText) findViewById(R.id.textfirst)).getText().toString();
                text11.setText(getNames);
//                layoutfive.setVisibility(View.GONE);
//                layoutsix.setVisibility(View.VISIBLE);
            }
        });

//      ===============  OTP VALIDATE AND REGISTRATION API CALL====================

        sixthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        otpres = true;
                //System.out.println("button clicked----------------->>>>>>>>>>>>>>>"+otpres);
                CheckConnectionStatus();
                if (otpres == true){
                    layoutsix.setVisibility(View.GONE);
                    layoutseven.setVisibility(View.VISIBLE);
                }

            }
        });


//       ----------------- TERMS AND CONDITION VISIBILITY ------------------------

        seventhbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutseven.setVisibility(View.GONE);
                layouteight.setVisibility(View.VISIBLE);
            }
        });


//   ======== INTENT APPLIED FROM THE LAST PAGE OF TERMS AND CONDITION TO DASHBOARD ========

        eighthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this,DashboardActivity.class);
                intent.putExtra("username",textfirst.getText().toString());
                intent.putExtra("birth",textsecond.getText().toString());
                intent.putExtra("city",textthird.getText().toString());
                intent.putExtra("phone",textfourth.getText().toString());
                startActivity(intent);
            }
        });

    }

    // ++++++++++++++VALIDATION FOR THE NAME FIELD+++++++++++++++++++

    private  boolean validates(){
        boolean result = false;

        String name = textfirst.getText().toString();
        //System.out.println("captured data----------->>>>>>" +name);
        if(name.isEmpty()){
            Toast.makeText(this,"please enter the " +
                    "name",Toast.LENGTH_SHORT ).show();
        }
        else if(name.length()>50){
            Toast.makeText(this,"Name length is" +
                    " too big",Toast.LENGTH_LONG ).show();
        }
        else {
            RegistrationActivity.myBundle.putString("Name" ,name);
            layoutMain.setVisibility(View.GONE);
            layoutSec.setVisibility(View.VISIBLE);
            result = true;
        }
        return result;
    }

    // +++++++++++++++++VALIDATION FOR GENDER RADIO BUTTON+++++++++++++++++++

    private boolean radiovalidate(){
        boolean radio = false;
       int radiogender = gender.getCheckedRadioButtonId();
        radiosex = (RadioButton) findViewById(radiogender);
        Toast.makeText(RegistrationActivity.this,
                radiosex.getText().toString(), Toast.LENGTH_SHORT).show();


        if(radiosex.isDirty()){
            Toast.makeText(this,"Please click one of the" +
                    " option",Toast.LENGTH_SHORT ).show();
        }
        else {
            RegistrationActivity.myBundle.putString("sex" , radiosex.getText().toString());
            layoutthird.setVisibility(View.VISIBLE);
            layoutSec.setVisibility(View.GONE);
            radio = true;
        }
        return radio;
    }
    // ++++++++++++++++++BIRTH VALIDATION++++++++++++++++++++

    private boolean datevalidate(){
           boolean birth = false;
          String dateOfbirth = textsecond.getText().toString();
          if(dateOfbirth.isEmpty()){
              Toast.makeText(this,"please enter the " +
                      "date of birth",Toast.LENGTH_SHORT ).show();
          }
          else{
              RegistrationActivity.myBundle.putString("Birth" , String.valueOf(dateOfbirth));

              layoutfour.setVisibility(View.VISIBLE);
              layoutthird.setVisibility(View.GONE);
              birth = true;
          }
           return birth;
    }
    // ++++++++++++++++++++++CITY VALIDATION++++++++++++++++++++++

    private boolean cityvalidate(){
            boolean city = false;
            String place = textthird.getText().toString();
            if(place.isEmpty()){
                Toast.makeText(this,"please enter the " +
                        "city",Toast.LENGTH_SHORT ).show();
            }
            else{
                RegistrationActivity.myBundle.putString("City" , String.valueOf(place));
                layoutfour.setVisibility(View.GONE);
                layoutfive.setVisibility(View.VISIBLE);
                city = true;
            }
            return city;
    }


//  *******************  API CALL FOR PRE REGISTRATION AND REGISTRATION *****************
    public void CheckConnectionStatus(){
        progress = new ProgressDialog(this);
        progress.setMessage("please wait...");
        progress.setCancelable(false);
        progress.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try{
            if (otpres == false){
                json.accumulate("phoneNumber", textfourth.getText().toString());
                json.accumulate("role","individual");
                //System.out.println(json+"otp is false---------------->>>>>>>>>>>>>>");
            }
            else if (otpres == true){
                json.accumulate("name",textfirst.getText().toString());
                json.accumulate("gender",radiosex.getText().toString());
                json.accumulate("dob",textsecond.getText().toString());
                json.accumulate("city",textthird.getText().toString());
                json.accumulate("phoneNumber", textfourth.getText().toString());
                json.accumulate("otp",textfifth.getText().toString());
                json.accumulate("deviceId",deviceid);
                json.accumulate("role","individual");
                //System.out.println(json+"otp is true---------------->>>>>>>>>>>>>>");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = getResources().getString(string.apiurl);
            String result = "";
        if (otpres == false){
            url = url+"/User/preregistration";
        }
        else {
            url = "http://ec2-13-232-207-92.ap-south-1.compute.amazonaws.com:5023/api/User/registration";
        }

        //System.out.println("url---------------->>>>>>>>>>"+ url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        progress.dismiss();
                        //System.out.println("response-------------------------------->>>>>>>>>>>>>>>"+response.toString());
                        try{
                            JSONObject obj = response.getJSONObject("result");
                            if (otpres == false){
//                                JSONObject obj = response.getJSONObject("result");
                                String msg = obj.getString("message");
                                //System.out.println("msg--------------->>>"+msg.toString());
                                if (msg.equals("OTP sent to registered number")){
                                    layoutfive.setVisibility(View.GONE);
                                    layoutsix.setVisibility(View.VISIBLE);
                                }
                                else if (msg.equals("User already registered")){
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Number already registered.",
                                            Toast.LENGTH_SHORT);

                                    toast.show();
                                }
                            }

                            else {
//                                JSONObject obj = response.getJSONObject("result");
                                JSONObject userObject = obj.getJSONObject("user");
                            String username = userObject.getString("name");
                            String individualId = userObject.getString("individualId");
                            String userId = userObject.getString("userId");
                            String myUserId = userId.substring(userId.lastIndexOf("#")+1);
                            //System.out.println("seperated--------->>>"+myUserId);
                            //System.out.println("username----------------"+username);
                            //System.out.println("individualId-----------------"+individualId);
                            //System.out.println("userId------------->>>>>>>>>>>"+userId);
                                String msg = obj.getString("message");
                                //System.out.println("msg--------------->>>"+msg.toString());
                            if ( msg.equals("Individual loggedIn successfully")){
                                layoutsix.setVisibility(View.GONE);
                                layoutseven.setVisibility(View.VISIBLE);
                            }


                            SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();
                            editor.putString("individualId",userObject.getString("individualId"));
                            editor.putString("userId",userObject.getString("userId"));
                            editor.putString("myUserId",userId.substring(userId.lastIndexOf("#")+1));
                            editor.putString("username",userObject.getString("name"));
                            //System.out.println("editor------------>>>>>>>>>>"+userObject.getString("individualId"));
                            editor.commit();
                            }

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progress.dismiss();
                //System.out.println("Error getting response------------------------");
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

}
