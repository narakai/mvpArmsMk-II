package clem.app.mvp.base;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uber.autodispose.AutoDisposeConverter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import clem.app.mvp.mvp.IPresenter;
import clem.app.mvp.utils.RxLifecycleUtils;

/**
 * Created by QingMei on 2017/8/14.
 */

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IFragment {

    protected View rootView;
    private Unbinder unbinder;

    @Inject
    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getContext()).inflate(getLayoutRes(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initLifecycleObserver(getLifecycle());
        initView(view);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        this.rootView = null;
        super.onDestroy();
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtils.bindLifecycle(this);
    }

    @CallSuper
    @MainThread
    protected void initLifecycleObserver(@NonNull Lifecycle lifecycle) {
        presenter.setLifecycleOwner(this);
        lifecycle.addObserver(presenter);
    }

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract int getLayoutRes();

    @MainThread
    @Override
    public void showLoading() {

    }

    @MainThread
    @Override
    public void hideLoading() {

    }
}
