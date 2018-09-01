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

import com.google.gson.Gson;

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

@Singleton
@Component(modules = {AppModule.class, ClientModule.class, GlobalConfigModule.class, CacheModule.class})
public interface AppComponent {
//    标记的依赖公开可见

    BaseApplication baseApplication();

    /**
     * 用于管理网络请求层, 以及数据缓存层
     *
     * @return {@link IRepositoryManager}
     */
    IRepositoryManager repositoryManager();

    /**
     * RxJava 错误处理管理类
     *
     * @return {@link RxErrorHandler}
     */
    RxErrorHandler rxErrorHandler();

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

    void inject(BaseApplication application);

}
