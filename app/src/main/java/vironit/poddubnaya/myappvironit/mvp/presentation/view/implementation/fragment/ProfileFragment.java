package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment;


import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.ProfilePresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment.base.BaseFragment;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.IProfileView;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.base.IProgressView;

public class ProfileFragment extends BaseFragment<ProfilePresenter> implements IProfileView {

    @InjectPresenter
    ProfilePresenter mProfilePresenter;

    @BindView(R.id.btn_edit_photo)
    FloatingActionButton mEditPhotoButton;

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
}
