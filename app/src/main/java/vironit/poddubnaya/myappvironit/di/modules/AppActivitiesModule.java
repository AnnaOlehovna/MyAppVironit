package vironit.poddubnaya.myappvironit.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import vironit.poddubnaya.myappvironit.di.annotations.ActivityScope;
import vironit.poddubnaya.myappvironit.di.annotations.FragmentScope;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.HomeActivity;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.LoginActivity;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.TestActivity;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment.ChatFragment;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment.NewsFragment;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment.ProfileFragment;

@Module(includes = {AndroidSupportInjectionModule.class})
public interface AppActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    TestActivity testActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    LoginActivity loginActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    HomeActivity homeActivityInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    NewsFragment newsFragmentInjector();

    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    ChatFragment chatFragmentInjector();


    @FragmentScope
    @ContributesAndroidInjector(modules = ActivityModule.class)
    ProfileFragment profileFragmentInjector();



}
