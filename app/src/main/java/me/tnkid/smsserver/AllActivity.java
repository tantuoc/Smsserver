package me.tnkid.smsserver;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import me.tnkid.smsserver.fragment.DataFragment;
import me.tnkid.smsserver.fragment.FilterFragment;
import me.tnkid.smsserver.fragment.MainFragment;
import me.tnkid.smsserver.myconstant.MyConstant;


public class AllActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_all);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        /*Intent irs = getIntent();
        int rs = irs.getIntExtra("rs_frag",MyConstant.RS_SCORE);
        if(rs == MyConstant.RS_SCORE){
            FragmentManager fragmentManager = getFragmentManager();
            DataFragment dataFragment = new DataFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.contentfragment, dataFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        if(rs == MyConstant.RS_FILTER){
            FragmentManager fragmentManager = getFragmentManager();
            FilterFragment filterFragment = new FilterFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.contentfragment,filterFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }*/


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
        getMenuInflater().inflate(R.menu.all, menu);
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
            Intent ist = new Intent(this,SettingActivity.class);
            startActivity(ist);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            FragmentManager fragmentManager = getFragmentManager();
            MainFragment mainFragment = new MainFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.contentfragment, mainFragment);
            transaction.addToBackStack(null);
            transaction.commit();


        } else if (id == R.id.nav_data) {
            FragmentManager fragmentManager = getFragmentManager();
            DataFragment dataFragment = new DataFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.contentfragment, dataFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_filter) {
            FragmentManager fragmentManager = getFragmentManager();
            FilterFragment filterFragment = new FilterFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.contentfragment,filterFragment);
            transaction.addToBackStack(null);
            transaction.commit();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_exit) {
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}