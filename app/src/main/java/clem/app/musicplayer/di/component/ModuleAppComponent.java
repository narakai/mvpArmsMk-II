package clem.app.musicplayer.di.component;

import com.google.gson.Gson;

import javax.inject.Singleton;

import clem.app.musicplayer.ModuleApplication;
import clem.app.mvp.di.module.AppModule;
import clem.app.mvp.di.module.CacheModule;
import clem.app.mvp.di.module.ClientModule;
import clem.app.mvp.di.module.GlobalConfigModule;
import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by QingMei on 2017/8/14.
 * desc:
 */
@Singleton
@Component(modules = {
        AppModule.class,
        ClientModule.class,
        CacheModule.class,
        GlobalConfigModule.class,
})
public interface ModuleAppComponent {

    Gson gson();

    OkHttpClient okHttpClient();

    ModuleApplication moduleApplication();

    void inject(ModuleApplication application);
}
