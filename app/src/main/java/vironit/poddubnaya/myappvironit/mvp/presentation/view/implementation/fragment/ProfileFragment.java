package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment;


import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.ProfilePresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment.base.BaseFragment;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.IProfileView;

public class ProfileFragment extends BaseFragment<ProfilePresenter> implements IProfileView {

    @InjectPresenter
    ProfilePresenter mProfilePresenter;

    @OnClick(R.id.btn_edit_photo)
    void changeUserPhoto() {
        mProfilePresenter.changePhoto(this);
    }


    @BindView(R.id.iv_user_photo)
    ImageView mUserPhoto;

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


    @Override
    public void setPhoto(File file) {
        Glide.with(this)
                .load(file)
                .into(mUserPhoto);
    }
}
