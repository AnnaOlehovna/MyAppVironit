package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.HomePresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.activity.base.BaseFragmentActivity;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment.ChatFragment;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment.NewsFragment;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment.ProfileFragment;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.IHomeView;

public class HomeActivity extends BaseFragmentActivity<HomePresenter> implements IHomeView {

    @BindView(R.id.navigation_bar)
    BottomNavigationView navigationView;

    @InjectPresenter
    HomePresenter mHomePresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.layout_home_activity;
    }

    @Override
    public int getRootViewResId() {
        return R.id.v_root_home_activity;
    }

    @Override
    protected HomePresenter getPresenter() {
        return mHomePresenter;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainerViewId(), NewsFragment.newInstance())
                .commit();

        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_news:
                    setTitle(R.string.menu_news);
                    mHomePresenter.showNews();
                    break;
                case R.id.action_chat:
                    setTitle(R.string.menu_chat);
                    mHomePresenter.showChat();
                    break;
                case R.id.action_profile:
                    setTitle(R.string.menu_profile);
                    mHomePresenter.showProfile();
                    break;
            }
            return true;
        });
    }

    @Override
    public void showNews() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainerViewId(), NewsFragment.newInstance())
                .commit();
    }

    @Override
    public void showChat() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainerViewId(), ChatFragment.newInstance())
                .commit();
    }

    @Override
    public void showProfile() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(getContainerViewId(), ProfileFragment.newInstance())
                .commit();
    }

    private int getContainerViewId() {
        return R.id.fragment_container;
    }


}
