package vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.base;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public interface IMessageView {

    void showAutocloseableMessage(@NonNull String message);

    void showDialogMessage(@NonNull String message, boolean closable);

    void showDialogWithOptions(@NonNull String title, @NonNull String message,
                               @Nullable  String positiveOptionMessage, @Nullable String negativeOptionMessage,
                               DialogInterface.OnClickListener positiveListener,
                               DialogInterface.OnClickListener negativeListener,
                               boolean cancelable);


    void showMessage(@NonNull String message, boolean closable,
                     @Nullable String actionMessage,@Nullable View.OnClickListener actionListener);

    void hideDialogMessage();

    void hideMessage();

    interface IActionListener {
        void onAction();
    }
}
