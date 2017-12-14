package me.tnkid.smsserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.tnkid.smsserver.dao.FilterDAO;
import me.tnkid.smsserver.model.NumberFilter;
import me.tnkid.smsserver.myconstant.MyConstant;

public class FilterActivity extends AppCompatActivity {
        EditText filterName,filterNum;
        Button addFilter;
         String rsF = "rs_frag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        filterName = findViewById(R.id.add_filtername);
        filterNum = findViewById(R.id.add_filternum);
        addFilter = findViewById(R.id.add_filter);

        Intent i = getIntent();
        int rq_filter = i.getIntExtra("rq_filter",0);

        if (rq_filter==MyConstant.RQ_FILTER_ADD) {
        addFilter.setText("Add");
        }
        if(rq_filter==MyConstant.RQ_FILTER_UPDATE){
            addFilter.setText("Update");
            NumberFilter nf = (NumberFilter) i.getSerializableExtra("nf");
            filterName.setText(nf.getName().toString());
            filterNum.setText(nf.getNumber().toString());

        }



        addFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterDAO filterDAO = new FilterDAO(getApplicationContext());
                filterDAO.open();
                Intent i = getIntent();

                int rq_filter = i.getIntExtra("rq_filter",0);
                if (rq_filter==MyConstant.RQ_FILTER_ADD) {
                    String fname = filterName.getText().toString();
                    String fnum =filterNum.getText().toString();
                    if(fnum!=null) {
                        NumberFilter n = new NumberFilter(0,fname,fnum);
                        boolean rs =filterDAO.addFilter(n);
                        if(rs){
                        Intent iu = new Intent(FilterActivity.this,AllActivity.class);
                        iu.putExtra(rsF, MyConstant.RS_FILTER);
                        startActivity(iu);
                        finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Lỗi!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else Toast.makeText(getApplicationContext(),"Không được để trống!",Toast.LENGTH_SHORT).show();
                }


                if(rq_filter==MyConstant.RQ_FILTER_UPDATE){

                    String fname = filterName.getText().toString();
                    String fnum =filterNum.getText().toString();
                    if(fnum!=null) {
                        NumberFilter nf = (NumberFilter) i.getSerializableExtra("nf");
                        NumberFilter n = new NumberFilter(nf.getId(),fname,fnum);
                        boolean rs =filterDAO.editFilter(n);
                        if(rs){
                            Toast.makeText(getApplicationContext(),"Cập nhật thành công!",Toast.LENGTH_SHORT).show();
                            Intent iu = new Intent(FilterActivity.this,AllActivity.class);
                            iu.putExtra(rsF, MyConstant.RS_FILTER);
                            startActivity(iu);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Lỗi!",Toast.LENGTH_SHORT).show();
                        }
                    }
                   else Toast.makeText(getApplicationContext(),"Không được để trống!",Toast.LENGTH_SHORT).show();

                }

                i.removeExtra("rq_filter");

            }
        });

    }


}
