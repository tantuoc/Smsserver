package me.tnkid.smsserver;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import me.tnkid.smsserver.adapter.ScoreAdapter;
import me.tnkid.smsserver.dao.ScoreDAO;
import me.tnkid.smsserver.model.Score;
import me.tnkid.smsserver.process.SMSBroadcastRecevier;


/**
 * Created by tantuoc96 on 12/5/2017.
 */

public class MainActivity extends AppCompatActivity {
    Button them;
    ListView ls;
    ScoreDAO scoredao;
    List<Score> scoreList;
    BaseAdapter scoreAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoredao = new ScoreDAO(getApplicationContext());
        them = findViewById(R.id.themdiem);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent(MainActivity.this,AddActivity.class);
            startActivity(i);

            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SMSBroadcastRecevier mc = new SMSBroadcastRecevier();
                if (!mc.isState()){
                    mc.setState(true);
                    Intent i = new Intent();
                    mc.onReceive(getApplicationContext(),i);
                    Snackbar.make(view, "SMSServer is Turn ON", Snackbar.LENGTH_LONG).show();
                }
               else  if (mc.isState()){
                    mc.setState(false);
                    Intent i = new Intent();
                    mc.onReceive(getApplicationContext(),i);
                    Snackbar.make(view, "SMSServer is turn OFF", Snackbar.LENGTH_LONG).show();
                }
            }
        });*/



        ls = findViewById(R.id.list_score);
        scoreList = scoredao.getScore();
        scoreAdapter = new ScoreAdapter(this,R.layout.list_score,scoreList);
        scoreAdapter.notifyDataSetChanged();
        ls.setAdapter(scoreAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}