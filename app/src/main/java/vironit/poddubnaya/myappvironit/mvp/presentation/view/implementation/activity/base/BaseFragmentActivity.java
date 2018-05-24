package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base;

import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BaseAppPresenter;

public abstract class BaseFragmentActivity<P extends BaseAppPresenter> extends BaseActivity<P>
        implements HasSupportFragmentInjector {
    @Inject
    DispatchingAndroidInjector<Fragment> mAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mAndroidInjector;
    }
}
