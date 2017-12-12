package me.tnkid.smsserver.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import me.tnkid.smsserver.ScoreActivity;
import me.tnkid.smsserver.R;
import me.tnkid.smsserver.adapter.ScoreAdapter;
import me.tnkid.smsserver.dao.ScoreDAO;
import me.tnkid.smsserver.model.Score;
import me.tnkid.smsserver.myconstant.MyConstant;

/**
 * Created by tom on 12/10/2017.
 */

public class ScoreFragment extends Fragment {
    @Nullable

    Button them;
    ListView lv;
    ScoreDAO scoreDAO;
    List<Score> scoreList;
    BaseAdapter scoreAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        scoreDAO = new ScoreDAO(getContext());
        scoreDAO.open();

        them = view.findViewById(R.id.themdiem);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ScoreActivity.class);
                i.putExtra("rq_s",MyConstant.RQ_SCORE_ADD);
                startActivity(i);

            }
        });

        lv = view.findViewById(R.id.list_score);
        scoreList = scoreDAO.getScore();
        scoreAdapter = new ScoreAdapter(getContext(), R.layout.list_score, scoreList);
        scoreAdapter.notifyDataSetChanged();
        lv.setAdapter(scoreAdapter);
        registerForContextMenu(lv);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_data,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.edit:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Score s = scoreList.get(menuInfo.position);
                Intent i = new Intent(getContext(),ScoreActivity.class);
                i.putExtra("score",s);
                i.putExtra("rq_s",MyConstant.RQ_SCORE_UPDATE);
                startActivity(i);
                break;
            case R.id.del:
                showAlertX(item);
                break;
        }

        return super.onContextItemSelected(item);
    }
    public void showAlertX(final MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Do you want delete item");
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Score sd = scoreList.get(menuInfo.position);
                boolean rs = scoreDAO.delScore(sd);
                if(rs){
                    Toast.makeText(getContext(),"Delete succes!",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getContext(),"Delete false!",Toast.LENGTH_LONG).show();

                }
                scoreList.remove(menuInfo.position);
                scoreAdapter.notifyDataSetChanged();

            }
        });

        builder.setNegativeButton(R.string.cancel, null);
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
