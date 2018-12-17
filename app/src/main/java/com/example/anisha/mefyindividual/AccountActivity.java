package com.example.anisha.mefyindividual;

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
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    public static final String MyPREFERENCES = "MyPrefs" ;
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        System.out.println("account-------------------->>>>>>>>>>>>>>>>>>>");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        System.out.println("aassasasa");

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.drawerusername);
        userName = prefs.getString("username","");
        navUsername.setText(userName);
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
                Intent h= new Intent(AccountActivity.this,DashboardActivity.class);
                startActivity(h);
                break;
            case R.id.nav_consult:
                Intent i= new Intent(AccountActivity.this,ConsultActivity.class);
                startActivity(i);
                break;
            case R.id.nav_appoint:
                Intent g= new Intent(AccountActivity.this,AppointmentActivity.class);
                startActivity(g);
                break;
            case R.id.nav_report:
                Intent s= new Intent(AccountActivity.this,ReportActivity.class);
                startActivity(s);
            case R.id.nav_family:
                Intent t= new Intent(AccountActivity.this,FamilyActivity.class);
                startActivity(t);
                break;
            case R.id.nav_record:
                Intent a= new Intent(AccountActivity.this,HealthActivity.class);
                startActivity(a);
                break;
            case R.id.nav_account:
                Intent b= new Intent(AccountActivity.this,AccountActivity.class);
                startActivity(b);
                break;
            case R.id.nav_profile:
                Intent c= new Intent(AccountActivity.this,ProfileActivity.class);
                startActivity(c);
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
