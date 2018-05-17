package vironit.poddubnaya.myappvironit.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import vironit.poddubnaya.myappvironit.R;

public abstract class ShowSnackBarUtil {
    @Nullable
    public static Snackbar showSnackBar(@Nullable View rootView,
                                        @Nullable Context context,
                                        @NonNull String message,
                                        @Nullable String actionText,
                                        View.OnClickListener actionListener,
                                        @BaseTransientBottomBar.Duration int duration) {
        if (rootView == null || context == null) {
            return null;
        }

        @Nullable Snackbar snackbar = null;
        try {
            snackbar = Snackbar.make(rootView, message, duration);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (snackbar != null) {
            if (actionListener != null) {
                snackbar.setAction(actionText, actionListener);
            }
            snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.colorAccent));

            View snackBarView = snackbar.getView();
            snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.color_gray_light));
            TextView textView = snackBarView.findViewById(R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(context, R.color.color_primary_text));
            snackbar.show();
        }

        return snackbar;
    }
}
