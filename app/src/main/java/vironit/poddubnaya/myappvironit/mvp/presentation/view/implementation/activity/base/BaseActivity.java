package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;

import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BasePresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.base.IBaseView;
import vironit.poddubnaya.myappvironit.utils.KeyboardUtil;
import vironit.poddubnaya.myappvironit.utils.ShowDialogUtil;
import vironit.poddubnaya.myappvironit.utils.ShowSnackBarUtil;

public abstract class BaseActivity<P extends BasePresenter> extends MvpAppCompatActivity implements IBaseView {

    @Nullable
    private AlertDialog mDialog;

    @Nullable
    private Snackbar mSnackBar;

    protected final Handler mHandler = new Handler();

    @LayoutRes
    public abstract int getLayoutResId();

    @IdRes
    public abstract int getRootViewResId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
    }

    @Override
    public void hideKeyboard() {
        KeyboardUtil.hideKeyboard(this);

    }

    @Override
    public void cancelScreen() {

    }

    @Override
    public void showAutocloseableMessage(@NonNull String message) {
        showMessage(message, true, null, null);
    }

    @Override
    public void showDialogMessage(@NonNull String message, boolean closable) {
        String title = getResources().getString(R.string.app_name);
        showDialogWithOptions(title, message, null, null, null,
                null, closable);

    }

    @Override
    public void showDialogWithOptions(@NonNull String title,
                                      @NonNull String message,
                                      @Nullable String positiveOptionMessage,
                                      @Nullable String negativeOptionMessage,
                                      @Nullable DialogInterface.OnClickListener positiveListener,
                                      @Nullable DialogInterface.OnClickListener negativeListener,
                                      boolean cancelable) {
        hideDialogMessage();
        mDialog = ShowDialogUtil.showMessageDialog(this, title, message, positiveOptionMessage,
                negativeOptionMessage, positiveListener, negativeListener, cancelable);

    }

    @Override
    public void showMessage(@NonNull String message, boolean closable,
                            @Nullable String actionMessage,
                            @Nullable View.OnClickListener actionListener) {
        hideMessage();
        @Nullable
        View rootView = findViewById(getRootViewResId());
        /* @BaseTransientBottomBar.Duration   */
        int duration = closable ? BaseTransientBottomBar.LENGTH_SHORT : BaseTransientBottomBar.LENGTH_INDEFINITE;
        if (rootView == null) {
            rootView = getWindow().getDecorView();
        }
        mSnackBar = ShowSnackBarUtil.showSnackBar(rootView, this, message, actionMessage, actionListener, duration);

    }

    @Override
    public void hideDialogMessage() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
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
        showProgress(null);
    }

    @Override
    public void showProgress(@Nullable String messageText) {
        hideProgress();
        String titleText = getResources().getString(R.string.progress_title);
        mDialog = ShowDialogUtil.showProgressDialog(this, titleText, messageText);

    }

    @Override
    public void hideProgress() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }

    }
}
