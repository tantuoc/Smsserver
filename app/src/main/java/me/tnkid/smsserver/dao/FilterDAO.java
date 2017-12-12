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


    private String[] coLumsFilter = {MyConstant.ID_FILTER, MyConstant.FILTER_NAME, MyConstant.FILTER_NUM};

    public FilterDAO(Context context) {
        sqLite = new FilterDb(context);

    }

    public void open() {
        sqLiteDatabase = sqLite.getWritableDatabase();

    }

    public void close() {
        sqLite.close();

    }


    public boolean addFilter(NumberFilter n) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyConstant.FILTER_NAME, n.getName());
        contentValues.put(MyConstant.FILTER_NUM, n.getNumber());
        long rs = sqLiteDatabase.insert(MyConstant.TB_FILTER, null, contentValues);
        close();
        if (rs != 0) return true;
        return false;

    }

    public boolean editFilter(NumberFilter n) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyConstant.FILTER_NAME, n.getName());
        contentValues.put(MyConstant.FILTER_NUM, n.getNumber());

        long result = sqLiteDatabase.update(MyConstant.TB_FILTER, contentValues, MyConstant.FILTER_NUM + " = " + n.getNumber(), null);
        close();
        if (result != 0) return true;
        return false;

    }

    public List<NumberFilter> getFilter() {
        NumberFilter n;
        List<NumberFilter> ln = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(MyConstant.TB_FILTER, coLumsFilter, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(MyConstant.ID_FILTER));
            String fnum = cursor.getString(cursor.getColumnIndex(MyConstant.FILTER_NUM));
            String fname = cursor.getString(cursor.getColumnIndex(MyConstant.FILTER_NAME));
            n = new NumberFilter(id, fname, fnum);
            ln.add(n);
            cursor.moveToNext();
        }
        close();
        return ln;
    }

    public boolean delFilter(NumberFilter n) {
        long rs = sqLiteDatabase.delete(MyConstant.TB_FILTER, MyConstant.ID_FILTER + " = " + n.getId(), null);
        close();
        if (rs != 0) return true;
        return false;

    }

    public boolean findNum(String number) {

        Cursor cur = sqLiteDatabase.query(MyConstant.TB_FILTER, coLumsFilter, null, null, null, null, null);
        cur.moveToFirst();

        while (!cur.isAfterLast()) {
            String num = cur.getString(cur.getColumnIndex(MyConstant.FILTER_NUM));
            String numm = "+84"+num.trim().substring(1);
            if (number.trim().equalsIgnoreCase(num.trim())||number.trim().equalsIgnoreCase(numm.trim()))
                return true;
            else
                cur.moveToNext();
        }
        close();
        return false;
    }
}
