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

import me.tnkid.smsserver.FilterActivity;
import me.tnkid.smsserver.R;
import me.tnkid.smsserver.adapter.FilterAdapter;
import me.tnkid.smsserver.dao.FilterDAO;
import me.tnkid.smsserver.model.NumberFilter;
import me.tnkid.smsserver.myconstant.MyConstant;

/**
 * Created by tom on 12/11/2017.
 */

public class FilterFragment extends Fragment {

    Button them;
    ListView lv;
    FilterDAO filterDAO;
    List<NumberFilter> filterList;
    BaseAdapter filterAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_filter,container,false);
        lv = view.findViewById(R.id.filter_list);
        filterDAO = new FilterDAO(getContext());
        filterDAO.open();


        filterList = filterDAO.getFilter();
        filterAdapter = new FilterAdapter(getContext(),R.layout.list_filter,filterList);
        filterAdapter.notifyDataSetChanged();
        lv.setAdapter(filterAdapter);
        registerForContextMenu(lv);

        them = view.findViewById(R.id.add_filter);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), FilterActivity.class);
                i.putExtra("rq_filter", MyConstant.RQ_FILTER_ADD);
                startActivity(i);
            }
        });

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
                NumberFilter nf= filterList.get(menuInfo.position);
                Intent i = new Intent(getContext(),FilterActivity.class);
                i.putExtra("nf",nf);
                i.putExtra("rq_filter", MyConstant.RQ_FILTER_UPDATE);
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
                NumberFilter nf = filterList.get(menuInfo.position);
                filterDAO = new FilterDAO(getContext());
                filterDAO.open();
                boolean rs = filterDAO.delFilter(nf);
                if(rs){
                    Toast.makeText(getContext(),"Delete succes!",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getContext(),"Delete false!",Toast.LENGTH_LONG).show();

                }
                filterList.remove(menuInfo.position);
                filterAdapter.notifyDataSetChanged();

            }
        });

        builder.setNegativeButton(R.string.cancel, null);
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
