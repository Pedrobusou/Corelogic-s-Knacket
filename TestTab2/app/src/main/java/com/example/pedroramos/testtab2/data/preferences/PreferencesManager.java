package com.example.pedroramos.testtab2.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import com.example.pedroramos.testtab2.injection.scope.PerApplication;

/**
 * Class to manage SharedPreferences of application.
 */
@PerApplication
public class PreferencesManager {
    private final static String PREFERENCES_FILE_NAME = "app_preferences";

    private final static String FIELD_MY_NUMBER = "my_number";
    private final static String FIELD_APP_HASH = "app_hash";
    private final static String FIELD_NOTIFICATIONS = "notifications_count";
    private final static String FIELD_PHOTO_PATH = "photo_path";

    private SharedPreferences preferences;

    @Inject
    public PreferencesManager(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
    }

    @Nullable
    public String getMyNumber() {
        return preferences.getString(FIELD_MY_NUMBER, null);
    }

    public void setMyNumber(@Nullable String myNumber) {
        preferences.edit()
                .putString(FIELD_MY_NUMBER, myNumber)
                .apply();
    }

    @Nullable
    public String getAppHash() {
        return preferences.getString(FIELD_APP_HASH, null);
    }

    public void setAppHash(@Nullable String appHash) {
        preferences.edit()
                .putString(FIELD_APP_HASH, appHash)
                .apply();
    }


    @Nullable
    public String getPhotoPath() {
        return preferences.getString(FIELD_PHOTO_PATH, null);
    }

    public void setPhotoPath(@Nullable String photoPath) {
        preferences.edit()
                .putString(FIELD_PHOTO_PATH, photoPath)
                .apply();
    }

    public void setNotificationsCount(@Nullable Integer notificationsCount) {
        preferences.edit().putInt(FIELD_NOTIFICATIONS, notificationsCount).apply();

    }

    @Nullable
    public Integer getNotificationsCount() {
        return preferences.getInt(FIELD_NOTIFICATIONS, 0);
    }
}