package com.example.pedroramos.testtab2.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pedroramos.testtab2.App;
import com.example.pedroramos.testtab2.R;
import com.example.pedroramos.testtab2.injection.component.ActivityComponent;
import com.example.pedroramos.testtab2.injection.component.DaggerActivityComponent;
import com.example.pedroramos.testtab2.injection.module.ActivityModule;
import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity {

    private final static int PERMISSIONS_REQUEST = 1;
    private final static int PERMISSIONS_READ_EXTERNAL_MEMORY = 2;

    private boolean contactsPermissionGranted = false;
    private boolean phoneStatePermissionGranted = false;
    private boolean recordAudioPermissionGranted = false;

    private ActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        inject();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else
            super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        permissionCheck();
    }

    private void permissionCheck() {
        contactsPermissionGranted =
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
        phoneStatePermissionGranted =
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
        recordAudioPermissionGranted =
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;

        Timber.i("Permission state: " +
                "\nREAD CONTACTS GRANTED = " + contactsPermissionGranted +
                "\nREAD PHONE STATE GRANTED = " + phoneStatePermissionGranted +
                "\nRECORD AUDIO GRANTED = " + recordAudioPermissionGranted);


        if (!(contactsPermissionGranted && phoneStatePermissionGranted && recordAudioPermissionGranted)) {
            Timber.i("Request user for permission.");


            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST:
                if (grantResults.length > 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                    Timber.i("Permissions granted.");

                } else {
                    Timber.e("Read contact permission denied, retry request.");
                    explainUserPermissions();
                }
                break;
            case PERMISSIONS_READ_EXTERNAL_MEMORY:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Timber.i("Permission for reading external memory granted.");
                else
                    Timber.e("Permission request for reading external memory rejected.");
                break;
        }
    }

    /**
     * Checks if app has permission to read external storage. If not, request for permission.
     *
     * @return true if permission is granted, false otherwise
     */
    public boolean assertCanReadExternalStorage() {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN)
            return true;

        boolean granted;
        granted = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        if (!granted)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSIONS_READ_EXTERNAL_MEMORY);

        return granted;
    }

    private void explainUserPermissions() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.permission_required)
                .setMessage(R.string.permission_explanations)
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, which) -> {
                    permissionCheck();
                })
                .show();
    }

    public boolean isContactsPermissionGranted() {
        return contactsPermissionGranted;
    }

    public boolean isPhoneStatePermissionGranted() {
        return phoneStatePermissionGranted;
    }

    public boolean isRecordAudioPermissionGranted() {
        return recordAudioPermissionGranted;
    }

    protected void inject() {
        // no injection by default in activities
    }

    public ActivityComponent getComponent() {
        return component;
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    private void initializeInjector() {
        if (component == null)
            component = DaggerActivityComponent.builder()
                    .applicationComponent(App.getComponent())
                    .activityModule(getActivityModule())
                    .build();
    }

    public void showUserMessage(String msg, int color) {
        if (getCurrentFocus() != null) {
            Snackbar snackbar = Snackbar.make(getCurrentFocus(), msg, Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(color);
            snackbar.show();
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }

    }
}