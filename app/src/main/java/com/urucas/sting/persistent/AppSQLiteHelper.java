package com.urucas.sting.persistent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vruno on 5/29/14.
 */
public class AppSQLiteHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "sting.db0";
    private static final int DATABASE_VERSION = 2;

    public AppSQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserSQLiteHelper.DATABASE_CREATE);
        db.execSQL(SlidesSQLiteHelper.DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserSQLiteHelper.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SlidesSQLiteHelper.TABLE_NAME);
        onCreate(db);
    }
}
