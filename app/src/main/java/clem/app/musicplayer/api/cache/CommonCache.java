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
package clem.app.musicplayer.api.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import clem.app.musicplayer.mvp.model.entity.User;
import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

//@param user：这是个Observable类型的对象，简单来说，这就是你将要缓存的数据对象。
//@param userName:DynamicKey类型，顾名思义，就是一个动态的key，我们以它作为tag，将数据存储到对应名字的File中
//@param evictDynamicKey 可以明确地清理指定的数据 ，很简单，如果我们该参数传入为true，那么RxCache就会驱逐对应的缓存数据直接进行网络的新一次请求（即使缓存没有过期）。如果传入为false，说明不驱逐缓存数据，如果缓存数据没有过期，那么就不请求网络，直接读取缓存数据返回。
//        @return 可以看到，该接口方法中，返回值为Observable,泛型为user，这个Observable的对象user和参数中传进来的Observable的对象user有什么区别呢？
//        — 很简单，返回值Observable中的数据为经过缓存处理的数据。

public interface CommonCache {
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<User>>> getUsers(Observable<List<User>> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);
}
