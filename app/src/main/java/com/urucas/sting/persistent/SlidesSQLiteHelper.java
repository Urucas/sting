package com.urucas.sting.persistent;

/**
 * @copyright Urucas
 * @license   Copyright (C) 2013. All rights reserved
 * @link       http://urucas.com
 * @developers Bruno Alassia, Pamela Prosperi
 * @date {5/29/14}
**/
public class SlidesSQLiteHelper {

    public static final String TABLE_NAME = "slides";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SID = "id";
    public static final String COLUMN_SNAME = "name";
    public static final String COLUMN_SNSP = "namespace";
    public static final String COLUMN_UID = "id_user";
    public static final String COLUMN_SDESC = "desc";

    public static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_SNAME + " text not null, "
            + COLUMN_SNSP + " text not null, "
            + COLUMN_SDESC + " text not null, "
            + COLUMN_UID + " integer not null, "
            + COLUMN_SID + " integer);";
}
