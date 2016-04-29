package com.example.pedroramos.testtab2.injection.component;

import dagger.Component;
import com.example.pedroramos.testtab2.injection.module.ActivityModule;
import com.example.pedroramos.testtab2.injection.scope.PerActivity;
import com.example.pedroramos.testtab2.ui.activity.MainActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}