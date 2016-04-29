package com.example.pedroramos.testtab2.data;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

import javax.inject.Inject;

import com.example.pedroramos.testtab2.injection.scope.PerApplication;
import rx.Observable;
import timber.log.Timber;

@PerApplication
public class DatabaseFacade {

    private DatabaseHelper helper;

    @Inject
    public DatabaseFacade(Context context) {
        helper = new DatabaseHelper(context);
    }

}