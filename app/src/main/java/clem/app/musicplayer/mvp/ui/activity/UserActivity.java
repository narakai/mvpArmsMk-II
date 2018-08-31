package clem.app.musicplayer.mvp.ui.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import clem.app.musicplayer.R;
import clem.app.musicplayer.di.component.DaggerUserComponent;
import clem.app.musicplayer.di.module.UserModule;
import clem.app.musicplayer.mvp.contract.UserContract;
import clem.app.musicplayer.mvp.presenter.UserPresenter;
import clem.app.mvp.base.BaseActivity;
import clem.app.mvp.base.BaseApplication;

public class UserActivity extends BaseActivity<UserPresenter> implements UserContract.View {
    @Inject
    RxPermissions mRxPermissions;
    @BindView(R.id.tv)
    TextView mTv;

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
        mTv.setText("Hello Dagger2");
    }

    @Override
    protected void initData() {

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
}
