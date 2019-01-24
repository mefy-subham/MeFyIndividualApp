package com.example.anisha.mefyindividual;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

    public class DoctorList extends AppCompatActivity {
    //Spinner spinner1;
    LinearLayout doctordet;
    RelativeLayout appointmentlist;
    Button datepicker;
    ProgressDialog progress;
    TextView doctorfirsttv,doctorsectv,doctorthirdtv,doctorfourthtv;
    String newString;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        datepicker= (Button)findViewById(R.id.datepicker);
        doctordet = (LinearLayout)findViewById(R.id.doctordet);
        appointmentlist = (RelativeLayout)findViewById(R.id.appointmentlist);
        doctorfirsttv = (TextView)findViewById(R.id.doctorfirsttv);
        doctorsectv = (TextView)findViewById(R.id.doctorsectv);
        doctorthirdtv = (TextView)findViewById(R.id.doctorthirdtv);
        doctorfourthtv = (TextView)findViewById(R.id.doctorfourthtv);


        Bundle extras = getIntent().getExtras();
        newString= extras.getString("docId");
        //System.out.println("doctor id ---------->>>>>>>>>>>" + newString);
//        addItemsOnSpinner2();

//        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
//        docId = prefs.getString("docId", "");
        docprofile();
    }
//    public void addItemsOnSpinner2() {
//
//        spinner1 = (Spinner) findViewById(R.id.spinner1);
//        List<String> list = new ArrayList<String>();
//        list.add("e-Consult");
//        list.add("Clinic Visit");
//
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, list);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner1.setAdapter(dataAdapter);
//    }


    //DOCTOR PROFILE FROM DOCTOR ID

    public void docprofile(){
        progress = new ProgressDialog(this);
        progress.setMessage("please wait...");
        progress.setCancelable(false);
        progress.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

         String url = getResources().getString(R.string.apiurl);
         url = url+"/doctor/"+newString;

        //System.out.println("url---------------->>>>>>>>>>"+ url);

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null,
                new Response.Listener<JSONArray>(){

                    @Override
                    public void onResponse(JSONArray response) {
                        progress.dismiss();
                        //System.out.println("response------------------------------------"+ response.toString());

                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject jsonObject=response.getJSONObject(i);
                             String docId = jsonObject.getString("doctorId");

                                //System.out.println("doctor id----------->>>>>>>"+docId);
                                String docName = jsonObject.getString("name");
                                String docSpec = jsonObject.getString("speciality");
                                String docAddr = jsonObject.getString("address");
                                String docPractice = jsonObject.getString("practicingSince");
                                doctorfirsttv.setText(docName);
                                doctorsectv.setText(docSpec);
                                doctorthirdtv.setText(docAddr);
                                doctorfourthtv.setText(docPractice);
//                                data.add(jsonObject.getString("name")+"\n"+jsonObject.getString("speciality")+"\n"+jsonObject.getString("address"));
//                            imgitem.add(jsonObject.getString("profileImage"));


                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        }
//                    adapter.notifyDataSetChanged();
                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                progress.dismiss();
                //System.out.println("error-----------------------------------"+ error);
            }
        })

        {
            /* Passing some request headers */
            @Override
            public Map<String,String> getHeaders() {
                //System.out.println("header----------------------------------------------------------------------<><><><><><><>");
                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json");
                headers.put("Accept","*/*");
                return headers;
            }
        };


        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonArrayRequest.setRetryPolicy(policy);
        //System.out.println("wating-----<><><><><><><><><><><><><><><><><><><><>><><><><>><");
        requestQueue.add(jsonArrayRequest);
    }
}
