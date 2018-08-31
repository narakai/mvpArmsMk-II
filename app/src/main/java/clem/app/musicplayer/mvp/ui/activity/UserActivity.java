package clem.app.musicplayer.mvp.ui.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import clem.app.musicplayer.R;
import clem.app.musicplayer.di.component.DaggerUserComponent;
import clem.app.musicplayer.di.module.UserModule;
import clem.app.musicplayer.mvp.contract.UserContract;
import clem.app.musicplayer.mvp.model.entity.User;
import clem.app.musicplayer.mvp.presenter.UserPresenter;
import clem.app.mvp.base.BaseActivity;
import clem.app.mvp.base.BaseApplication;

public class UserActivity extends BaseActivity<UserPresenter> implements UserContract.View, SwipeRefreshLayout.OnRefreshListener {
    @Inject
    RxPermissions mRxPermissions;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private BaseQuickAdapter mAdapter;
    private List<User> mUser = new ArrayList<>();

    @Override
    protected void setupActivityComponent() {
        DaggerUserComponent
                .builder()
                .appComponent(BaseApplication.getInstance().getAppComponent())
                //MainModule的构造器中传递的是View接口的实例化对象
                .userModule(new UserModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initView() {
        initRecyclerView();
        mAdapter = new UserAdapter(R.layout.recycle_list, mUser);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                presenter.requestUsers(false);
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void initRecyclerView() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void showLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public RxPermissions getRxPermissions() {
        return mRxPermissions;
    }

    @Override
    public void onRefresh() {
        presenter.requestUsers(true);
    }
}
