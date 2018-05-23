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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.util.Arrays;
import java.util.Objects;

import javax.inject.Inject;

import vironit.poddubnaya.myappvironit.App;
import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.constants.IAppConstants;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BaseAppPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base.BaseActivity;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.ILoginView;

@InjectViewState
public class LoginPresenter extends BaseAppPresenter<ILoginView> {

    private String selectedLoginButton = "";

    @Inject
    TwitterAuthClient mTwitterAuthClient;

    @Inject
    CallbackManager mCallbackManager;

    @Inject
    GoogleSignInClient mGoogleSignInClient;


    public LoginPresenter() {
        App.getsAppComponent().inject(this);
    }

    public void clickOnFacebookButton(@NonNull Activity activity) {
        selectedLoginButton = IAppConstants.FACEBOOK;
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.i("MY_APP_TAG", loginResult.getAccessToken().getUserId());
                        getViewState().showSuccessMessage();
                    }

                    @Override
                    public void onCancel() {
                        // TODO
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.i("MY_APP_TAG", exception.toString());
                        getViewState().showFailMessage();
                    }
                });
    }


    public void clickOnTwitterButton(@NonNull Activity activity) {
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

    public void clickOnGoogleButton(@NonNull Activity activity) {
        selectedLoginButton = IAppConstants.GOOGLE;
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, IAppConstants.RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.i("MY_APP_TAG", "ok");
            getViewState().showSuccessMessage();
        } catch (ApiException e) {
            Log.w("MY_APP_TAG", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    public void signOutFromAllAccounts() {
        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (twitterSession != null) {
            TwitterCore.getInstance().getSessionManager().clearActiveSession();
        }
        LoginManager.getInstance().logOut();
        mGoogleSignInClient.signOut();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, @NonNull BaseActivity activity) {
        super.onActivityResult(requestCode, resultCode, data, activity);
        switch (selectedLoginButton) {
            case IAppConstants.TWITTER:
                mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
                break;
            case IAppConstants.FACEBOOK:
                mCallbackManager.onActivityResult(requestCode, resultCode, data);
                break;
            case IAppConstants.GOOGLE:
                if (requestCode == IAppConstants.RC_SIGN_IN) {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    handleSignInResult(task);
                }
                break;
            default:
                break;
        }
    }
}
