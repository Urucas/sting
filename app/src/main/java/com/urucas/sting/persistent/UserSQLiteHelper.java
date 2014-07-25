package com.urucas.sting.persistent;

/**
 * @copyright Urucas
 * @license   Copyright (C) 2013. All rights reserved
 * @link       http://urucas.com
 * @developers Bruno Alassia, Pamela Prosperi
 * @date {5/29/14}
**/
public class UserSQLiteHelper {

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_UID = "id";
    public static final String COLUMN_UNAME = "name";
    public static final String COLUMN_UEMAIL = "email";
    public static final String COLUMN_UPASS = "pass";
    public static final String COLUMN_UTOKEN = "token";

    public static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_UNAME + " text not null, "
            + COLUMN_UEMAIL + " text not null, "
            + COLUMN_UPASS + " text not null, "
            + COLUMN_UTOKEN + " text not null, "
            + COLUMN_UID + " integer);";
}
