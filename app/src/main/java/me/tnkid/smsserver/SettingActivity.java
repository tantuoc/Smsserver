package me.tnkid.smsserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import me.tnkid.smsserver.process.Config;

public class SettingActivity extends AppCompatActivity {
    Switch aSwitchs,aSwitchf;
    EditText syntax;
    Button updateSyntax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        aSwitchs = findViewById(R.id.switservice);
        aSwitchf = findViewById(R.id.switfilter);
        syntax = findViewById(R.id.syntax);
        updateSyntax = findViewById(R.id.updatesyntax);
        final Config config = new Config(this);
        String syn = config.getSyntax();

        syntax.setText(syn);


        updateSyntax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            config.updateSyntax(syntax.getText().toString());
                Toast.makeText(getApplicationContext(),"Cập nhật cú pháp thành công!",Toast.LENGTH_SHORT).show();
            }
        });


        if(config.isRunning()){
            aSwitchs.setChecked(true);
        }
        else {
            aSwitchs.setChecked(false);
        }
        if(config.isAllow()){
            aSwitchf.setChecked(true);
        }
        else{
            aSwitchf.setChecked(false);
        }

        aSwitchf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    config.setFilter(true);
                }
                else{
                    config.setFilter(false);
                }
            }
        });



        aSwitchs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    config.setState(true);
                }
                else{
                    config.setState(false);
                }
            }
        });



    }
}
