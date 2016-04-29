package com.example.pedroramos.testtab2.injection.component;


import dagger.Component;
import com.example.pedroramos.testtab2.injection.module.FragmentModule;
import com.example.pedroramos.testtab2.injection.scope.PerFragment;

@PerFragment
@Component(modules = FragmentModule.class, dependencies = ActivityComponent.class)
public interface FragmentComponent {

    //void inject(ContactsFragment contactsFragment);
}
