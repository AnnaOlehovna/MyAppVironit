package vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.base.IBaseView;

@StateStrategyType(SkipStrategy.class)
public interface ILoginView extends IBaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showSuccessMessage();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showFailMessage();

}
