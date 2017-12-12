package me.tnkid.smsserver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import me.tnkid.smsserver.R;
import me.tnkid.smsserver.model.NumberFilter;


/**
 * Created by tom on 12/11/2017.
 */

public class FilterAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<NumberFilter> filterList;

    public FilterAdapter(Context context, int layout, List<NumberFilter> filterList) {
        this.context = context;
        this.layout = layout;
        this.filterList = filterList;
    }

    @Override
    public int getCount() {
        return filterList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(layout, viewGroup, false);
        TextView filtername = row.findViewById(R.id.name_filter);
        TextView filternum = row.findViewById(R.id.num_filter);
        filtername.setText("Tên :" + filterList.get(i).getName().toString());
        filternum.setText("SĐT :" + filterList.get(i).getNumber().toString());
        return row;
    }

   /* class ViewHolder {
        TextView filtername, filternum;

    }*/

}