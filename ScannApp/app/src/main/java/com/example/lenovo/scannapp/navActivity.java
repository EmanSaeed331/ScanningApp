package com.example.lenovo.scannapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.lenovo.scannapp.fragments.Broadcast;
import com.example.lenovo.scannapp.fragments.analysis;
import com.example.lenovo.scannapp.fragments.logout;
import com.example.lenovo.scannapp.fragments.pdfCeartor;
import com.example.lenovo.scannapp.fragments.updates;


public class navActivity extends AppCompatActivity implements navInter, NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle myToogle;
    public DrawerLayout drawerLayout;

    private Toolbar toolbar;


    @SuppressLint({"NewApi", "ResourceAsColor"})
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nav);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.nvd_act_main);
        myToogle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(myToogle);
        myToogle.syncState();
        myToogle.getDrawerArrowDrawable().setColor(getColor(R.color.white));
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.nvd_act_main);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (myToogle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;


        if (id == R.id.analysisID) {
            fragment = new analysis();
        }
        ;

        if (id == R.id.broadcastID) {
            fragment = new Broadcast();
        }
        ;
        if (id == R.id.pdfCreatorID) {
            fragment = new pdfCeartor();

            /*
            * */


        }
        ;
        if (id == R.id.updatesID) {
            fragment = new updates();
        }
        ;
        if (id == R.id.logoutID) {

            fragment = new logout();
        }
        ;
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.sourceFrame, fragment);
            fragmentTransaction.commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.nvd_act_main);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}