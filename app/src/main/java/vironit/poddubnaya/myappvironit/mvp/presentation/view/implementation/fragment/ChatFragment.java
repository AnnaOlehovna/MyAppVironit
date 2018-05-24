package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;

import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.ChatPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment.base.BaseFragment;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.IChatView;

public class ChatFragment extends BaseFragment<ChatPresenter> implements IChatView {

    @InjectPresenter
    ChatPresenter mChatPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.layout_chat_fragment;
    }

    @Override
    protected ChatPresenter getPresenter() {
        return mChatPresenter;
    }
    public static ChatFragment newInstance(){
        return new ChatFragment();
    }
}
