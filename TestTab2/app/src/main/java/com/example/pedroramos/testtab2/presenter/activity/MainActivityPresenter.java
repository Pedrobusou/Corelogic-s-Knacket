package com.example.pedroramos.testtab2.presenter.activity;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import javax.inject.Inject;

import com.example.pedroramos.testtab2.ExceptionHandler;
import com.example.pedroramos.testtab2.data.DataManager;
import com.example.pedroramos.testtab2.injection.scope.PerActivity;
import com.example.pedroramos.testtab2.ui.activity.BasePresenterActivity;
import com.example.pedroramos.testtab2.ui.activity.MainActivity;
import rx.Subscription;

@PerActivity
public class MainActivityPresenter extends BasePresenter<BasePresenterActivity> { //Before: <MainActivity>
    DataManager dataManager;
    Context context;
    Subscription subscription;
    ExceptionHandler exceptionHandler;

    @Inject
    public MainActivityPresenter(DataManager dataManager, Context context, ExceptionHandler exceptionHandler) {
        this.dataManager = dataManager;
        this.context = context;
        this.exceptionHandler = exceptionHandler;
    }

    public String getRealPathFromURI(Uri uri) {
        // Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }
}