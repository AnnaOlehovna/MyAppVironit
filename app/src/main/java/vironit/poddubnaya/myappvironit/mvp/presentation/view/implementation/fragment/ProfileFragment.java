package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment;


import com.arellomobile.mvp.presenter.InjectPresenter;

import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.ProfilePresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment.base.BaseFragment;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.IProfileView;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.base.IProgressView;

public class ProfileFragment extends BaseFragment<ProfilePresenter> implements IProfileView {

    @InjectPresenter
    ProfilePresenter mProfilePresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.layout_profile_fragment;
    }

    @Override
    protected ProfilePresenter getPresenter() {
        return mProfilePresenter;
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
}
