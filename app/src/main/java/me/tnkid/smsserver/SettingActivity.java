package me.tnkid.smsserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import me.tnkid.smsserver.process.Config;

public class SettingActivity extends AppCompatActivity {
    Switch aSwitchs,aSwitchf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        aSwitchs = findViewById(R.id.switservice);

        Config config = new Config(this);
        if(config.isRunning()){
            aSwitchs.setChecked(true);
        }
        else {
            aSwitchs.setChecked(false);
        }

        aSwitchs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    Config config = new Config(getApplicationContext());
                    config.setState(true);
                }
                else{
                    Config config = new Config(getApplicationContext());
                    config.setState(false);
                }
            }
        });
    }
}
