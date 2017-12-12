package me.tnkid.smsserver;
import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import me.tnkid.smsserver.fragment.ScoreFragment;
import me.tnkid.smsserver.fragment.FilterFragment;
import me.tnkid.smsserver.fragment.MainFragment;
import me.tnkid.smsserver.myconstant.MyConstant;
import me.tnkid.smsserver.process.Config;


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

        Config config = new Config(this);
        config.isFirstRun();

        Intent irs = getIntent();
        String rsF = "rs_frag";
        int rs = irs.getIntExtra(rsF,0);
        if(rs == MyConstant.RS_SCORE){
            FragmentManager fragmentManager = getFragmentManager();
            ScoreFragment scoreFragment = new ScoreFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.contentfragment, scoreFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }
        else if(rs == MyConstant.RS_FILTER){
            FragmentManager fragmentManager = getFragmentManager();
            FilterFragment filterFragment = new FilterFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.contentfragment,filterFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else{
            FragmentManager fragmentManager = getFragmentManager();
            MainFragment mainFragment = new MainFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.contentfragment, mainFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }


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
            ScoreFragment scoreFragment = new ScoreFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.contentfragment, scoreFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_filter) {
            FragmentManager fragmentManager = getFragmentManager();
            FilterFragment filterFragment = new FilterFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.contentfragment,filterFragment);
            transaction.addToBackStack(null);
            transaction.commit();


        } else if (id == R.id.nav_message_me) {
            String url = "http://t.me/tuoctrund";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);

        } else if (id == R.id.nav_exit) {
            System.exit(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
