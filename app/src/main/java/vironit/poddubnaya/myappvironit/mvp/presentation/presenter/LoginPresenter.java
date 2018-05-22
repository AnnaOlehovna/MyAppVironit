package vironit.poddubnaya.myappvironit.mvp.presentation.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import vironit.poddubnaya.myappvironit.App;
import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.constants.IAppConstants;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BaseAppPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base.BaseActivity;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.ILoginView;

@InjectViewState
public class LoginPresenter extends BaseAppPresenter<ILoginView> {

    private  TwitterAuthClient mTwitterAuthClient = new TwitterAuthClient();
    private CallbackManager mCallbackManager;
    private String selectedLoginButton = "";


    public LoginPresenter() {
        App.getsAppComponent().inject(this);
    }

    public void clickOnFacebookButton() {
        selectedLoginButton = IAppConstants.FACEBOOK;
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.e("AAA", "ok");
                       getViewState().showDialogMessage(mResourcesManager.getString(R.string.success_login),true);
                    }

                    @Override
                    public void onCancel() {
                        // TODO
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        getViewState().showDialogMessage(mResourcesManager.getString(R.string.success_login),true);
                        Log.e("AAA", exception.toString());
                    }
                });
    }


    public void clickOnTwitterButton(@NonNull Activity activity){
        selectedLoginButton = IAppConstants.TWITTER;
        mTwitterAuthClient.authorize(activity, new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                getViewState().showSuccessMessage();
            }

            @Override
            public void failure(TwitterException exception) {
                getViewState().showFailMessage();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, @NonNull BaseActivity activity) {
        super.onActivityResult(requestCode, resultCode, data, activity);
        mTwitterAuthClient.onActivityResult(requestCode,resultCode,data);
//        mCallbackManager.onActivityResult(requestCode,resultCode,data);

//        switch (selectedLoginButton){
//            case IAppConstants.FACEBOOK:
//            {
//                mCallbackManager.onActivityResult(requestCode, resultCode, data);
//                break;
//            }
//        }

    }
}
