package vironit.poddubnaya.myappvironit.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public abstract class KeyboardUtil {

    public static void hideKeyboard(@Nullable View view, @Nullable Activity activity) {
        if (activity == null || view == null) {
            return;
        }
        @Nullable InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

    public static void hideKeyboard(@Nullable Activity activity) {
        if (activity == null) {
            return;
        }
        @Nullable InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        @Nullable View currentFocus = activity.getCurrentFocus();
        if (imm != null && currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

    public static void showKeyboard(@Nullable Activity activity) {
        if (activity == null) {
            return;
        }
        @Nullable InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
    }
}
