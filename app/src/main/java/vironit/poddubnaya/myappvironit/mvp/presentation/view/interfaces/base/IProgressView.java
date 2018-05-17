package vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.base;

import android.support.annotation.NonNull;

public interface IProgressView {

    void showProgress();

    void showProgress(@NonNull String message);

    void hideProgress();

}
