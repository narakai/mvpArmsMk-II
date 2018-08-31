package clem.app.mvp.di.module;

import android.util.Log;

import com.google.gson.Gson;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import clem.app.mvp.utils.DataHelper;
import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * Created by QingMei on 2017/9/1.
 * desc:
 */
@Module
public class CacheModule {

    private final File cacheDir;

    public CacheModule(File cacheDir) {
        this.cacheDir = cacheDir;
    }

    @Provides
    @Singleton
    RxCache provideRxCache(Gson gson) {
//        Log.d("DIR", "provideRxCache: " + cacheDir);
//         /storage/emulated/0/Android/data/clem.app.musicplayer/cache
        return new RxCache.Builder()
                .persistence(cacheDir, new GsonSpeaker(gson));
    }

//    /**
//     * 需要单独给 {@link RxCache} 提供缓存路径
//     *
//     * @param cacheDir
//     * @return {@link File}
//     */
//    @Singleton
//    @Provides
//    @Named("RxCacheDirectory")
//    public File provideRxCacheDirectory() {
////        File cacheDirectory = new File(cacheDir, "RxCache");
//        return cacheDir;
//    }

}
