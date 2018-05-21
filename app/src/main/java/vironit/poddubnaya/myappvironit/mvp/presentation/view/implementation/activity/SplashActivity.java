package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.SplashPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.SplashView;

public class SplashActivity extends MvpAppCompatActivity implements SplashView {

    @InjectPresenter
    SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_activity);
        splashPresenter.navigateToLogin();
    }

    @Override
    public void navigateToLoginScreen() {
        TestActivity.start(this);
    }
}
