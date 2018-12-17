package com.example.anisha.mefyindividual;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import static android.view.View.GONE;

public class AppointmentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    LinearLayout appointfirstlayout,appointseclayout,doctordet;
    RelativeLayout appointmentlist;
    TextView appointfirsttv,appointsectv;
    Button appointfirstbtn,appointsecbtn,appointthirdbtn,appointfourthbtn,book;
    public static final String MyPREFERENCES = "MyPrefs" ;
    String userName;
    ProgressDialog progress;
    private List<ListItem> listItems;
//    private RecyclerView.Adapter adapter;
    private ArrayList<String> items;
//    private ArrayList<String>imgitem;
    private ArrayAdapter<String>adapter;
    ListView appointgetlist;
//    String[] Doctorlist;
    String docId,textpos,textdocid;
    private ArrayList<String> data = new ArrayList<String>();

    //    public Integer[] appoint_img = {
//        R.drawable.mefy_logo, R.drawable.mefy_logo
//    };
//    CardView cardview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        System.out.println("appointment-------------------->>>>>>>>>>>>>>>>>>>");
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
        testkey();
        appointfirsttv = (TextView)findViewById(R.id.appointfirsttv);
        appointsectv = (TextView)findViewById(R.id.appointsectv);
        appointfirstbtn = (Button)findViewById(R.id.appointfirstbtn);
        appointsecbtn = (Button)findViewById(R.id.appointsecbtn);
        appointthirdbtn = (Button)findViewById(R.id.appointthirdbtn);
        appointfourthbtn = (Button)findViewById(R.id.appointfourthbtn);
//        book = (Button)findViewById(R.id.book);
        appointfirstlayout = (LinearLayout)findViewById(R.id.appointfirstlayout);
        appointseclayout = (LinearLayout)findViewById(R.id.appointseclayout);
        appointfirstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appointfirstlayout.setVisibility(GONE);
                appointseclayout.setVisibility(View.VISIBLE);
                testkey();
            }
        });


        appointgetlist = (ListView)findViewById(R.id.appointgetlist);

        appointgetlist.setAdapter(new MyListAdaper(AppointmentActivity.this,R.layout.appointlist,data));

            appointgetlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(AppointmentActivity.this,"List item clicked"+position,
                            Toast.LENGTH_SHORT).show();
                }
            });


    }



//ITEM CLICK LISTENER

//    public void setAppointmentlist(RelativeLayout appointmentlist) {
//        this.appointmentlist = appointmentlist;
//        appointmentlist.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("appointment----------------------->>>>>>>>>>>>>>>>>>>>");
//            }
//        });
//    }


    // API GET LIST OF ALL DOCTORS

public void testkey(){
    progress = new ProgressDialog(this);
    progress.setMessage("please wait...");
    progress.setCancelable(false);
    progress.show();
    RequestQueue requestQueue = Volley.newRequestQueue(this);

    String url = getResources().getString(R.string.apiurl);
      url = url+"/doctor";

    System.out.println("url---------------->>>>>>>>>>"+ url);

    final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null,
            new Response.Listener<JSONArray>(){

                @Override
                public void onResponse(JSONArray response) {
                    progress.dismiss();
                    System.out.println("response------------------------------------"+ response.toString());

                    for(int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject=response.getJSONObject(i);
                            docId = jsonObject.getString("doctorId");

                            System.out.println("doctor id----------->>>>>>>"+docId);

                            data.add(jsonObject.getString("name")+"\n"+jsonObject.getString("speciality")+"\n"+jsonObject.getString("address"));
//                            imgitem.add(jsonObject.getString("profileImage"));
//                            SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();
//                            editor.putString("docId",jsonObject.getString("doctorId"));

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
            System.out.println("error-----------------------------------"+ error);
        }
    })

    {
        /* Passing some request headers */
        @Override
        public Map<String,String> getHeaders() {
            System.out.println("header----------------------------------------------------------------------<><><><><><><>");
            HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json");
            headers.put("Accept","*/*");
            return headers;
        }
    };


    int socketTimeout = 30000;//30 seconds - change to what you want
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    jsonArrayRequest.setRetryPolicy(policy);
    System.out.println("wating-----<><><><><><><><><><><><><><><><><><><><>><><><><>><");
    requestQueue.add(jsonArrayRequest);
}

    private class MyListAdaper extends ArrayAdapter<String> {
        private int layout;
        private List<String> mObjects;
        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mainViewholder = null;
            if(convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
//                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);

               viewHolder.appointmentlist = (RelativeLayout)convertView.findViewById(R.id.appointmentlist);
                viewHolder.title = (TextView) convertView.findViewById(R.id.appointmentfirsttv);
                viewHolder.button = (Button) convertView.findViewById(R.id.book);
                convertView.setTag(viewHolder);
//                String item = getItem(Integer.parseInt(docId));
//                viewHolder.button.setText(item);

            }

                mainViewholder = (ViewHolder) convertView.getTag();



            mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Button was clicked for list item " + position +docId, Toast.LENGTH_SHORT).show();
                    textdocid = docId.toString();


//                    System.out.println("id in button------------------>>>>>>>>>>>>"+ iddd);
                    Intent intent = new Intent(AppointmentActivity.this,DoctorList.class);
//                    intent.putExtra("position",textpos.getText().toString());
                    intent.putExtra("docId",textdocid);
                    intent.putExtra("position",position);

                    startActivity(intent);
                }
            });
            mainViewholder.title.setText(getItem(position));
            return convertView;
        }
    }
    public class ViewHolder{
        TextView title,doctorfirsttv;
        Button button;
//        CardView cardview;
        LinearLayout doctordet;
        RelativeLayout appointmentlist;
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
                Intent h= new Intent(AppointmentActivity.this,DashboardActivity.class);
                startActivity(h);
                break;
            case R.id.nav_consult:
                Intent i= new Intent(AppointmentActivity.this,ConsultActivity.class);
                startActivity(i);
                break;
            case R.id.nav_appoint:
                Intent g= new Intent(AppointmentActivity.this,AppointmentActivity.class);
                startActivity(g);
                break;
            case R.id.nav_report:
                Intent s= new Intent(AppointmentActivity.this,ReportActivity.class);
                startActivity(s);
            case R.id.nav_family:
                Intent t= new Intent(AppointmentActivity.this,FamilyActivity.class);
                startActivity(t);
                break;
            case R.id.nav_record:
                Intent a= new Intent(AppointmentActivity.this,HealthActivity.class);
                startActivity(a);
                break;
            case R.id.nav_account:
                Intent b= new Intent(AppointmentActivity.this,AccountActivity.class);
                startActivity(b);
                break;
            case R.id.nav_profile:
                Intent c= new Intent(AppointmentActivity.this,ProfileActivity.class);
                startActivity(c);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}









