package vironit.poddubnaya.myappvironit;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;

import com.facebook.FacebookSdk;
import com.twitter.sdk.android.core.Twitter;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasBroadcastReceiverInjector;
import dagger.android.HasServiceInjector;
import vironit.poddubnaya.myappvironit.di.components.AppComponent;
import vironit.poddubnaya.myappvironit.di.components.DaggerAppComponent;

public class App extends Application implements HasActivityInjector, HasServiceInjector, HasBroadcastReceiverInjector {

    private static AppComponent sAppComponent;

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<BroadcastReceiver> broadcastReceiverInjector() {
        return broadcastReceiverDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceDispatchingAndroidInjector;
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<BroadcastReceiver> broadcastReceiverDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Service> serviceDispatchingAndroidInjector;

    public static AppComponent getsAppComponent() {
        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger2();
        Twitter.initialize(this);
    }


    private void initDagger2() {
        sAppComponent = DaggerAppComponent.builder()
                .appContext(this)
                .build();

        sAppComponent.inject(this);
    }
}
