package me.tnkid.smsserver.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.tnkid.smsserver.myconstant.MyConstant;

/**
 * Created by tantuoc96 on 12/5/2017.
 */

public class ScoreDb extends SQLiteOpenHelper {


    public ScoreDb(Context context) {
        super(context, MyConstant.DB_MY, null, MyConstant.DB_VER);
    }

    public ScoreDb(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryScore = "CREATE TABLE " + MyConstant.TB_SCORE + "( " + MyConstant.MA_HS +
                " INTEGER PRIMARY KEY , " + MyConstant.TEN_HS + " TEXT, " +
                MyConstant.D_TOAN + " REAL DEFAULT NULL, " + MyConstant.D_LY + " REAL DEFAULT NULL, " + MyConstant.D_HOA + " REAL DEFAULT NULL)";

        sqLiteDatabase.execSQL(queryScore);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE EXITS" + MyConstant.TB_SCORE);
    }
}
