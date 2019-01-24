package com.example.anisha.mefyindividual;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.layout.select_dialog_item;
import static android.R.layout.simple_list_item_1;

public class HealthActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    String userid,role,individualId,myUserId,userAddr,userPin,username;
    public static final String MyPREFERENCES = "MyPrefs" ;
    LinearLayout healthlayout,allergy
            ,allergydet,diseasedet,surgerydet,treatementlayout
            ,treatmentdet,medicationdet,familydet,uploadlayout,allergyView,allergylistview,
            surgerylistview;
    RelativeLayout diseasemainlayout,surgerymainlayout,medicationmainlayout,
            familymainlayout,healthmainlayout;
    TextView healthfirsttv,healthsectv,healththirdtv,healthfourthtv,allergyfirsttv,diseasefirsttv
    ,surgeryfirsttv,treatmentfirsttv,treatmentsectv,medicationfirsttv,familyfirsttv,allergytypeview
            ;
    ToggleButton healthfirsttoggle,healthsectoggle,healththirdtoggle,healthfourthtoggle;
    Button healthleftfirst,healthfirstbtn,healthsecbtn,healththirdbtn,healthfourthbtn,allergyleftfirst,allergyfirstbtn,
            allergysecbtn,allergythirdbtn,diseaseleftfirst,diseasefirstbtn,diseasesecbtn,diseasethirdbtn,
            diseasefourthbtn,diseasefifthbtn,surgeryleftfirst,surgeryfirstbtn,surgerysecbtn,surgerythirdbtn,surgeryfourthbtn
    ,surgeryfifthbtn ,trtmntleftfirst,treatmentfirstbtn,treatmentsecbtn,treatmentthirdbtn,treatmentfourthbtn,medleftfirst,medicationfirstbtn,medicationsecbtn
            ,medicationthirdbtn,familysecbtn,familythirdbtn;
    EditText allergythirdedit,diseasesecedit,
            diseasethirdedit,surgerysecedit,surgerythirdedit,meidcationfirstedit
            ,medicationsecedit,medicationthirdedit,medicationfourthedit,medicationfifthedit
            ,familyfirstedit,familysecedit;
    AutoCompleteTextView allergyfirstedit,allergysecedit,diseasefirstedit,surgeryfirstedit;
    boolean layout = true;
    ProgressDialog progress;
    private List<ListItem> listItems;
    //    private RecyclerView.Adapter adapter;
    private ArrayList<String> items;
    private ArrayList<String>imgitem;
    private ArrayAdapter<String>adapter;
    String userName;
    ListView lst;
    Boolean allergyviewbtn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //System.out.println("health-------------------->>>>>>>>>>>>>>>>>>>");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.drawerusername);
        userName = prefs.getString("username","");
        navUsername.setText(userName);
        healthlayout = (LinearLayout)findViewById(R.id.healthlayout);
        healthmainlayout = (RelativeLayout)findViewById(R.id.healthmainlayout);
        allergy = (LinearLayout)findViewById(R.id.allergy);
        allergydet = (LinearLayout)findViewById(R.id.allergydet);
        diseasemainlayout = (RelativeLayout) findViewById(R.id.diseasemainlayout);
        diseasedet = (LinearLayout)findViewById(R.id.diseasedet);
        surgerymainlayout = (RelativeLayout) findViewById(R.id.surgerymainlayout);
        surgerydet = (LinearLayout)findViewById(R.id.surgerydet);
        treatementlayout = (LinearLayout)findViewById(R.id.treatementlayout);
        treatmentdet = (LinearLayout)findViewById(R.id.treatmentdet);
        medicationmainlayout = (RelativeLayout)findViewById(R.id.medicationmainlayout);
        medicationdet = (LinearLayout)findViewById(R.id.medicationdet);
        familymainlayout = (RelativeLayout)findViewById(R.id.familymainlayout);
        familydet = (LinearLayout)findViewById(R.id.familydet);
        uploadlayout =(LinearLayout)findViewById(R.id.uploadlayout);
        allergyView = (LinearLayout)findViewById(R.id.allergyView);
        allergylistview = (LinearLayout)findViewById(R.id.allergylistview);

        healthfirsttv = (TextView)findViewById(R.id.healthfirsttv);
        healthsectv = (TextView)findViewById(R.id.healthsectv);
        healththirdtv = (TextView)findViewById(R.id.healththirdtv);
        healthfourthtv = (TextView)findViewById(R.id.healthfourthtv);
        allergyfirsttv = (TextView)findViewById(R.id.allergyfirsttv);
        diseasefirsttv = (TextView)findViewById(R.id.diseasefirsttv);
        surgeryfirsttv = (TextView)findViewById(R.id.surgeryfirsttv);
        treatmentfirsttv = (TextView)findViewById(R.id.treatmentfirsttv);
        treatmentsectv = (TextView)findViewById(R.id.treatmentsectv);
        medicationfirsttv = (TextView)findViewById(R.id.medicationfirsttv);
        familyfirsttv = (TextView)findViewById(R.id.familyfirsttv);
        allergytypeview = (TextView)findViewById(R.id.allergytypeview);

        healthfirsttoggle = (ToggleButton)findViewById(R.id.healthfirsttoggle);
        healthsectoggle = (ToggleButton)findViewById(R.id.healthsectoggle);
        healththirdtoggle = (ToggleButton)findViewById(R.id.healththirdtoggle);
        healthfourthtoggle = (ToggleButton)findViewById(R.id.healthfourthtoggle);

        healthleftfirst = (Button)findViewById(R.id.healthleftfirst);
        healthfirstbtn = (Button)findViewById(R.id.healthfirstbtn);
        healthsecbtn = (Button)findViewById(R.id.healthsecbtn);
        healththirdbtn = (Button)findViewById(R.id.healththirdbtn);
        healthfourthbtn = (Button)findViewById(R.id.healthfourthbtn);
        allergyleftfirst = (Button)findViewById(R.id.allergyleftfirst);
        allergyfirstbtn = (Button)findViewById(R.id.allergyfirstbtn);
        allergysecbtn = (Button)findViewById(R.id.allergysecbtn);
        allergythirdbtn = (Button)findViewById(R.id.allergythirdbtn);
        diseaseleftfirst = (Button)findViewById(R.id.diseaseleftfirst);
        diseasefirstbtn = (Button)findViewById(R.id.diseasefirstbtn);
        diseasesecbtn = (Button)findViewById(R.id.diseasesecbtn);
        diseasethirdbtn = (Button)findViewById(R.id.diseasethirdbtn);
        diseasefourthbtn = (Button)findViewById(R.id.diseasefourthbtn);
        diseasefifthbtn = (Button)findViewById(R.id.diseasefifthbtn);
        surgeryleftfirst = (Button)findViewById(R.id.surgeryleftfirst);
        surgeryfirstbtn = (Button)findViewById(R.id.surgeryfirstbtn);
        surgerysecbtn = (Button)findViewById(R.id.surgerysecbtn);
        surgerythirdbtn = (Button)findViewById(R.id.surgerythirdbtn);
        surgeryfourthbtn = (Button)findViewById(R.id.surgeryfourthbtn);
        surgeryfifthbtn = (Button)findViewById(R.id.surgeryfifthbtn);
        trtmntleftfirst = (Button)findViewById(R.id.trtmntleftfirst);
        treatmentfirstbtn = (Button)findViewById(R.id.treatmentfirstbtn);
        treatmentsecbtn = (Button)findViewById(R.id.treatmentsecbtn);
        treatmentthirdbtn = (Button)findViewById(R.id.treatmentthirdbtn);
        treatmentfourthbtn = (Button)findViewById(R.id.treatmentfourthbtn);
        medleftfirst = (Button)findViewById(R.id.medleftfirst);
        medicationfirstbtn = (Button)findViewById(R.id.medicationfirstbtn);
        medicationsecbtn = (Button)findViewById(R.id.medicationsecbtn);
        medicationthirdbtn = (Button)findViewById(R.id.medicationthirdbtn);
        familysecbtn = (Button)findViewById(R.id.familysecbtn);
        familythirdbtn = (Button)findViewById(R.id.familythirdbtn);

        allergyfirstedit = (AutoCompleteTextView) findViewById(R.id.allergyfirstedit);
        allergysecedit = (AutoCompleteTextView)findViewById(R.id.allergysecedit);
        allergythirdedit = (EditText)findViewById(R.id.allergythirdedit);
        diseasefirstedit = (AutoCompleteTextView)findViewById(R.id.diseasefirstedit);
        diseasesecedit = (EditText)findViewById(R.id.diseasesecedit);
        diseasethirdedit = (EditText)findViewById(R.id.diseasethirdedit);
        surgeryfirstedit = (AutoCompleteTextView)findViewById(R.id.surgeryfirstedit);
        surgerysecedit = (EditText)findViewById(R.id.surgerysecedit);
        surgerythirdedit = (EditText)findViewById(R.id.surgerythirdedit);
        meidcationfirstedit = (EditText)findViewById(R.id.meidcationfirstedit);
        medicationsecedit = (EditText)findViewById(R.id.medicationsecedit);
        medicationthirdedit = (EditText)findViewById(R.id.medicationthirdedit);
        medicationfourthedit = (EditText)findViewById(R.id.medicationfourthedit);
        medicationfifthedit = (EditText)findViewById(R.id.medicationfifthedit);
        familyfirstedit = (EditText)findViewById(R.id.familyfirstedit);
        familysecedit = (EditText)findViewById(R.id.familysecedit);


//        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        individualId = prefs.getString("individualId", "");
        userid = prefs.getString("userId","");
        myUserId = prefs.getString("myUserId","");
        username = prefs.getString("username","");
        role = "individual";
        //System.out.println("id------------------------->>"+individualId);
        //System.out.println("userId-------------->>>>>>>>"+userid);
        //System.out.println("myUserId---------->>>>>>>>>>>>>>"+myUserId);
        //System.out.println("username------------>>>>"+username);


        lst = (ListView)findViewById(R.id.allergylist);
        items=new ArrayList<String>();
        adapter=new ArrayAdapter<>(this,R.layout.allergyview,R.id.allergytypeview,items);
//        adapter=new ArrayAdapter<>(this,R.layout.appointlist,R.id.appointmentimg,imgitem);
        lst.setAdapter(adapter);


        healthfirstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(healthfirsttoggle.isChecked()){
                 healthlayout.setVisibility(View.GONE);
                 allergy.setVisibility(View.VISIBLE);
              }
              else {
                  Toast.makeText(HealthActivity.this,"Kindly click the toggle button",
                          Toast.LENGTH_LONG).show();
              }
              allergyAutoComplete();
            }
        });

        healthsecbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (healthsectoggle.isChecked()){
                    healthlayout.setVisibility(View.GONE);
                    allergy.setVisibility(View.GONE);
                    diseasemainlayout.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(HealthActivity.this,"Kindly click the toggle button",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        healththirdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (healththirdtoggle.isChecked()){
                    healthlayout.setVisibility(View.GONE);
                    allergy.setVisibility(View.GONE);
                    diseasemainlayout.setVisibility(View.GONE);
                    surgerymainlayout.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(HealthActivity.this,"Kindly click the toggle button",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

//        surgerythirdedit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                healthlayout.setVisibility(View.GONE);
//                allergy.setVisibility(View.GONE);
//                diseasemainlayout.setVisibility(View.GONE);
//                surgerymainlayout.setVisibility(View.GONE);
//                treatementlayout.setVisibility(View.VISIBLE);
//            }
//        });
        surgerythirdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                healthlayout.setVisibility(View.GONE);
                allergy.setVisibility(View.GONE);
                diseasemainlayout.setVisibility(View.GONE);
                surgerymainlayout.setVisibility(View.GONE);
                treatementlayout.setVisibility(View.GONE);
                medicationmainlayout.setVisibility(View.VISIBLE);
            }
        });
        healthleftfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent h= new Intent(HealthActivity.this,DashboardActivity.class);
                startActivity(h);
            }
        });
        allergyleftfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allergy.setVisibility(View.GONE);
                healthlayout.setVisibility(View.VISIBLE);
            }
        });
        diseaseleftfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diseasemainlayout.setVisibility(View.GONE);
                healthlayout.setVisibility(View.VISIBLE);
            }
        });
        surgeryleftfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surgerymainlayout.setVisibility(View.GONE);
                healthlayout.setVisibility(View.VISIBLE);
            }
        });
        surgeryfifthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surgerymainlayout.setVisibility(View.GONE);
                treatementlayout.setVisibility(View.VISIBLE);
            }
        });
        trtmntleftfirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treatementlayout.setVisibility(View.GONE);
                healthlayout.setVisibility(View.VISIBLE);
            }
        });
        healthfirsttoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                healthfirsttoggle.isChecked();
                //System.out.println("allergyToggle"+healthfirsttoggle.isChecked());
                allergyToggleChecked();
            }
        });
        allergyfirstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allergy.setVisibility(View.GONE);
                allergylistview.setVisibility(View.VISIBLE);
                allergygetdetails();
                allergyviewbtn = true;

            }
        });

        surgeryfirstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surgerymainlayout.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public void onBackPressed() {
        //System.out.println();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            //System.out.println("first-------------------------->>>>>");
        }

            else if (allergyviewbtn == true){
            //System.out.println(" from allergy list view------------>>>>>");
            allergy.setVisibility(View.VISIBLE);
            allergylistview.setVisibility(View.GONE);
        }
            else {
                super.onBackPressed();
            //System.out.println("sec---------------------------------->>>>>");
            }
        }


//    allergy toggle checked api
public void allergyToggleChecked(){
    progress = new ProgressDialog(this);
    progress.setMessage("please wait...");
    progress.setCancelable(false);
    progress.show();
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    JSONObject json = new JSONObject();

        String url = getResources().getString(R.string.apiurl);
    final String result = "";
     url = url+"/individual/"+individualId;


    //System.out.println("url---------------->>>>>>>>>>"+ url);

    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,json,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    progress.dismiss();
                    //System.out.println("resp------------------->>>>>>>>>>>>>>>>"+result);
                    //System.out.println("response-------------------------------->>>>>>>>>>>>>>>"+response.toString());
                    try {
                        JSONObject serverResp = new JSONObject(response.toString());
                        String userHealth = serverResp.getString("HealthRecord");
                        //System.out.println("healthrecord--------->>>>>>"+userHealth);
                        progress.dismiss();
                    } catch (JSONException e) {
// TODO Auto-generated catch block
                        progress.dismiss();
                        //System.out.println("cache------------------------------------------>>>>>>>>>>>>>>");
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



//ALLERGY DETAILS AUTO COMPLETE API

   public void allergyAutoComplete(){
       progress = new ProgressDialog(this);
       progress.setMessage("please wait...");
       progress.setCancelable(false);
       progress.show();
       RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = getResources().getString(R.string.apiurl);
       final String result = "";
        url = url+"/individual/allergies";


       //System.out.println("url---------------->>>>>>>>>>"+ url);

       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
               new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {
                       progress.dismiss();
                       //System.out.println("resp------------------->>>>>>>>>>>>>>>>"+result);
                       //System.out.println("response-------------------------------->>>>>>>>>>>>>>>"+response.toString());
                       try {
                           JSONObject serverResp = new JSONObject(response.toString());
                           JSONArray obj = serverResp.getJSONArray("result");
                           String[] sasasa = {"Medicine","Food","Dust","Skin","Insect Sting","Cockraches","Dog","Cat","Eye","Hey Fever","Latex","Mold","Sinusitis"};
                           //System.out.println("sasaa------------>>>>"+sasasa.toString());
                           String[] allergywith ={"Curd", "Egg", "Milk", "Wheat", "Corn",
                                   "Peanut", "Soy", "Garlic", "Chilli", "Fruit"};
                           String[] disease = {"Respiratory","Cardiovascular","Disabilities",
                                   "Cancer","Gastrointestinal system","Hepatic diseases",
                                  "Nervous system" ,"Respiratory system","Cardiovascular system",
                                  "Auto immune conditions", "Oncology","Metabolic disorders",
                                   "Endocrinology"," Allergies","Dermatological conditions",
                                   "Psychiatric diseases","Musculoskeletal system","Rheumatology",
                                   "Genitourinary system"," Gynecology and Obstetric history",
                                   "Sexual wellbeing","ENT","Ophthalmology","Disabilities"};
                            String[] surgery = {"hernia repair", "stomach surgery","hemorrhoids", "removal of the appendix",
                                            "removal of the gall bladder","breast surgery","colonoscopy"};



//                           List<String> responselist = new ArrayList<String>();
//                           for (int i=1;i<response.length();i++){
//                               JSONObject jsonObject=response.getJSONObject(String.valueOf(i));
//                               String allergyname = jsonObject.getString("result");
//                               //System.out.println("allergyname---------->>>>"+allergyname);
//                               responselist.add(allergyname);
//                           }

//                           JSONArray obj = response.getJSONArray("result");
//                           JSONArray allergytype = obj;

//                           //System.out.println("allergy-------->>>>>"+obj);
                           ArrayAdapter<String> adapter = new ArrayAdapter<String>(HealthActivity.this,select_dialog_item,sasasa);
                           AutoCompleteTextView allergyfirstedit = (AutoCompleteTextView)findViewById(R.id.allergyfirstedit);
                           allergyfirstedit.setAdapter(adapter);
                           //System.out.println("adapter------------>>>>"+adapter);
                           allergyfirstedit.setThreshold(1);

                           ArrayAdapter<String> adapterwith = new ArrayAdapter<String>(HealthActivity.this,select_dialog_item,allergywith);
                           allergysecedit.setAdapter(adapterwith);
                           allergysecedit.setThreshold(1);

                           ArrayAdapter<String> diseasewith = new ArrayAdapter<String>(HealthActivity.this,select_dialog_item,disease);
                           diseasefirstedit.setAdapter(diseasewith);
                           diseasefirstedit.setThreshold(1);

                           ArrayAdapter<String> surgerywith = new ArrayAdapter<String>(HealthActivity.this,select_dialog_item,surgery);
                           surgeryfirstedit.setAdapter(surgerywith);
                           surgeryfirstedit.setThreshold(1);
                       } catch (JSONException e) {
// TODO Auto-generated catch block
                           progress.dismiss();
                           //System.out.println("cache------------------------------------------>>>>>>>>>>>>>>");
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


//   POST ALLERGY API

    public void postallergy(View view){

        healthdialog();
        progress = new ProgressDialog(this);
        progress.setMessage("please wait...");
        progress.setCancelable(false);
        progress.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.accumulate("individualId",individualId);
            json.accumulate("allergyType",allergyfirstedit.getText().toString());
            json.accumulate("allergyWith",allergysecedit.getText().toString());
            json.accumulate("allergySince",allergythirdedit.getText().toString());
            //System.out.println("json--------------->>>>"+json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = getResources().getString(R.string.apiurl);
        final String result = "";
         url = url+"/allergies/addallergies";


        //System.out.println("url---------------->>>>>>>>>>"+ url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progress.dismiss();
                        //System.out.println("resp------------------->>>>>>>>>>>>>>>>"+result);
                        //System.out.println("response-------------------------------->>>>>>>>>>>>>>>"+response.toString());
                        try {
                           JSONObject serverResp = new JSONObject(response.toString());

                        } catch (JSONException e) {
// TODO Auto-generated catch block
                            progress.dismiss();
                            //System.out.println("cache------------------------------------------>>>>>>>>>>>>>>");
                            e.printStackTrace();
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Allergy details saved successfully.",
                                    Toast.LENGTH_SHORT);
                            toast.show();
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


public void healthdialog(){
    //System.out.println("method called----------------->>>>>>>>>>>>>>>>>>>>.");
    AlertDialog.Builder builder = new AlertDialog.Builder(HealthActivity.this);

    builder.setCancelable(true);
    builder.setTitle("Allergy added successfully");
    builder.setMessage("Do you want to add more allergies?");

    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
//            dialogInterface.cancel();
            allergy.setVisibility(View.GONE);
            allergylistview.setVisibility(View.VISIBLE);
            allergygetdetails();
            allergyviewbtn = true;
        }
    });

    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
//            alertTextView.setVisibility(View.VISIBLE);
            allergy.setVisibility(View.VISIBLE);
            allergyfirstedit.setText("");
            allergysecedit.setText("");
            allergythirdedit.setText("");
            //System.out.println("ok button clicked--------------------->>>>>>>>>");
        }
    });
    builder.show();
}



//    ALLERGIES DETAILS GET API CALL
    public void allergygetdetails(){
        progress = new ProgressDialog(this);
        progress.setMessage("please wait...");
        progress.setCancelable(false);
        progress.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = getResources().getString(R.string.apiurl);
        final String result = "";
         url = url+"/individual/allergy?individualId="+individualId;


        //System.out.println("url---------------->>>>>>>>>>"+ url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progress.dismiss();
                        //System.out.println("resp------------------->>>>>>>>>>>>>>>>"+result);
                        //System.out.println("response-------------------------------->>>>>>>>>>>>>>>"+response.toString());
//                        for(int i=0;i<response.length();i++){
//                            try {
//                                JSONObject jsonObject=response.getJSONObject(i);
//                                items.add(jsonObject.getString("name")+"\n"+jsonObject.getString("speciality"));
////                            imgitem.add(jsonObject.getString("profileImage"));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        adapter.notifyDataSetChanged();
                        try {
                            JSONObject serverResp = new JSONObject(response.toString());

                            JSONObject obj = serverResp.getJSONObject("result");
                            JSONArray objallr = obj.getJSONArray("result");
//                            Object objallr = obj.get("result");
                            for(int i=0;i<objallr.length();i++){
                                JSONObject jsonObject=objallr.getJSONObject(i);
                           items.add(jsonObject.getString("allergySince")+"\n"+jsonObject.getString("allergyType")+"\n"+jsonObject.getString("allergyWith"));

                            }
                            adapter.notifyDataSetChanged();
                            //System.out.println("objallr--------->>>>>>"+objallr);
//                            items.add(objallr.toString());

//                            items.add(objallr.getString("allergySince")+"\n"+objallr.getString("allergyType")+"\n"+objallr.getString("allergyWith"));
                        } catch (JSONException e) {
// TODO Auto-generated catch block
                            progress.dismiss();
                            //System.out.println("cache------------------------------------------>>>>>>>>>>>>>>");
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progress.dismiss();
                //System.out.println("Error getting response------------------------"+error);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


//    POST SURGERY API

    public void postsurgery(View view){

        progress = new ProgressDialog(this);
        progress.setMessage("please wait...");
        progress.setCancelable(false);
        progress.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.accumulate("individualId",individualId);
            json.accumulate("surgery",surgeryfirstedit.getText().toString());
            json.accumulate("dateOfDiagnosis",surgerysecedit.getText().toString());
            json.accumulate("allergySince",allergythirdedit.getText().toString());
            //System.out.println("json--------------->>>>"+json);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = getResources().getString(R.string.apiurl);
        final String result = "";
         url = url+"/surgical/addsurgical";


        //System.out.println("url---------------->>>>>>>>>>"+ url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progress.dismiss();
                        //System.out.println("resp------------------->>>>>>>>>>>>>>>>"+result);
                        //System.out.println("response-------------------------------->>>>>>>>>>>>>>>"+response.toString());
                        try {
                            JSONObject serverResp = new JSONObject(response.toString());

                        } catch (JSONException e) {
// TODO Auto-generated catch block
                            progress.dismiss();
                            //System.out.println("cache------------------------------------------>>>>>>>>>>>>>>");
                            e.printStackTrace();
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "Surgery details saved successfully.",
                                    Toast.LENGTH_SHORT);
                            toast.show();
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

//    SURGERY GET API CALL

    public void surgerygetdetails(){
        progress = new ProgressDialog(this);
        progress.setMessage("please wait...");
        progress.setCancelable(false);
        progress.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = getResources().getString(R.string.apiurl);
        final String result = "";
         url = url+"/individual/surgery?individualId="+individualId;


        //System.out.println("url---------------->>>>>>>>>>"+ url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progress.dismiss();
                        //System.out.println("resp------------------->>>>>>>>>>>>>>>>"+result);
                        //System.out.println("response-------------------------------->>>>>>>>>>>>>>>"+response.toString());
//                        for(int i=0;i<response.length();i++){
//                            try {
//                                JSONObject jsonObject=response.getJSONObject(i);
//                                items.add(jsonObject.getString("name")+"\n"+jsonObject.getString("speciality"));
////                            imgitem.add(jsonObject.getString("profileImage"));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        adapter.notifyDataSetChanged();
                        try {
                            JSONObject serverResp = new JSONObject(response.toString());

                            JSONObject obj = serverResp.getJSONObject("result");
                            JSONArray objallr = obj.getJSONArray("result");
//                            Object objallr = obj.get("result");
                            for(int i=0;i<objallr.length();i++){
                                JSONObject jsonObject=objallr.getJSONObject(i);
                                items.add(jsonObject.getString("surgery")+"\n"+jsonObject.getString("dateOfDiagnosis")+"\n"+jsonObject.getString("allergyWith"));

                            }
                            adapter.notifyDataSetChanged();
                            //System.out.println("objallr--------->>>>>>"+objallr);
//                            items.add(objallr.toString());

//                            items.add(objallr.getString("allergySince")+"\n"+objallr.getString("allergyType")+"\n"+objallr.getString("allergyWith"));
                        } catch (JSONException e) {
// TODO Auto-generated catch block
                            progress.dismiss();
                            //System.out.println("cache------------------------------------------>>>>>>>>>>>>>>");
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progress.dismiss();
                //System.out.println("Error getting response------------------------"+error);
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){

            case R.id.nav_dashboard:
                Intent h= new Intent(HealthActivity.this,DashboardActivity.class);
                startActivity(h);
                break;
            case R.id.nav_consult:
                Intent i= new Intent(HealthActivity.this,ConsultActivity.class);
                startActivity(i);
                break;
            case R.id.nav_appoint:
                Intent g= new Intent(HealthActivity.this,AppointmentActivity.class);
                startActivity(g);
                break;
            case R.id.nav_report:
                Intent s= new Intent(HealthActivity.this,ReportActivity.class);
                startActivity(s);
            case R.id.nav_family:
                Intent t= new Intent(HealthActivity.this,FamilyActivity.class);
                startActivity(t);
                break;
            case R.id.nav_record:
                Intent a= new Intent(HealthActivity.this,HealthActivity.class);
                startActivity(a);
                break;
            case R.id.nav_account:
                Intent b= new Intent(HealthActivity.this,AccountActivity.class);
                startActivity(b);
                break;
            case R.id.nav_profile:
                Intent c= new Intent(HealthActivity.this,ProfileActivity.class);
                startActivity(c);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
