package clem.app.musicplayer.mvp.model;

import android.app.Application;

import com.google.gson.Gson;

import javax.inject.Inject;

import clem.app.musicplayer.mvp.contract.LeonContract;
import clem.app.mvp.di.scope.ActivityScope;
import clem.app.mvp.integration.IRepositoryManager;
import clem.app.mvp.mvp.BaseModel;


@ActivityScope
public class LeonModel extends BaseModel implements LeonContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public LeonModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}