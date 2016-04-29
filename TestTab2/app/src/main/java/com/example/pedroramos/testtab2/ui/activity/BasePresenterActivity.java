package com.example.pedroramos.testtab2.ui.activity;

import android.os.Bundle;

import com.example.pedroramos.testtab2.presenter.activity.BasePresenter;

public abstract class BasePresenterActivity<PRESENTER extends BasePresenter> extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().onCreate(savedInstanceState);
        getPresenter().onAttach(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getPresenter().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        getPresenter().onDetach();
        super.onDestroy();
    }

    protected abstract PRESENTER getPresenter();
}