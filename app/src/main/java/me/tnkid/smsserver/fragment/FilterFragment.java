package me.tnkid.smsserver.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import me.tnkid.smsserver.R;
import me.tnkid.smsserver.adapter.FilterAdapter;
import me.tnkid.smsserver.dao.FilterDAO;
import me.tnkid.smsserver.model.NumberFilter;

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

        /*NumberFilter ndemo = new NumberFilter("0987654321","Tu Hu");
        filterList = new ArrayList<>();
        filterList.add(ndemo);*/

        filterList = filterDAO.getFilter();
        filterAdapter = new FilterAdapter(getContext(),R.layout.list_filter,filterList);
        filterAdapter.notifyDataSetChanged();
        lv.setAdapter(filterAdapter);

        them = view.findViewById(R.id.add_filter);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

}
