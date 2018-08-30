package clem.app.mvp.base;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.uber.autodispose.AutoDisposeConverter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import clem.app.mvp.mvp.IPresenter;
import clem.app.mvp.utils.RxLifecycleUtils;

/**
 * Created by QingMei on 2017/8/14.
 * desc:
 */

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity {
    private Unbinder mUnbinder;

    @Inject
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setupActivityComponent();
        mUnbinder = ButterKnife.bind(this);
        initLifecycleObserver(getLifecycle());
        initView();
        initData();
    }

    protected abstract void setupActivityComponent();

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
    }

    protected abstract int getLayoutId();

    @CallSuper
    @MainThread
    protected void initLifecycleObserver(@NonNull Lifecycle lifecycle) {
        presenter.setLifecycleOwner(this);
        lifecycle.addObserver(presenter);
    }

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY)
            mUnbinder.unbind();
        this.mUnbinder = null;
    }

    @MainThread
    @Override
    public void showLoading() {

    }

    @MainThread
    @Override
    public void hideLoading() {

    }
}
