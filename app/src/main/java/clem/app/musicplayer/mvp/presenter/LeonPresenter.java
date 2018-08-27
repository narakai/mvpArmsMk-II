package clem.app.musicplayer.mvp.presenter;

import android.app.Application;

import javax.inject.Inject;

import clem.app.musicplayer.mvp.contract.LeonContract;
import clem.app.mvp.di.scope.ActivityScope;
import clem.app.mvp.integration.AppManager;
import clem.app.mvp.mvp.BasePresenter;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class LeonPresenter extends BasePresenter<LeonContract.Model, LeonContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    AppManager mAppManager;

    @Inject
    public LeonPresenter(LeonContract.Model model, LeonContract.View rootView) {
        super(model, rootView);
    }

}
