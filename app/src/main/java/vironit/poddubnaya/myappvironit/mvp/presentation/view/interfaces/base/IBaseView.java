package vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface IBaseView extends MvpView,IProgressView, IMessageView{

    void hideKeyboard();

    void cancelScreen();
}
