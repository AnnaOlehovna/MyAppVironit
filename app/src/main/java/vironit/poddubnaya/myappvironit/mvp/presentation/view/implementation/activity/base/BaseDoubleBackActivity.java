package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base;

import android.content.Intent;

import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.constants.IAppConstants;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BasePresenter;

public abstract class BaseDoubleBackActivity<P extends BasePresenter> extends BaseActivity<P> {


    private boolean doubleBackToExitPressedOnce = false;

    private void handleTaskBack() {
        if (doubleBackToExitPressedOnce) {
            startActivity(new Intent(this, DummyActivity.class));
            finish();
            doubleBackToExitPressedOnce = false;
        }else {
            doubleBackToExitPressedOnce = true;
            showAutocloseableMessage(getString(R.string.press_again_to_exit));
            mHandler.postDelayed(() -> doubleBackToExitPressedOnce = false, IAppConstants.LONG_DOUBLE_BACK_DELAY);
        }
    }

    @Override
    public void onBackPressed() {
        handleTaskBack();
    }
}
