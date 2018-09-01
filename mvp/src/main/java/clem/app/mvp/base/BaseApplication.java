package clem.app.mvp.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;
import android.support.v4.content.ContextCompat;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import clem.app.mvp.BuildConfig;
import clem.app.mvp.di.component.AppComponent;
import clem.app.mvp.di.module.CacheModule;
import clem.app.mvp.di.module.ClientModule;
import clem.app.mvp.di.module.GlobalConfigModule;
import clem.app.mvp.http.GlobalHttpHandler;
import clem.app.mvp.utils.Preconditions;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;

public abstract class BaseApplication extends MultiDexApplication implements App {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    //mAppComponent接口的实现类dagger
    protected AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        BaseApplication.instance = this;
        injectApp();
    }

    protected abstract void injectApp();

    /**
     * 这是类库底层的injectApp代码示例，你应该在你的Module中重写该方法:
     * <p>
     * DaggerBaseAppComponent.builder()
     * .cacheModule(getCacheModule())
     * .appModule(getAppModule())
     * .globalConfigModule(getGlobalConfigModule())
     * .httpClientModule(getHttpClientModule())
     * .serviceModule(getServiceModule())
     * .build()
     * .initDI(this);
     */

    /**
     * 将 {@link AppComponent} 返回出去, 供其它地方使用, {@link AppComponent} 接口中声明的方法返回的实例, 在 {@link #getAppComponent()} 拿到对象后都可以直接使用
     *
     * @return AppComponent
     * @see ArmsUtils#obtainAppComponentFromContext(Context) 可直接获取 {@link AppComponent}
     */
    @NonNull
    @Override
    public AppComponent getAppComponent() {
        Preconditions.checkNotNull(mAppComponent,
                "%s cannot be null, first call %s#onCreate(Application) in %s#onCreate()",
                AppComponent.class.getName(), getClass().getName(), instance == null
                        ? Application.class.getName() : instance.getClass().getName());
        return mAppComponent;
    }

    protected GlobalConfigModule getGlobalConfigModule() {
        return GlobalConfigModule.buidler()
                //todo
                .baseurl("https://api.github.com/")
                .addInterceptor(new LoggingInterceptor.Builder()
                        .loggable(BuildConfig.DEBUG)
                        .setLevel(Level.BASIC)
                        .log(Platform.INFO)
                        .request("Request")
                        .response("Response")
                        .build())
                .globeHttpHandler(new GlobalHttpHandler() {
                    @Override
                    public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
                        return response;
                    }

                    @Override
                    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
                        return request;
                    }
                })
                .build();
    }

    protected ClientModule getClientModule() {
        return new ClientModule();
    }


    protected CacheModule getCacheModule() {
        return new CacheModule(ContextCompat.getExternalCacheDirs(this)[0]);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        //系统正运行于低内存的状态并且你的进程正处于 LRU 列表中最容易被杀掉的位置, 你应该释放任何不影响你的 App 恢复状态的资源
    }
}