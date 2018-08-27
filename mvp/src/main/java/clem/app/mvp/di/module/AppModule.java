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
package clem.app.mvp.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import clem.app.mvp.base.BaseApplication;
import clem.app.mvp.integration.AppManager;
import dagger.Module;
import dagger.Provides;

/**
 * ================================================
 * 提供一些框架必须的实例的 {@link Module}
 * <p>
 * Created by JessYan on 8/4/2016.
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@Module
public class AppModule {

    //先来分析一下，需要哪些类是单例的，单例创建的，都和BaseApplication关联起来
    @Singleton
    @Provides
    public BaseApplication provideBaseApplication() {
        return BaseApplication.getInstance();
    }

    @Singleton
    @Provides
    public Gson provideGson(BaseApplication application) {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    /**
     * 之前 {@link AppManager} 使用 Dagger 保证单例, 只能使用 {@link AppComponent#appManager()} 访问
     * 现在直接将 AppManager 独立为单例类, 可以直接通过静态方法 {@link AppManager#getAppManager()} 访问, 更加方便
     * 但为了不影响之前使用 {@link AppComponent#appManager()} 获取 {@link AppManager} 的项目, 所以暂时保留这种访问方式
     *
     * @param application
     * @return
     */
    @Singleton
    @Provides
    public AppManager provideAppManager(BaseApplication application){
        return AppManager.getAppManager().init(application);
    }

//    如果你有提供实例类的方法只调用构造函数注入接口。在dagger中使用@Binds注解可以代替原有的样板模式。
//    @Binds
//    abstract IRepositoryManager bindRepositoryManager(RepositoryManager repositoryManager);
//
}
