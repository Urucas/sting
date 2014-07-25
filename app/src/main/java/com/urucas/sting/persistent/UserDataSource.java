package com.urucas.sting.persistent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.urucas.sting.model.User;

import java.sql.SQLException;

/**
 * @copyright Urucas
 * @license   Copyright (C) 2013. All rights reserved
 * @link       http://urucas.com
 * @developers Bruno Alassia, Pamela Prosperi
 * @date {5/29/14}
**/

public class UserDataSource {

    private SQLiteDatabase database;
    private AppSQLiteHelper dbHelper;

    private String[] allColumns = {
            UserSQLiteHelper.COLUMN_ID,
            UserSQLiteHelper.COLUMN_UID,
            UserSQLiteHelper.COLUMN_UEMAIL,
            UserSQLiteHelper.COLUMN_UNAME,
            UserSQLiteHelper.COLUMN_UPASS,
            UserSQLiteHelper.COLUMN_UTOKEN
    };

    public UserDataSource(Context context) {
        dbHelper = new AppSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();

        user.setId(cursor.getInt(cursor.getColumnIndex(UserSQLiteHelper.COLUMN_UID)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(UserSQLiteHelper.COLUMN_UEMAIL)));
        user.setName(cursor.getString(cursor.getColumnIndex(UserSQLiteHelper.COLUMN_UNAME)));
        user.setPass(cursor.getString(cursor.getColumnIndex(UserSQLiteHelper.COLUMN_UPASS)));
        user.setToken(cursor.getString(cursor.getColumnIndex(UserSQLiteHelper.COLUMN_UTOKEN)));
        return user;
    }

    public void deleteUser() {
        database.delete(UserSQLiteHelper.TABLE_NAME, null, null);
    }

    public boolean createUser(User user){

        deleteUser();

        ContentValues values = new ContentValues();
        values.put(UserSQLiteHelper.COLUMN_UID, user.getId());
        values.put(UserSQLiteHelper.COLUMN_UNAME, user.getName());
        values.put(UserSQLiteHelper.COLUMN_UEMAIL, user.getEmail());
        values.put(UserSQLiteHelper.COLUMN_UPASS, user.getPass());
        values.put(UserSQLiteHelper.COLUMN_UTOKEN, user.getToken());

        long insertId = database.insert(UserSQLiteHelper.TABLE_NAME, null, values);
        return insertId != -1 ? true : false;
    }

    public User getUser(){
        Cursor c = database.rawQuery("SELECT * FROM "+UserSQLiteHelper.TABLE_NAME+" LIMIT 1", new String[]{});
        c.moveToFirst();
        if(c.getCount() == 0) {
            return null;
        }
        return cursorToUser(c);
    }

}
