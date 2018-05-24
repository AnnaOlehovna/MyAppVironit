package vironit.poddubnaya.myappvironit.mvp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;

import vironit.poddubnaya.myappvironit.App;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BaseAppPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.IProfileView;

@InjectViewState
public class ProfilePresenter extends BaseAppPresenter<IProfileView> {
    public ProfilePresenter() {
        App.getsAppComponent().inject(this);
    }
}
