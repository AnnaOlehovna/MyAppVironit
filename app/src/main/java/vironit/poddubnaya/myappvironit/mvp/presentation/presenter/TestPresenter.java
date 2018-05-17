package vironit.poddubnaya.myappvironit.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BaseAppPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.ITestView;

@InjectViewState
public class TestPresenter extends BaseAppPresenter<ITestView> {

    @Override
    public void attachView(ITestView view) {
        super.attachView(view);
        getViewState().showProgress();
        Single.just(1)
                .delay(5000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(as-> {
                    getViewState().hideProgress();
                }, throwable -> throwable.printStackTrace());
    }
}
