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

import me.tnkid.smsserver.database.ScoreDb;
import me.tnkid.smsserver.myconstant.MyConstant;
import me.tnkid.smsserver.model.Score;






/**
 * Created by tantuoc96 on 12/5/2017.
 */

public class ScoreDAO {
    private ScoreDb sqLite;
    private SQLiteDatabase sqLiteDatabase;
    private String[] coLums = {MyConstant.MA_HS, MyConstant.TEN_HS,
            MyConstant.D_TOAN, MyConstant.D_LY, MyConstant.D_HOA};

    public ScoreDAO(Context context) {
        sqLite = new ScoreDb(context);
        open();

    }
    public void open() {
        sqLiteDatabase = sqLite.getWritableDatabase();

    }

    public void close() {
        sqLite.close();

    }

    public boolean addScore(Score s){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyConstant.MA_HS,s.getMHS());
        contentValues.put(MyConstant.TEN_HS,s.getName());
        contentValues.put(MyConstant.D_TOAN,s.getdToan());
        contentValues.put(MyConstant.D_LY,s.getdLy());
        contentValues.put(MyConstant.D_HOA,s.getdHoa());
        long rs = sqLiteDatabase.insert(MyConstant.TB_SCORE,null,contentValues);
        close();
        if (rs!=0) return true;
        return  false;

    }


    public List<Score> getScore(){
        Score s;
        List<Score> arrs = new ArrayList<Score>();
        Cursor cur = sqLiteDatabase.query(MyConstant.TB_SCORE,coLums,null,null,null,null,null);
        cur.moveToFirst();

        while (!cur.isAfterLast()){
            int  mhs =cur.getInt(0);
            String name =cur.getString(1);
            float dtoan =cur.getFloat(2);
            float dly =cur.getFloat(3);
            float dhoa =cur.getFloat(4);
            s = new Score(mhs,name,dtoan,dly,dhoa);
            arrs.add(s);
            cur.moveToNext();
        }
        close();

        return arrs;
    }
    public Score findScoreByID(String id){
        Cursor cur = sqLiteDatabase.query(MyConstant.TB_SCORE, coLums,
                null, null, null, null, null);
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            int mhs =cur.getInt(0);
            String name =cur.getString(1);
            float dtoan =cur.getFloat(2);
            float dly =cur.getFloat(3);
            float dhoa =cur.getFloat(4);

            if (id.equalsIgnoreCase(mhs+"")) {
              Score s = new Score(mhs,name,dtoan,dly,dhoa);
              return  s;
            } else {
                cur.moveToNext();
            }
            close();
        }
        return null;
    }

}
