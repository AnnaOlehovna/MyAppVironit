package vironit.poddubnaya.myappvironit.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.SplashView;

@InjectViewState
public class SplashPresenter extends MvpPresenter<SplashView> {

    private PublishSubject<Integer> subject = PublishSubject.create();
    private Disposable disposable = subject.delay(3, TimeUnit.SECONDS)
            .subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) throws Exception {
                    getViewState().navigateToLoginScreen();
                }
            });

    public void navigateToLogin() {
        subject.onNext(1);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

}
