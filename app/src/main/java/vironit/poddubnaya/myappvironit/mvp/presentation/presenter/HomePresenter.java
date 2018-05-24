package vironit.poddubnaya.myappvironit.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;

import vironit.poddubnaya.myappvironit.App;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BaseAppPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.IHomeView;

@InjectViewState
public class HomePresenter extends BaseAppPresenter<IHomeView> {

    public HomePresenter() {
        App.getsAppComponent().inject(this);
    }

    public void showNews() {
        getViewState().showNews();
    }

    public void showChat() {
        getViewState().showChat();
    }

    public void showProfile() {
        getViewState().showProfile();
    }
}
