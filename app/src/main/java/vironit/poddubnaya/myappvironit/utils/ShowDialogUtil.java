package vironit.poddubnaya.myappvironit.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import vironit.poddubnaya.myappvironit.R;

public abstract class ShowDialogUtil {

    @Nullable
    public static AlertDialog showProgressDialog(@Nullable Activity activity,
                                                 @NonNull String titleText, @Nullable String messageText) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }

        //TODO
        AlertDialog.Builder builder = new AlertDialog.Builder(activity /*, R.style.AlertDialogStyle*/ )
                .setTitle(titleText)
                .setIcon(R.mipmap.ic_launcher_round)
                .setView(initProgressView(activity, messageText))
                .setCancelable(false);

        AlertDialog materialDialog = builder.show();
        @Nullable Window window = materialDialog.getWindow();
        if (window != null) {
            window.setLayout(activity.getResources().getDimensionPixelSize(R.dimen.progress_dialog_width),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        materialDialog.setCanceledOnTouchOutside(false);
        materialDialog.setCancelable(false);
        return materialDialog;
    }

    @NonNull
    private static View initProgressView(@NonNull Context context, @Nullable String messageText) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_progress_dialog, null, false);
        TextView messageTextView = view.findViewById(R.id.tv_message);
        if (!TextUtils.isEmpty(messageText)) {
            messageTextView.setText(messageText);
            messageTextView.setVisibility(View.VISIBLE);
        } else {
            messageTextView.setVisibility(View.GONE);
        }
        return view;
    }

    @Nullable
    public static AlertDialog showMessageDialog(@Nullable Activity activity,
                                                @NonNull String title,
                                                @NonNull String message,
                                                @Nullable String positiveOptionMessage,
                                                @Nullable String negativeOptionMessage,
                                                DialogInterface.OnClickListener positiveListener,
                                                DialogInterface.OnClickListener negativeListener,
                                                boolean cancelable) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }

        //TODO
        AlertDialog.Builder builder = new AlertDialog.Builder(activity /*, R.style.AlertDialogStyle*/)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveOptionMessage, positiveListener)
                .setNegativeButton(negativeOptionMessage, negativeListener)
                .setCancelable(cancelable);

        AlertDialog materialDialog = builder.show();
        materialDialog.setCanceledOnTouchOutside(cancelable);
        materialDialog.setCancelable(cancelable);
        return materialDialog;
    }
}
