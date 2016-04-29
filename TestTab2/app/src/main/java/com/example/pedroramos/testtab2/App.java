/*
 * Copyright (C) 2015 TOLIART MOBILE.
 * All Rights Reserved.
 *
 * Disclaimer:
 * Redistribution of this source code for commercial or non-commercial use in source and binary forms,
 * with or without modification, are permitted without a commercial license of this product.
 *
 * THIS SOFTWARE IS PROVIDED BY TOLIART MOBILE ON AN "AS IS" BASIS.
 * TOLIART MOBILE MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
 * THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE, REGARDING THE SOFTWARE OR ITS USE AND
 * OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS. NO WARRANTY IS MADE THAT THE SOFTWARE WILL
 * OPERATE ERROR-FREE OR REGARDING THE RESULTS TO BE ACHIEVED. NO WARRANTY IS MADE THAT THE SOFTWARE
 * WILL MEET YOUR REQUIREMENTS OR PRODUCE ACCURATE RESULTS.
 *
 * IN NO EVENT SHALL TOLIART MOBILE BE LIABLE FOR ANY SPECIAL, INDIRECT,
 * INCIDENTAL OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION,
 * MODIFICATION AND/OR DISTRIBUTION OF THE TOLIART MOBILE SOFTWARE, HOWEVER CAUSED
 * AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE),
 * STRICT LIABILITY OR OTHERWISE, EVEN IF TOLIART MOBILE HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.example.pedroramos.testtab2;

import android.app.Application;
import android.os.Build;

//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;
import com.squareup.picasso.Picasso;

import com.example.pedroramos.testtab2.injection.component.ApplicationComponent;
import com.example.pedroramos.testtab2.injection.component.DaggerApplicationComponent;
import com.example.pedroramos.testtab2.injection.module.ApplicationModule;
import timber.log.Timber;

public class App extends Application {
    private static ApplicationComponent component;

    private static App singleton;

    public static synchronized App getInstance() {
        return singleton;
    }

    @Override
    public final void onCreate() {
        initDagger();
        initTimber();
        super.onCreate();
        //initializeCrashlytics();
        initPicasso();

        Timber.i("Android version name: " + Build.VERSION.RELEASE);

        singleton = this;
    }


    private void initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
    }

    private void initDagger() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    /*private void initializeCrashlytics() {
        if (!BuildConfig.DEBUG)
            Fabric.with(this, new Crashlytics());
    }*/

    public static ApplicationComponent getComponent() {
        return component;
    }

    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(getApplicationContext())
                .listener((picasso1, uri, exception) -> Timber.w(exception, "Picasso: error loading image"))
                .indicatorsEnabled(BuildConfig.DEBUG)
                .build();

        Picasso.setSingletonInstance(picasso);
    }
}