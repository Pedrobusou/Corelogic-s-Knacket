package com.example.pedroramos.testtab2.data.api;

import android.content.Context;

import com.example.pedroramos.testtab2.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAdapterFactory {

    private RestAdapterFactory() {}

    public static Retrofit create(Context context) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.ENDPOINT)
                .client(HttpClientFactory.build(context))
                .addConverterFactory(GsonConverterFactory.create(GsonFactory.create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

}