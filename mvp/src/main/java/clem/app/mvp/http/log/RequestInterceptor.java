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
package clem.app.mvp.http.log;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import clem.app.mvp.http.GlobalHttpHandler;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * ================================================
 * 解析框架中的网络请求和响应结果,并以日志形式输出,调试神器
 * 可使用 {@link GlobalConfigModule.Builder#printHttpLogLevel(Level)} 控制或关闭日志
 * <p>
 * Created by JessYan on 7/1/2016.
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@Singleton
public class RequestInterceptor implements Interceptor {

    private GlobalHttpHandler mHttpRequestHandler;

    @Inject
    public RequestInterceptor(GlobalHttpHandler httpRequestHandler) {
        this.mHttpRequestHandler = httpRequestHandler;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (mHttpRequestHandler != null) {
            // do something before http request like adding specific headers.
            mHttpRequestHandler.onHttpRequestBefore(chain, request);
        }

        Response originalResponse = chain.proceed(request);
        ResponseBody responseBody = originalResponse.body();

        if (mHttpRequestHandler != null) {
            mHttpRequestHandler.onHttpResultResponse(responseBody.toString(), chain, originalResponse);
        }

        return originalResponse;
    }
}
