package clem.app.musicplayer;


import clem.app.mvp.base.BaseApplication;
import clem.app.mvp.di.component.DaggerAppComponent;

public class ModuleApplication extends BaseApplication {

    @Override
    protected void injectApp() {
        mAppComponent = DaggerAppComponent.builder()
                .globalConfigModule(getGlobalConfigModule())
                .cacheModule(getCacheModule())
                .clientModule(getClientModule())
                .build();
        mAppComponent.inject(this);
    }
}