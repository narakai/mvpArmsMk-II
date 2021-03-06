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
import clem.app.mvp.integration.IRepositoryManager;
import clem.app.mvp.integration.RepositoryManager;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class AppModule {

    //先来分析一下，需要哪些类是单例的，单例创建的，都和BaseApplication关联起来
    @Singleton
    @Provides
    static BaseApplication provideBaseApplication() {
        return BaseApplication.getInstance();
    }

    @Singleton
    @Provides
    static Gson provideGson(BaseApplication application) {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

//    如果你有提供实例类的方法只调用构造函数注入接口。在dagger中使用@Binds注解可以代替原有的样板模式。
    @Binds
    abstract IRepositoryManager bindRepositoryManager(RepositoryManager repositoryManager);
//
}
