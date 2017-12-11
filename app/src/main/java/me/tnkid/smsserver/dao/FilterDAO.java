package me.tnkid.smsserver.dao;

/**
 * Created by tom on 12/5/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.tnkid.smsserver.database.FilterDb;

import me.tnkid.smsserver.model.NumberFilter;
import me.tnkid.smsserver.myconstant.MyConstant;







/**
 * Created by tantuoc96 on 12/5/2017.
 */

public class FilterDAO {
    private FilterDb sqLite;
    private SQLiteDatabase sqLiteDatabase;


    private String[] coLumsFilter = { MyConstant.FILTER_NAME, MyConstant.FILTER_NUM};

    public FilterDAO(Context context) {
        sqLite = new FilterDb(context);

    }

    public void open() {
        sqLiteDatabase = sqLite.getWritableDatabase();

    }

    public void close() {
        sqLite.close();

    }


    public boolean addFilter(NumberFilter n){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyConstant.FILTER_NAME,n.getName());
        contentValues.put(MyConstant.FILTER_NUM,n.getNumber());
        long rs = sqLiteDatabase.insert(MyConstant.TB_FILTER,null,contentValues);
        close();
        if (rs!=0) return true;
        return  false;

    }
    public List<NumberFilter> getFilter(){
        NumberFilter n;
        List<NumberFilter> ln = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(MyConstant.TB_FILTER ,coLumsFilter,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String fnum = cursor.getString(cursor.getColumnIndex(MyConstant.FILTER_NUM));
            String fname = cursor.getString(cursor.getColumnIndex(MyConstant.FILTER_NAME));
            n = new NumberFilter(fnum,fname);
            ln.add(n);
            cursor.moveToNext();
        }
        close();
        return ln;
    }
}
