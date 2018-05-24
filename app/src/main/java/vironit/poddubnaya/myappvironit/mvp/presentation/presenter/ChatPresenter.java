package vironit.poddubnaya.myappvironit.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;

import vironit.poddubnaya.myappvironit.App;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BaseAppPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.IChatView;

@InjectViewState
public class ChatPresenter extends BaseAppPresenter<IChatView>{

    public ChatPresenter() {
        App.getsAppComponent().inject(this);
    }
}
