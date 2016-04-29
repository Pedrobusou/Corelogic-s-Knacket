package com.example.pedroramos.testtab2.injection.module;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import com.example.pedroramos.testtab2.injection.scope.PerActivity;
import com.example.pedroramos.testtab2.ui.activity.BaseActivity;

@Module
public class ActivityModule {
    final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public BaseActivity provideActivity() {
        return activity;
    }

    @Provides
    @PerActivity
    public FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }
}
