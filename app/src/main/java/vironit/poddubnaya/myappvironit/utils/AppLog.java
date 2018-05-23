package vironit.poddubnaya.myappvironit.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import vironit.poddubnaya.myappvironit.BuildConfig;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.base.BasePresenter;

public abstract class AppLog {


    public static void logPresenter(@NonNull BasePresenter presenter) {
        if (isLogEnabled()) {
            Log.i(getAppTag(), getInfo(presenter));
        }
    }

    public static void logPresenter(@NonNull BasePresenter presenter, String message) {

    }


    public static void logPresenter(@NonNull BasePresenter presenter, String message, Exception e) {

    }

    public static void logActivity(@NonNull Activity activity) {
        if (isLogEnabled()) {
            Log.i(getAppTag(), getInfo(activity));
        }
    }

    private static boolean isLogEnabled() {
        return BuildConfig.DEBUG;
    }

    private static String getMethodName() {
        try {
            return Thread.currentThread().getStackTrace()[5].getMethodName();
        } catch (Exception e) {
            return "Unkhown method";
        }
    }

    private static String getClassName(Object o) {
        return o.getClass().getName();
    }


    private static String getAppTag() {
        return "MY_APP_TAG";
    }


    private static String getInfo(Object o){
        return "Class: "+getClassName(o)+"."+getMethodName() + "()";
    }


}
