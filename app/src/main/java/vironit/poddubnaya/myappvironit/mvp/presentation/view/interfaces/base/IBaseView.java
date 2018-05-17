package vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.base;

import com.arellomobile.mvp.MvpView;

public interface IBaseView extends MvpView,IProgressView, IMessageView{

    void hideKeyboard();

    void cancelScreen();
}
