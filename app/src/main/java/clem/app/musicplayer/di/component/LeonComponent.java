package clem.app.musicplayer.di.component;

import clem.app.musicplayer.di.module.LeonModule;
import clem.app.musicplayer.mvp.ui.activity.LeonActivity;
import clem.app.mvp.di.component.AppComponent;
import clem.app.mvp.di.scope.ActivityScope;
import dagger.Component;

@ActivityScope
@Component(modules = LeonModule.class, dependencies = AppComponent.class)
public interface LeonComponent {
    void inject(LeonActivity activity);
}