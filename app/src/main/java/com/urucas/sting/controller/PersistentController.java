package com.urucas.sting.controller;

import android.content.Context;


import com.urucas.sting.model.User;
import com.urucas.sting.persistent.UserDataSource;

import java.sql.SQLException;

/**
 * @copyright Urucas
 * @license   Copyright (C) 2013. All rights reserved
 * @link       http://urucas.com
 * @developers Bruno Alassia, Pamela Prosperi
 * @date {5/29/14}
**/
public class PersistentController {

    private UserDataSource userDataSource;

    public User getUser(Context context) throws SQLException {
        if(userDataSource == null) {
            userDataSource = new UserDataSource(context);
            userDataSource.open();
        }
        return userDataSource.getUser();
    }

    public boolean createUser(Context context, User user) throws SQLException {
        if(userDataSource == null) {
            userDataSource = new UserDataSource(context);
            userDataSource.open();
        }
        return userDataSource.createUser(user);
    }

    public void logout(Context context) throws SQLException {
        if (userDataSource == null) {
            userDataSource = new UserDataSource(context);
            userDataSource.open();
        }
        userDataSource.deleteUser();
    }

}
