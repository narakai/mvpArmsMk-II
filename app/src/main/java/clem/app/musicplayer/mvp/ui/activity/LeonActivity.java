package clem.app.musicplayer.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import butterknife.BindView;
import clem.app.musicplayer.R;
import clem.app.musicplayer.mvp.contract.LeonContract;
import clem.app.musicplayer.mvp.presenter.LeonPresenter;
import clem.app.mvp.base.BaseActivity;
import clem.app.mvp.utils.ArmsUtils;

import static clem.app.mvp.utils.Preconditions.checkNotNull;


public class LeonActivity extends BaseActivity<LeonPresenter> implements LeonContract.View {

    @BindView(R.id.leon_ll)
    LinearLayout mLeonLl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 初始化属于自己Activity的Component对象
         */
//        DaggerLeonComponent
//                .builder()
//                .appComponent(ModuleApplication.getInstance().getAppComponent())
//                //MainModule的构造器中传递的是View接口的实例化对象
//                .leonModule(new LeonModule(this))
//                .build()
//                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_leon;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
