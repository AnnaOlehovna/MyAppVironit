package vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment;

import com.arellomobile.mvp.presenter.InjectPresenter;

import vironit.poddubnaya.myappvironit.R;
import vironit.poddubnaya.myappvironit.mvp.presentation.presenter.NewsPresenter;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.implementation.fragment.base.BaseFragment;
import vironit.poddubnaya.myappvironit.mvp.presentation.view.interfaces.INewsView;

public class NewsFragment extends BaseFragment<NewsPresenter> implements INewsView {

    @InjectPresenter
    NewsPresenter mNewsPresenter;
    @Override
    public int getLayoutResId() {
        return R.layout.layout_news_fragment;
    }

    @Override
    protected NewsPresenter getPresenter() {
        return mNewsPresenter;
    }

    public static NewsFragment newInstance(){
        return new NewsFragment();
    }


}
