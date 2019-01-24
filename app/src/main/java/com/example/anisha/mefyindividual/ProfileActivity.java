package com.example.anisha.mefyindividual;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static SharedPreferences.Editor bundle;
    public static Bundle myBundle = new Bundle();
    public static final String MyPREFERENCES = "MyPrefs" ;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "ProfileActivity";

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    Button nextbtn,donebtn,skipbtn,nextthirdbtn
            ,skipthirdbtn,nextfourthbtn,skipfourthbtn,nextfifthbtn,skipfifthbtn,nextsixthbtn,
            skipsixthbtn,nextsevenththbtn,skipseventhbtn;
    private ImageView imageview,btn,editbtn,imageviewone;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    String userid,role,individualId,myUserId,userAddr,userPin,username;
    LinearLayout mainprofile,mainfirstprofile,firstlayoutpro,thirdlayoutpro,fourthlayoutpro,buttonslayout
            ,buttonslayoutthird,buttonslayoutfourth,fifthlayoutpro,buttonslayoutfifth,
            sixthlayoutpro,buttonslayoutsixth,seventhlayoutpro,buttonslayoutseventh,
            eightthlayoutpro,buttonslayouteight;
    RelativeLayout imagelayout,imagelayoutone;
    TextView nametxtview,birthtxtview,citytxtview,phntxtview,tvprofirst
            ,streettxtview,pintxtview,marriagetxtview,edtprosixth,drawerusername;
    EditText edtprofirst,edtprothird,edtprofifth,edtproseventh,edtproeighth;
    RadioGroup marrigestatus;
    RadioButton marrige;
    ProgressDialog progress;
    private List<ListItem> listItems;
    Boolean addrsresp = true;
    String userName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //System.out.println("profile-------------------->>>>>>>>>>>>>>>>>>>");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        nextbtn = (Button)findViewById(R.id.nextbtn);
        donebtn = (Button)findViewById(R.id.donebtn);
        skipbtn = (Button)findViewById(R.id.skipbtn);
        nextthirdbtn = (Button)findViewById(R.id.nextthirdbtn);
        skipthirdbtn = (Button)findViewById(R.id.skipthirdbtn);
        nextfourthbtn = (Button)findViewById(R.id.nextfourthbtn);
        skipfourthbtn =(Button)findViewById(R.id.skipfourthbtn);
        nextfifthbtn = (Button)findViewById(R.id.nextfifthbtn);
        skipfifthbtn = (Button)findViewById(R.id.skipfifthbtn);
        nextsixthbtn = (Button)findViewById(R.id.nextsixthbtn);
        skipsixthbtn = (Button)findViewById(R.id.skipsixthbtn);
        nextsevenththbtn = (Button)findViewById(R.id.nextsevenththbtn);
        skipseventhbtn = (Button)findViewById(R.id.skipseventhbtn);

        mainprofile = (LinearLayout)findViewById(R.id.mainprofile);
        firstlayoutpro = (LinearLayout)findViewById(R.id.firstlayoutpro);
        thirdlayoutpro = (LinearLayout)findViewById(R.id.thirdlayoutpro);
        fourthlayoutpro = (LinearLayout)findViewById(R.id.fourthlayoutpro);
        buttonslayout = (LinearLayout)findViewById(R.id.buttonslayout);
        buttonslayoutthird = (LinearLayout)findViewById(R.id.buttonslayoutthird);
        buttonslayoutfourth = (LinearLayout)findViewById(R.id.buttonslayoutfourth);
        mainfirstprofile = (LinearLayout)findViewById(R.id.mainfirstprofile);
        fifthlayoutpro = (LinearLayout)findViewById(R.id.fifthlayoutpro);
        buttonslayoutfifth = (LinearLayout)findViewById(R.id.buttonslayoutfifth);
        sixthlayoutpro = (LinearLayout)findViewById(R.id.sixthlayoutpro);
        buttonslayoutsixth = (LinearLayout)findViewById(R.id.buttonslayoutsixth);
        seventhlayoutpro = (LinearLayout)findViewById(R.id.seventhlayoutpro);
        buttonslayoutseventh = (LinearLayout)findViewById(R.id.buttonslayoutseventh);
        eightthlayoutpro = (LinearLayout)findViewById(R.id.eightthlayoutpro);
        buttonslayouteight = (LinearLayout)findViewById(R.id.buttonslayouteight);

        imagelayout = (RelativeLayout) findViewById(R.id.imagelayout);
        imagelayoutone = (RelativeLayout) findViewById(R.id.imagelayoutone);


        nametxtview = (TextView)findViewById(R.id.nametxtview);
        birthtxtview = (TextView)findViewById(R.id.birthtxtview);
        citytxtview = (TextView)findViewById(R.id.citytxtview);
        phntxtview = (TextView)findViewById(R.id.phntxtview);
        tvprofirst = (TextView)findViewById(R.id.tvprofirst);
        streettxtview = (TextView)findViewById(R.id.streettxtview);
        pintxtview = (TextView)findViewById(R.id.pintxtview);
        marriagetxtview = (TextView)findViewById(R.id.marriagetxtview);
        edtprosixth = (TextView)findViewById(R.id.edtprosixth);
        drawerusername = (TextView)findViewById(R.id.drawerusername);

        edtprofirst = (EditText)findViewById(R.id.edtprofirst);
        edtprothird = (EditText)findViewById(R.id.edtprothird);
        edtprofifth = (EditText)findViewById(R.id.edtprofifth);
        edtproseventh =(EditText)findViewById(R.id.edtproseventh);
        edtproeighth = (EditText)findViewById(R.id.edtproeighth);

        imageview = (ImageView)findViewById(R.id.imageview);
        editbtn = (ImageView) findViewById(R.id.editbtn);
        btn = (ImageView) findViewById(R.id.btn);
        imageviewone = (ImageView)findViewById(R.id.imageviewone);

        marrigestatus = (RadioGroup)findViewById(R.id.marrigestatus);




        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

         individualId = prefs.getString("individualId", "");
         userid = prefs.getString("userId","");
         myUserId = prefs.getString("myUserId","");
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.drawerusername);
        userName = prefs.getString("username","");
        //System.out.println("username----------------->>>>>>>>>>>>>>>>>>>"+userName);
        navUsername.setText(userName);
        role = "individual";
        //System.out.println("id------------------------->>"+individualId);
        //System.out.println("userId-------------->>>>>>>>"+userid);
        //System.out.println("myUserId---------->>>>>>>>>>>>>>"+myUserId);
        //System.out.println("username------------>>>>"+username);
        CheckConnectionStatus();
        Bundle extras = getIntent().getExtras();

//       ------------- PROFILE PAGE EDIT VISIBILITY APPLIED ------------
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainprofile.setVisibility(View.GONE);
                mainfirstprofile.setVisibility(View.VISIBLE);
                firstlayoutpro.setVisibility(View.VISIBLE);

            }
        });


//    ----------------DATE PICKER DIALOG APPLIED FOR THE BIRTH VALIDATION ------------
//        drawerusername.setText(username);
        edtprosixth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(ProfileActivity.this,
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
                edtprosixth.setText(date);
            }
        };

//       ---------------- ADDRESS PAGE NEXT BUTTON ON CLICK VALIDATION-------------------
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addrvalidates());
            }
        });

//       ------------------ PIN PAGE NEXT ON CLICK VALIDATON -----------------------

        nextthirdbtn.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pinvalidate());
            }
        });

//       --------------- NAME PAGE NEXT BUTTON ON CLICK VALIDATE-----------------

        nextfourthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validates());
            }
        });

//      ----------------  BIRTH PAGE NEXT BUTTON ON CLICK VALIDATE ----------------

        nextfifthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datevalidate());
            }
        });

//      ----------------  CITY PAGE NEXT BUTTON ON CLICK VALIDATE ----------------

        nextsixthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cityvalidate());
            }
        });

//      ----------------  PHONE NUMBER PAGE NEXT BUTTON ON CLICK VALIDATE ----------------

        nextsevenththbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numbervalidate());
            }
        });


        skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });


        skipthirdbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });

        skipfourthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });

        skipfifthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });

        skipsixthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });
        skipseventhbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });

//     ------------------   MARRITAL STATE RADIO VALIDATE ----------------

        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radiovalidate());
                addrsresp=false;
                UpdateStatus();

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPictureDialog();
            }
        });

        //System.out.println("oncreate----------------------->>>>>>>>>>>>>>.");
    }



    @Override
    protected void onResume() {
        super.onResume();
        //System.out.println("resume---------->>>>>>>>>>>");
    }

    // +++++++++++++++++ADDRESS VALIDATION+++++++++++++++++


    private  boolean addrvalidates(){
        boolean result = false;

        String addr = edtprofirst.getText().toString();
        //System.out.println("captured data----------->>>>>>" +addr);
        if(addr.isEmpty()){
            Toast.makeText(this,"please enter the " +
                    "address",Toast.LENGTH_SHORT ).show();
        }
        else {
            ProfileActivity.myBundle.putString("Addr" ,addr);
            firstlayoutpro.setVisibility(View.GONE);
            thirdlayoutpro.setVisibility(View.VISIBLE);
            result = true;
        }
        return result;
    }

    // PIN CODE VALIDATION

    private boolean pinvalidate(){
        boolean pinres = false;
        String pin = edtprothird.getText().toString();
        //System.out.println("captured data----------->>>>>>" +pin);

        if(pin.isEmpty()){
            Toast.makeText(this,"please enter the " +
                    "Pincode",Toast.LENGTH_SHORT ).show();
        }
        else  {
            ProfileActivity.myBundle.putString("Pin" ,pin);
            thirdlayoutpro.setVisibility(View.GONE);
            fifthlayoutpro.setVisibility(View.VISIBLE);
            pinres = true;
        }
        return pinres;
    }

//    NAME VALIDATION

    private  boolean validates(){
        boolean result = false;

        String name = edtprofifth.getText().toString();
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
            ProfileActivity.myBundle.putString("Name" ,name);
            fifthlayoutpro.setVisibility(View.GONE);
            sixthlayoutpro.setVisibility(View.VISIBLE);
            result = true;
        }
        return result;
    }
    // DATE VALIDATION

    private boolean datevalidate(){
        boolean birth = false;
        String dateOfbirth = edtprosixth.getText().toString();
        if(dateOfbirth.isEmpty()){
            Toast.makeText(this,"please enter the " +
                    "date of birth",Toast.LENGTH_SHORT ).show();
        }
        else{
            ProfileActivity.myBundle.putString("Birth" , String.valueOf(dateOfbirth));
            sixthlayoutpro.setVisibility(View.GONE);
            seventhlayoutpro.setVisibility(View.VISIBLE);
            birth = true;
        }
        return birth;
    }
    // CITY VALIDATION

    private boolean cityvalidate(){
        boolean city = false;
        String place = edtproseventh.getText().toString();
        if(place.isEmpty()){
            Toast.makeText(this,"please enter the " +
                    "city",Toast.LENGTH_SHORT ).show();
        }
        else{
            ProfileActivity.myBundle.putString("City" , String.valueOf(place));
            seventhlayoutpro.setVisibility(View.GONE);
            eightthlayoutpro.setVisibility(View.VISIBLE);
            city = true;
        }
        return city;
    }
//    PHONE NUMBER VALIDATE

    private boolean numbervalidate(){
        boolean number = false;
        String nmbr = edtproeighth.getText().toString();
        if (nmbr.isEmpty()){
            Toast.makeText(this,"please enter the " +
                    "phone number",Toast.LENGTH_SHORT ).show();
        }
        else{
            ProfileActivity.myBundle.putString("City" , String.valueOf(nmbr));
            eightthlayoutpro.setVisibility(View.GONE);
            fourthlayoutpro.setVisibility(View.VISIBLE);
            number = true;
        }
        return number;
    }
    // MARRIED RADIO BUTTON VALIDATION

    private boolean radiovalidate(){
        boolean radio = false;
        int radiogender = marrigestatus.getCheckedRadioButtonId();
        marrige = (RadioButton) findViewById(radiogender);
        //System.out.println("captured data----------->>>>>>" +marrige);
        //System.out.println("captured data----------->>>>>>" +radiogender);

        Toast.makeText(ProfileActivity.this,
                marrige.getText().toString(), Toast.LENGTH_SHORT).show();


        if(marrige.isDirty()){
            Toast.makeText(this,"Please click one of the" +
                    " option",Toast.LENGTH_SHORT ).show();
        }
        else {
            RegistrationActivity.myBundle.putString("sex" , marrige.getText().toString());
//            mainprofile.setVisibility(View.VISIBLE);
            firstlayoutpro.setVisibility(View.GONE);
            thirdlayoutpro.setVisibility(View.GONE);
            fourthlayoutpro.setVisibility(View.GONE);
            radio = true;
        }
        return radio;
    }

//    USER PROFILE GET API CALL

    public void CheckConnectionStatus(){
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
                                String userName = serverResp.getString("name");
                                String userCity = serverResp.getString("city");
                                String userGen = serverResp.getString("gender");
                                String userDob = serverResp.getString("dob");
                                String userPhn = serverResp.getString("phoneNumber");


                                //System.out.println("name------------->>>>"+serverResp.getString("name"));
                                //System.out.println("city-------------->>>"+serverResp.getString("city"));
                                //System.out.println("success result:------------------------->>>>>>>>>>>>> " + serverResp);
                                nametxtview.setText(userName);
                                birthtxtview.setText(userDob);
                                citytxtview.setText(userCity);
                                phntxtview.setText(userPhn);
                                edtprofifth.setText(userName);
                                edtprosixth.setText(userDob);
                                edtproseventh.setText(userCity);
                                edtproeighth.setText(userPhn);

                            if (addrsresp == false){
                                String userAddr = serverResp.getString("address");
                                String userPin = serverResp.getString("pin");
                                String userMairriage = serverResp.getString("married");
                                //System.out.println("marriage---------->>>"+userMairriage);
                                streettxtview.setText(userAddr);
                                pintxtview.setText(userPin);
                                edtprofirst.setText(userAddr);
                                edtprothird.setText(userPin);
                                marriagetxtview.setText(userMairriage);
                            }
                            else {
                                streettxtview.setText("Street address");
                                edtprofirst.setText("Street address");
                                pintxtview.setText("pin");
                                edtprothird.setText("pin");
                                marriagetxtview.setText("marriage status");

                            }
//                            String userMairriage = serverResp.getString("married");
//                            marriagetxtview.setText(userMairriage);
//                            marrige.setText(userMairriage);

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

//USER PROFILE UPDATE (PUT) API CALL

    public void UpdateStatus(){
        progress = new ProgressDialog(this);
        progress.setMessage("please wait...");
        progress.setCancelable(false);
        progress.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JSONObject json = new JSONObject();
        try {
            json.put("name",edtprofifth.getText().toString());
            json.put("dob",edtprosixth.getText().toString());
            json.put("city",edtproseventh.getText().toString());
            json.put("phoneNumber",edtproeighth.getText().toString());
            json.put("address",edtprofirst.getText().toString());
            json.put("pin",edtprothird.getText().toString());
            json.put("married",marrige.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = getResources().getString(R.string.apiurl);
        final String result = "";
         url = url+"/individual/profile/"+myUserId;


        //System.out.println("url---------------->>>>>>>>>>"+ url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url,json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //System.out.println("resp------------------->>>>>>>>>>>>>>>>"+result);
                        progress.dismiss();
                        //System.out.println("response-------------------------------->>>>>>>>>>>>>>>"+response.toString());
                        mainprofile.setVisibility(View.VISIBLE);
                        mainfirstprofile.setVisibility(View.GONE);
                        CheckConnectionStatus();
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progress.dismiss();
                //System.out.println("Error getting response------------------------"+error);
            }
        }) {

            @Override
            public Map<String, String> getHeaders()
            {
                Map<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json");
                //or try with this:
                //headers.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
                return headers;
            }
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
            @Override
            public byte[] getBody() {

                try {
                    Log.i("json", json.toString());
                    return json.toString().getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("name",edtprofifth.getText().toString() );
//                params.put("username", "tester3");
//                params.put("token", "blah");
//                return params;
//            }
        };

        requestQueue.add(jsonObjectRequest);
    }


    // DIALOG SECTION
    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(ProfileActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageviewone.setImageBitmap(bitmap);
                    //System.out.println("path------------->>>"+path.toString());
                    //System.out.println("imagepath-------->>>>"+imageviewone);
                    //System.out.println("bitmap------->>>>"+bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ProfileActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageviewone.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(ProfileActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            //System.out.println("getPath--------------->>>>>>>>>>>"+f);
            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
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
                Intent h= new Intent(ProfileActivity.this,DashboardActivity.class);
                startActivity(h);
                break;
            case R.id.nav_consult:
                Intent i= new Intent(ProfileActivity.this,ConsultActivity.class);
                startActivity(i);
                break;
            case R.id.nav_appoint:
                Intent g= new Intent(ProfileActivity.this,AppointmentActivity.class);
                startActivity(g);
                break;
            case R.id.nav_report:
                Intent s= new Intent(ProfileActivity.this,ReportActivity.class);
                startActivity(s);
            case R.id.nav_family:
                Intent t= new Intent(ProfileActivity.this,FamilyActivity.class);
                startActivity(t);
                break;
            case R.id.nav_record:
                Intent a= new Intent(ProfileActivity.this,HealthActivity.class);
                startActivity(a);
                break;
            case R.id.nav_account:
                Intent b= new Intent(ProfileActivity.this,AccountActivity.class);
                startActivity(b);
                break;
            case R.id.nav_profile:
                Intent c= new Intent(ProfileActivity.this,ProfileActivity.class);
                startActivity(c);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
