package com.example.anisha.mefyindividual;

import android.app.ProgressDialog;
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
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class DashboardActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    Button Dashfirstbtn,Dashthirdbtn;
    String userid,role,individualId,myUserId,userAddr,userPin,username;
    public static final String MyPREFERENCES = "MyPrefs" ;
    TextView drawerusername;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView wishtext = (TextView)findViewById(R.id.wishtext);
        Dashfirstbtn = (Button)findViewById(R.id.Dashfirstbtn);
        Dashfirstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,HealthActivity.class);
                startActivity(intent);
            }
        });
//        Dashthirdbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DashboardActivity.this,AppointmentActivity.class);
//                startActivity(intent);
//            }
//        });

        System.out.println("view dashboard--------------->>>>>>>>>>>>>>>>>");

        // NAVIGATION DRAWER
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView tvdash1 = (TextView)findViewById(R.id.tvlike);
        drawerusername = (TextView)findViewById(R.id.drawerusername);

        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        individualId = prefs.getString("individualId", "");
        userid = prefs.getString("userId","");
        myUserId = prefs.getString("myUserId","");
        role = "individual";
        System.out.println("id------------------------->>"+individualId);
        System.out.println("userId-------------->>>>>>>>"+userid);
        System.out.println("myUserId---------->>>>>>>>>>>>>>"+myUserId);
        getProfile();
    }
    public void intentappoint(View view){
        Intent intent = new Intent(DashboardActivity.this,AppointmentActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
                Intent h= new Intent(DashboardActivity.this,DashboardActivity.class);
                startActivity(h);
                break;
            case R.id.nav_consult:
                Intent i= new Intent(DashboardActivity.this,ConsultActivity.class);
                startActivity(i);
                break;
            case R.id.nav_appoint:
                Intent g= new Intent(DashboardActivity.this,AppointmentActivity.class);
                startActivity(g);
                break;
            case R.id.nav_report:
                Intent s= new Intent(DashboardActivity.this,ReportActivity.class);
                startActivity(s);
            case R.id.nav_family:
                Intent t= new Intent(DashboardActivity.this,FamilyActivity.class);
                startActivity(t);
                break;
            case R.id.nav_record:
                Intent a= new Intent(DashboardActivity.this,HealthActivity.class);
                startActivity(a);
                break;
            case R.id.nav_account:
                Intent b= new Intent(DashboardActivity.this,AccountActivity.class);
                startActivity(b);
                break;
            case R.id.nav_profile:
                Intent c= new Intent(DashboardActivity.this,ProfileActivity.class);
                startActivity(c);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //Get Profile activity....
    public void getProfile(){
        progress = new ProgressDialog(this);
        progress.setMessage("please wait...");
        progress.setCancelable(false);
        progress.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();

//        String url = getResources().getString(R.string.json_get_url);
        final String result = "";
        String url = "http://ec2-13-232-207-92.ap-south-1.compute.amazonaws.com:5023/api/individual/"+individualId;


        System.out.println("url---------------->>>>>>>>>>"+ url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progress.dismiss();
                        System.out.println("resp------------------->>>>>>>>>>>>>>>>"+result);
                        System.out.println("response-------------------------------->>>>>>>>>>>>>>>"+response.toString());

                        try {
                            JSONObject serverResp = new JSONObject(response.toString());
                            String userName = serverResp.getString("name");


                            System.out.println("name------------->>>>"+ userName);

                            View headerView = navigationView.getHeaderView(0);
                            TextView navUsername = (TextView) headerView.findViewById(R.id.drawerusername);
                            navUsername.setText(userName);


                            progress.dismiss();
                        } catch (JSONException e) {
// TODO Auto-generated catch block
                            progress.dismiss();
                            System.out.println("cache------------------------------------------>>>>>>>>>>>>>>");
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progress.dismiss();
                System.out.println("Error getting response------------------------");
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

}
