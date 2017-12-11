package me.tnkid.smsserver.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.tnkid.smsserver.myconstant.MyConstant;

/**
 * Created by tom on 12/11/2017.
 */

public class FilterDb extends SQLiteOpenHelper {
    public FilterDb(Context context) {
        super(context, MyConstant.DB2_MY, null, MyConstant.DB_VER);
    }

    public FilterDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String queryFilter = "CREATE TABLE " + MyConstant.TB_FILTER + " ( " + MyConstant.ID_FILTER +
                " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + MyConstant.FILTER_NAME  + " TEXT DEFAULT NULL," + MyConstant.FILTER_NUM + " TEXT )";
        sqLiteDatabase.execSQL(queryFilter);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE EXITS" + MyConstant.TB_FILTER);
    }
}
