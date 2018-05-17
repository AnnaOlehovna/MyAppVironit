package vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.base;

import android.support.annotation.Nullable;

public interface IProgressView {

    void showProgress();

    void showProgress(@Nullable String messageText);

    void hideProgress();

}
