package clem.app.mvp.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import clem.app.mvp.di.component.AppComponent;
import clem.app.mvp.utils.Preconditions;


/**
 * Created by QingMei on 2017/8/14.
 * desc:
 */

public abstract class BaseApplication extends MultiDexApplication implements App {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    //mAppComponent接口的实现类dagger
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        BaseApplication.instance = this;
    }

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