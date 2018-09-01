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

import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import clem.app.mvp.http.GlobalHttpHandler;
import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

@Module
public class GlobalConfigModule {

    private HttpUrl mApiUrl;
    private List<Interceptor> mInterceptors;
    private File mCacheFile;
    private GlobalHttpHandler mHandler;

    /**
     * @author: jess
     * @date 8/5/16 11:03 AM
     * @description: baseurl
     */
    private GlobalConfigModule(Buidler buidler) {
        this.mApiUrl = buidler.apiUrl;
        this.mHandler = buidler.handler;
        this.mInterceptors = buidler.interceptors;
        this.mCacheFile = buidler.cacheFile;
    }

    public static Buidler buidler() {
        return new Buidler();
    }

    @Singleton
    @Provides
    List<Interceptor> provideInterceptors() {
        return mInterceptors;
    }

    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        return mApiUrl;
    }

    @Singleton
    @Provides
    GlobalHttpHandler provideHttpRequestHandler() {
        return mHandler == null ? GlobalHttpHandler.EMPTY : mHandler;//打印请求信息
    }

    public static final class Buidler {
        private HttpUrl apiUrl = HttpUrl.parse("https://api.github.com/");
        private List<Interceptor> interceptors = new ArrayList<>();
        private GlobalHttpHandler handler;
        private File cacheFile;

        private Buidler() {
        }

        public Buidler baseurl(String baseurl) {
            if (TextUtils.isEmpty(baseurl)) {
                throw new IllegalArgumentException("baseurl can not be empty");
            }
            this.apiUrl = HttpUrl.parse(baseurl);
            return this;
        }

        public Buidler globeHttpHandler(GlobalHttpHandler handler) {// handle the http response before displaying it to users
            this.handler = handler;
            return this;
        }

        public Buidler addInterceptor(Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }

        public Buidler cacheFile(File cacheFile) {
            this.cacheFile = cacheFile;
            return this;
        }

        public GlobalConfigModule build() {
            return new GlobalConfigModule(this);
        }
    }

}
