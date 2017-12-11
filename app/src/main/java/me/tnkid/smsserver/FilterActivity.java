package me.tnkid.smsserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import me.tnkid.smsserver.dao.FilterDAO;
import me.tnkid.smsserver.model.NumberFilter;

public class FilterActivity extends AppCompatActivity {
        EditText filterName,filterNum;
        Button addFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        filterName = findViewById(R.id.add_filtername);
        filterNum = findViewById(R.id.add_filternum);

        addFilter = findViewById(R.id.add_filter);


        addFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FilterDAO filterDAO = new FilterDAO(getApplicationContext());
                filterDAO.open();
                String fname = filterName.getText().toString();
                String fnum =filterNum.getText().toString();
                if(fnum!=null) {
                    NumberFilter n = new NumberFilter(1,fname,fnum);
                    filterDAO.addFilter(n);
                    Intent i = new Intent(FilterActivity.this,AllActivity.class);
                    startActivity(i);
                }
            }
        });


    }
}
