package vironit.poddubnaya.myappvironit.mvp.presentation.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;

import vironit.poddubnaya.myappvironit.App;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BaseAppPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base.BaseActivity;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.IChatView;

@InjectViewState
public class ChatPresenter extends BaseAppPresenter<IChatView>{

    public ChatPresenter() {
        App.getsAppComponent().inject(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, @NonNull BaseActivity activity) {
        super.onActivityResult(requestCode, resultCode, data, activity);
    }
}
