package clem.app.musicplayer.di.module;

import clem.app.musicplayer.mvp.contract.LeonContract;
import clem.app.musicplayer.mvp.model.LeonModel;
import clem.app.mvp.di.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;


@Module
public class LeonModule {
    private LeonContract.View view;

    /**
     * 构建LeonModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public LeonModule(LeonContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    LeonContract.View provideLeonView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    LeonContract.Model provideLeonModel(LeonModel model) {
        return model;
    }
}