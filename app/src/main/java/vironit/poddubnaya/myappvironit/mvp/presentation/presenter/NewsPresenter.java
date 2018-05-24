package vironit.poddubnaya.myappvironit.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;

import vironit.poddubnaya.myappvironit.App;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BaseAppPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.INewsView;

@InjectViewState
public class NewsPresenter extends BaseAppPresenter<INewsView> {
    public NewsPresenter() {
        App.getsAppComponent().inject(this);
    }
}
