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
import me.tnkid.smsserver.myconstant.MyConstant;

/**
 * Created by tantuoc96 on 12/5/2017.
 */

public class ScoreActivity extends AppCompatActivity {
    EditText mhs, tenhs, dtoan, dly, dhoa;
    Button them;
    ScoreDAO scoreDAO;
    Score s;
    String rqSc = "rq_s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreDAO = new ScoreDAO(getApplicationContext());
        scoreDAO.open();
        mhs = findViewById(R.id.mhs);
        tenhs = findViewById(R.id.tenhs);
        dtoan = findViewById(R.id.dtoan);
        dly = findViewById(R.id.dly);
        dhoa = findViewById(R.id.dhoa);
        them = findViewById(R.id.them);
        Intent i = getIntent();
        if (i.getExtras().getInt(rqSc) == MyConstant.RQ_SCORE_UPDATE) {
            mhs.setCursorVisible(false);
            mhs.setClickable(false);
            them.setText("Update");
            Score s = (Score) i.getSerializableExtra("score");
            mhs.setText(s.getMHS() + "");
            tenhs.setText(s.getName());
            dtoan.setText(s.getdToan() + "");
            dly.setText(s.getdLy() + "");
            dhoa.setText(s.getdHoa() + "");
        } else if (i.getExtras().getInt(rqSc) == MyConstant.RQ_SCORE_ADD) {
            them.setText("Add");
        }


        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                if (i.getIntExtra(rqSc,0) == MyConstant.RQ_SCORE_UPDATE) {
                    int mh = Integer.parseInt(mhs.getText().toString());
                    String ths = tenhs.getText().toString();
                    float dt = Float.parseFloat(dtoan.getText().toString());
                    float dl = Float.parseFloat(dly.getText().toString());
                    float dh = Float.parseFloat(dhoa.getText().toString());
                    if (mhs.getText() != null && tenhs.getText() != null) {
                        Score s = new Score(mh, ths, dt, dl, dh);
                        boolean rs = scoreDAO.editScore(s);
                        if (!rs)
                            Toast.makeText(getApplicationContext(), "Lỗi!", Toast.LENGTH_LONG).show();

                        else {
                            Toast.makeText(getApplicationContext(), "Cập nhật thành công!", Toast.LENGTH_LONG).show();
                            Intent ir = new Intent(ScoreActivity.this, AllActivity.class);
                            ir.putExtra("rs_frag", MyConstant.RS_SCORE);
                            startActivity(ir);
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Dữ liêu bắt buộc không thể bỏ trống!", Toast.LENGTH_LONG).show();
                    }


                } else if (i.getExtras().getInt(rqSc) == MyConstant.RQ_SCORE_ADD) {
                    if (mhs.getText() != null && tenhs.getText() != null&&dtoan.getText().toString()!=null&&dly.getText().toString()!=null&&dhoa.getText().toString()!=null) {
                        s = new Score(Integer.parseInt(mhs.getText().toString()), tenhs.getText().toString(), Float.parseFloat(dtoan.getText().toString()), Float.parseFloat(dly.getText().toString()), Float.parseFloat(dhoa.getText().toString()));
                        Toast.makeText(ScoreActivity.this, "ĐM!", Toast.LENGTH_LONG).show();
                        boolean rs = scoreDAO.addScore(s);
                        if (!rs)
                            Toast.makeText(ScoreActivity.this, "Thêm Lỗi!", Toast.LENGTH_LONG).show();
                        else {
                            Toast.makeText(ScoreActivity.this, "Thêm Thành công!", Toast.LENGTH_LONG).show();
                            Intent irs = new Intent(ScoreActivity.this, AllActivity.class);
                            irs.putExtra("rs_frag", MyConstant.RS_SCORE);
                            startActivity(irs);
                            finish();
                        }
                    } else {
                        Toast.makeText(ScoreActivity.this, "Không được để trống!", Toast.LENGTH_LONG).show();
                    }

                }
                i.removeExtra("rq_s");
            }
        });

    }


}
