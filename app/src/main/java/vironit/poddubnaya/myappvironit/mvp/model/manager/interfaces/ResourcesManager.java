package vironit.poddubnaya.myappvironit.mvp.model.manager.interfaces;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public interface ResourcesManager {

    @NonNull
    String getString(@StringRes int strResId);

    @NonNull
    String getString(@StringRes int resId, @NonNull Object... formatArgs);
}
