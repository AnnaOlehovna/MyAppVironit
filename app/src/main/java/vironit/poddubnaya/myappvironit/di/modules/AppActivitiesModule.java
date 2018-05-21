package vironit.poddubnaya.myappvironit.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import vironit.poddubnaya.myappvironit.di.annotations.ActivityScope;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.TestActivity;

@Module(includes = {AndroidSupportInjectionModule.class})
public interface AppActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    TestActivity testActivityInjector();

}
