package vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces;


import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.io.File;

import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.base.IBaseView;

@StateStrategyType(SkipStrategy.class)
public interface IProfileView extends IBaseView {
    void setPhoto(File file);
}
