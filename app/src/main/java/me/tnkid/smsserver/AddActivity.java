package me.tnkid.smsserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import me.tnkid.smsserver.dao.ScoreDAO;
import me.tnkid.smsserver.model.Score;

/**
 * Created by tantuoc96 on 12/5/2017.
 */

public class AddActivity extends AppCompatActivity {
    EditText mhs,tenhs,dtoan,dly,dhoa;
    Button them;
    ScoreDAO scoredao;
    Score s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        scoredao = new ScoreDAO(getApplicationContext());
        mhs = findViewById(R.id.mhs);
        tenhs = findViewById(R.id.tenhs);
        dtoan = findViewById(R.id.dtoan);
        dly = findViewById(R.id.dly);
        dhoa = findViewById(R.id.dhoa);
        them = findViewById(R.id.them);


        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mahs = Integer.parseInt(mhs.getText().toString().trim());
                String name = tenhs.getText().toString().trim();
                float toan = Float.parseFloat(dtoan.getText().toString().trim());
                float ly = Float.parseFloat(dly.getText().toString().trim());
                float hoa = Float.parseFloat(dhoa.getText().toString().trim());
                if(mhs.getText().toString().trim()==null||name==null)
                    Toast.makeText(getApplicationContext(),"Mã HS và Tên HS không được để trống",Toast.LENGTH_LONG).show();
                else {
                    s = new Score(mahs,name,toan,ly,hoa);
                    boolean rs = scoredao.addScore(s);
                    if (!rs) Toast.makeText(getApplicationContext(),"Thêm Lỗi",Toast.LENGTH_LONG).show();
                    else{ Toast.makeText(getApplicationContext(),"Thêm Thành công",Toast.LENGTH_LONG).show();
                      Intent i = new Intent(AddActivity.this,MainActivity.class);
                        startActivity(i);


                    }
                }
            }
        });

    }


}
