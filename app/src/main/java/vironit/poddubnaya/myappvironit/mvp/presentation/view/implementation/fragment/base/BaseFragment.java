package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import java.lang.reflect.Field;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import vironit.poddubnaya.myappvironit.mvp.model.manager.interfaces.ResourcesManager;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BaseAppPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base.BaseActivity;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.base.IBaseView;
import vironit.poddubnaya.myappvironit.utils.AppLog;
import vironit.poddubnaya.myappvironit.utils.ShowSnackBarUtil;

public abstract class BaseFragment<P extends BaseAppPresenter> extends MvpAppCompatFragment implements IBaseView {

    @LayoutRes
    public abstract int getLayoutResId();

    protected abstract P getPresenter();

    @Nullable
    private IBaseView mRootActivityIBaseView;

    @Nullable
    private Snackbar mSnackBar;

    @Inject
    protected ResourcesManager mResourcesManager;

    @Override
    public void onAttach(Context context) {
        AppLog.logFragment(this);
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof IBaseView) {
            mRootActivityIBaseView = (IBaseView) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AppLog.logFragment(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppLog.logFragment(this);
        View view = inflater.inflate(getLayoutResId(), container, false);
        if (getArguments() != null) {
            initFromArguments(getArguments());
        }
        initBeforeLayout();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AppLog.logFragment(this);
        super.onViewCreated(view, savedInstanceState);
        initViewBeforePresenterAttach();
        getMvpDelegate().onAttach();
    }

    @Override
    public void onStart() {
        AppLog.logFragment(this);
        super.onStart();
    }

    @Override
    public void onResume() {
        AppLog.logFragment(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        AppLog.logFragment(this);
        hideKeyboard();
        super.onPause();
    }

    @Override
    public void onStop() {
        AppLog.logFragment(this);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        AppLog.logFragment(this);
        hideProgress();
        hideDialogMessage();
        hideMessage();
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        AppLog.logFragment(this);
        mRootActivityIBaseView = null;
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            Log.i("MY_APP_TAG", "signInResult:failed code=" + e.toString());
        } catch (IllegalAccessException e) {
            Log.i("MY_APP_TAG", "signInResult:failed code=" + e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            Log.i("MY_APP_TAG", "signInResult:failed code=" + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        AppLog.logFragment(this);
        super.onDestroy();
    }


    @Override
    public void hideKeyboard() {
        if (mRootActivityIBaseView != null) {
            mRootActivityIBaseView.hideKeyboard();
        }
    }

    @Override
    public void cancelScreen() {
        @Nullable
        Activity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    @Override
    public void showAutocloseableMessage(@NonNull String message) {
        showMessage(message, true, null, null);

    }

    @Override
    public void showDialogMessage(@NonNull String message, boolean closable) {
        if (mRootActivityIBaseView != null) {
            mRootActivityIBaseView.showDialogMessage(message, closable);
        }
    }

    @Override
    public void showDialogWithOptions(@NonNull String title, @NonNull String message, @Nullable String positiveOptionMessage, @Nullable String negativeOptionMessage, DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener, boolean cancelable) {
        if (mRootActivityIBaseView != null) {
            mRootActivityIBaseView.showDialogWithOptions(title, message, positiveOptionMessage, negativeOptionMessage, positiveListener, negativeListener, cancelable);
        }
    }

    @Override
    public void showMessage(@NonNull String message, boolean closable,
                            @Nullable String actionMessage, @Nullable View.OnClickListener actionListener) {
        hideKeyboard();
        hideMessage();
        @Nullable
        View rootView = getView();
        /* @BaseTransientBottomBar.Duration   */
        int duration = closable ? BaseTransientBottomBar.LENGTH_SHORT : BaseTransientBottomBar.LENGTH_INDEFINITE;
        mSnackBar = ShowSnackBarUtil.showSnackBar(rootView, getContext(), message, actionMessage, actionListener, duration);

    }

    @Override
    public void hideDialogMessage() {
        if (mRootActivityIBaseView != null) {
            mRootActivityIBaseView.hideDialogMessage();
        }
    }

    @Override
    public void hideMessage() {
        if (mSnackBar != null) {
            mSnackBar.dismiss();
        }

    }

    @Override
    public void showProgress() {
        if (mRootActivityIBaseView != null) {
            mRootActivityIBaseView.showProgress();
        }
    }

    @Override
    public void showProgress(@Nullable String messageText) {
        if (mRootActivityIBaseView != null) {
            mRootActivityIBaseView.showProgress(messageText);
        }
    }

    @Override
    public void hideProgress() {
        if (mRootActivityIBaseView != null) {
            mRootActivityIBaseView.hideProgress();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Activity activity = getActivity();
        if (activity != null) {
            if (activity instanceof BaseActivity) {
                getPresenter().onActivityResult(requestCode, resultCode, data, (BaseActivity) activity);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Activity activity = getActivity();
        if (activity != null) {
            if (activity instanceof BaseActivity) {
                getPresenter().onRequestPermissionsResult(requestCode, permissions, grantResults, (BaseActivity) activity);
            }
        }
    }

    protected void initBeforeLayout() {
        AppLog.logFragment(this);
    }

    protected void initFromArguments(@NonNull Bundle arg) {
        AppLog.logFragment(this);

    }

    protected void initViewBeforePresenterAttach() {

    }

    protected String getResourseString(@StringRes int stringId) {
        return mResourcesManager.getString(stringId);
    }
}
