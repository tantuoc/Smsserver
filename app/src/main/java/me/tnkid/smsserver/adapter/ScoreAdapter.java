package me.tnkid.smsserver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.tnkid.smsserver.R;
import me.tnkid.smsserver.model.Score;

/**
 * Created by tantuoc96 on 12/5/2017.
 */

public class ScoreAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Score> scoreList;
    public ScoreAdapter(Context context, int layout, List<Score> scores){
        this.context =context;
        this.layout = layout;
        this.scoreList =scores;
    }
    @Override
    public int getCount() {
        return scoreList.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View viewRow = view;
        if(viewRow==null){
            viewRow = inflater.inflate(layout,viewGroup,false);
            ViewHolder holder = new ViewHolder();
            holder.ten =viewRow.findViewById(R.id.ten);
            holder.mhs =viewRow.findViewById(R.id.mahs);
            holder.toan =viewRow.findViewById(R.id.toan);
            holder.ly =viewRow.findViewById(R.id.ly);
            holder.hoa =viewRow.findViewById(R.id.hoa);
            viewRow.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) viewRow.getTag();

        holder.ten.setText("Tên: "+scoreList.get(i).getName());
        holder.mhs.setText("MHS: "+scoreList.get(i).getMHS());
        holder.toan.setText("Toán: "+scoreList.get(i).getdToan());
        holder.ly.setText("Lý: "+scoreList.get(i).getdLy());
        holder.hoa.setText("Hoá: "+scoreList.get(i).getdHoa());
        return viewRow;
    }

    class ViewHolder{
        TextView ten,mhs,toan,ly,hoa;
    }
}
