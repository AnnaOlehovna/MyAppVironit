package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.LoginPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base.BaseActivity;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.ILoginView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    @BindView(R.id.btn_facebook)
    Button facebookButton;

    @BindView(R.id.btn_twitter)
    Button twitterButton;

    @BindView(R.id.btn_google)
    Button googleButton;

    @Override
    public int getLayoutResId() {
        return R.layout.layout_login_activity;
    }

    @Override
    public int getRootViewResId() {
        return R.id.v_root_login_activity;
    }

    @Override
    protected LoginPresenter getPresenter() {
        return mLoginPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginPresenter.signOutFromAllAccounts();
        facebookButton.setOnClickListener(v -> mLoginPresenter.clickOnFacebookButton(this));
        twitterButton.setOnClickListener(v -> mLoginPresenter.clickOnTwitterButton(this));
        googleButton.setOnClickListener(v -> mLoginPresenter.clickOnGoogleButton(this));
    }



    public static void start(@Nullable Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }

    @Override
    public void showSuccessMessage() {
        showDialogMessage(getString(R.string.success_login),true);
    }

    @Override
    public void showFailMessage() {
        showDialogMessage(getString(R.string.error_login),true);
    }
}
