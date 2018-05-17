package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base;

import android.os.Bundle;
import android.os.Handler;

import com.arellomobile.mvp.MvpAppCompatActivity;

public class DummyActivity extends MvpAppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(() -> this.finish(),500);
    }
}
