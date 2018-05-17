package vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base.BaseActivity;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.base.IBaseView;
import vironit.poddubnaya.myappvironit.utils.AppLog;

public abstract class BasePresenter<View extends IBaseView> extends MvpPresenter<View> {

    private final CompositeDisposable mLiteCompositeDisposable = new CompositeDisposable();
    private final CompositeDisposable mHardCompositeDisposable = new CompositeDisposable();

    @Override
    public void attachView(View view) {
        AppLog.logPresenter(this);
        super.attachView(view);
    }

    @Override
    protected void onFirstViewAttach() {
        AppLog.logPresenter(this);
        super.onFirstViewAttach();
    }

    @Override
    public void detachView(View view) {
        mLiteCompositeDisposable.clear();
        AppLog.logPresenter(this);
        super.detachView(view);
    }

    @Override
    public void destroyView(View view) {

        AppLog.logPresenter(this);
        super.destroyView(view);
    }

    @Override
    public void onDestroy() {
        mHardCompositeDisposable.clear();
        AppLog.logPresenter(this);
        super.onDestroy();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data, @NonNull BaseActivity activity) {
        AppLog.logPresenter(this);

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults, @NonNull BaseActivity activity) {
        AppLog.logPresenter(this);

    }

    protected void addLiteDisposable(@Nullable Disposable disposable) {
        if (disposable != null) {
            mLiteCompositeDisposable.add(disposable);
        }
    }

    protected void addHardDisposable(@Nullable Disposable disposable) {
        if (disposable != null) {
            mHardCompositeDisposable.add(disposable);
        }
    }


}
