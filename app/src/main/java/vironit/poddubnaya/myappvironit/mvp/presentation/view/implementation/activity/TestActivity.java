package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.TestPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base.BaseActivity;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.ITestView;

public class TestActivity extends BaseActivity<TestPresenter> implements ITestView {

    @InjectPresenter
    TestPresenter mTestPresenter;

    public static void start(@Nullable Context context) {
        if (context != null) {
            context.startActivity(new Intent(context, TestActivity.class));
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.layout_test_actvity;
    }

    @Override
    public int getRootViewResId() {
        return R.id.v_root_test_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
    }

    private void setListeners() {
        findViewById(R.id.btn_show_dialog_simple_message)
                .setOnClickListener(v -> showDialogMessage("simple_message", true));

        findViewById(R.id.btn_show_dialog_with_options)
                .setOnClickListener(v -> showDialogWithOptions("dialog_with_options",
                        "dialog_with_options",
                        "ok",
                        "cancel",
                        (v2, w1) -> Toast.makeText(this,"positiveText",Toast.LENGTH_SHORT).show(),
                        (v1, v2) -> Toast.makeText(this,"negativeText",Toast.LENGTH_SHORT).show(),
                        false));


        findViewById(R.id.btn_show_message_with_action)
                .setOnClickListener(v -> showMessage("message_with_action", false
                        , "message_with_action", v1 -> Toast.makeText(this,"actionText",Toast.LENGTH_SHORT).show()));

       findViewById(R.id.btn_show_avtoclosable_message)
               .setOnClickListener(v -> showAutocloseableMessage("klhlkhlk"));
    }
}
