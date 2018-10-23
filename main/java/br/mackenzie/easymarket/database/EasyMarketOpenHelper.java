package br.mackenzie.easymarket.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EasyMarketOpenHelper  extends SQLiteOpenHelper {


    public EasyMarketOpenHelper(@androidx.annotation.Nullable Context context) {
        super(context, "EasyMarketDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(ScriptDDL.getCreateTableClient());
        db.execSQL(ScriptDDL.getCreateTableProducts());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
