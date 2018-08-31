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
package clem.app.musicplayer.mvp.model;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import clem.app.musicplayer.api.cache.CommonCache;
import clem.app.musicplayer.api.service.UserService;
import clem.app.musicplayer.mvp.contract.UserContract;
import clem.app.musicplayer.mvp.model.entity.User;
import clem.app.mvp.di.scope.ActivityScope;
import clem.app.mvp.integration.IRepositoryManager;
import clem.app.mvp.mvp.BaseModel;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;
import timber.log.Timber;

/**
 * ================================================
 * 展示 Model 的用法
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.3">Model wiki 官方文档</a>
 * Created by JessYan on 09/04/2016 10:56
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@ActivityScope
public class UserModel extends BaseModel implements UserContract.Model {
    public static final int USERS_PER_PAGE = 10;

    @Inject
    public UserModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<List<User>> getUsers(int lastIdQueried, boolean update) {
//        return mRepositoryManager.obtainRetrofitService(UserService.class).getUsers(lastIdQueried, USERS_PER_PAGE);
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
//        如果我们该参数传入为true，那么RxCache就会驱逐对应的缓存数据直接进行网络的新一次请求（即使缓存没有过期）。如果传入为false，说明不驱逐缓存数据，如果缓存数据没有过期，那么就不请求网络，直接读取缓存数据返回
        return Observable.just(mRepositoryManager
                .obtainRetrofitService(UserService.class)
                .getUsers(lastIdQueried, USERS_PER_PAGE))
                .flatMap(new Function<Observable<List<User>>, ObservableSource<List<User>>>() {
                    @Override
                    public ObservableSource<List<User>> apply(@NonNull Observable<List<User>> listObservable) {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getUsers(listObservable
                                        , new DynamicKey(lastIdQueried)
                                        , new EvictDynamicKey(update))
                                .map(listReply -> listReply.getData());
                    }
                });

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        Timber.d("Release Resource");
    }

}
