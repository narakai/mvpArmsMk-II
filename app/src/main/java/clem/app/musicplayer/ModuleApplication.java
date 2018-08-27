package clem.app.musicplayer;


import clem.app.musicplayer.di.component.DaggerModuleAppComponent;
import clem.app.mvp.base.BaseApplication;

public class ModuleApplication extends BaseApplication {

    @Override
    protected void injectApp() {
        DaggerModuleAppComponent.builder()
                .globalConfigModule(getGlobalConfigModule())
                .appModule(getAppModule())
                .cacheModule(getCacheModule())
                .clientModule(getClientModule())
                .build().inject(this);
    }
}