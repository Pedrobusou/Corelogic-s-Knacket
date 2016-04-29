package com.example.pedroramos.testtab2.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import com.example.pedroramos.testtab2.data.DataManager;
import com.example.pedroramos.testtab2.data.SyncService;
import com.example.pedroramos.testtab2.data.local.DatabaseHelper;
import com.example.pedroramos.testtab2.data.local.PreferencesHelper;
import com.example.pedroramos.testtab2.data.remote.RibotsService;
import com.example.pedroramos.testtab2.injection.ApplicationContext;
import com.example.pedroramos.testtab2.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    RibotsService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    Bus eventBus();

}