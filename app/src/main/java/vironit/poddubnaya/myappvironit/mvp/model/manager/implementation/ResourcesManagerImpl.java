package vironit.poddubnaya.myappvironit.mvp.model.manager.implementation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import javax.inject.Inject;

import vironit.poddubnaya.myappvironit.mvp.model.manager.interfaces.ResourcesManager;

public class ResourcesManagerImpl implements ResourcesManager {

    @NonNull
    private final Context mAppContext;

    @Inject
    public ResourcesManagerImpl(@NonNull Context AppContext) {
        mAppContext = AppContext;
    }

    @NonNull
    @Override
    public String getString(@StringRes int strResId) {
        String str = null;
        try {
            str = mAppContext.getString(strResId);
        } catch (Exception e) {
        }
        return str != null ? str : "";
    }

    @NonNull
    @Override
    public String getString(@StringRes int resId,@NonNull Object... formatArgs) {
        String str = null;
        try {
            str = mAppContext.getString(resId, formatArgs);
        } catch (Exception e) {
        }

        return str != null ? str : "";
    }
}
