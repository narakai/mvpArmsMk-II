/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clem.app.mvp.di.component;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;

import javax.inject.Singleton;

import clem.app.mvp.base.BaseApplication;
import clem.app.mvp.di.module.AppModule;
import clem.app.mvp.di.module.CacheModule;
import clem.app.mvp.di.module.ClientModule;
import clem.app.mvp.di.module.GlobalConfigModule;
import clem.app.mvp.integration.IRepositoryManager;
import dagger.Component;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.OkHttpClient;

/**
 * ================================================
 * 可通过 {@link ArmsUtils#obtainAppComponentFromContext(Context)} 拿到此接口的实现类
 * 拥有此接口的实现类即可调用对应的方法拿到 Dagger 提供的对应实例
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.2">AppComponent wiki 官方文档</a>
 * Created by JessYan on 8/4/2016
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class, GlobalConfigModule.class, CacheModule.class})
public interface AppComponent {
//    BaseApplication baseApplication();

    /**
     * 用于管理网络请求层, 以及数据缓存层
     *
     * @return {@link IRepositoryManager}
     */
//    IRepositoryManager repositoryManager();

    /**
     * RxJava 错误处理管理类
     *
     * @return {@link RxErrorHandler}
     */
    RxErrorHandler rxErrorHandler();

    /**
     * 图片加载管理器, 用于加载图片的管理类, 使用策略者模式, 可在运行时动态替换任何图片加载框架
     * arms-imageloader-glide 提供 Glide 的策略实现类, 也可以自行实现
     * 需要在 {@link ConfigModule#applyOptions(Context, GlobalConfigModule.Builder)} 中
     * 手动注册 {@link BaseImageLoaderStrategy}, {@link ImageLoader} 才能正常使用
     *
     * @return
     */
//    ImageLoader imageLoader();

    /**
     * 网络请求框架
     *
     * @return {@link OkHttpClient}
     */
    OkHttpClient okHttpClient();

    /**
     * Json 序列化库
     *
     * @return {@link Gson}
     */
    Gson gson();

    /**
     * 缓存文件根目录 (RxCache 和 Glide 的缓存都已经作为子文件夹放在这个根目录下), 应该将所有缓存都统一放到这个根目录下
     * 便于管理和清理, 可在 {@link ConfigModule#applyOptions(Context, GlobalConfigModule.Builder)} 种配置
     *
     * @return {@link File}
     */
//    File cacheFile();

    void inject(BaseApplication application);

}
