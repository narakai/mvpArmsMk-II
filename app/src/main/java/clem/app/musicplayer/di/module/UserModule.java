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
package clem.app.musicplayer.di.module;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import clem.app.musicplayer.R;
import clem.app.musicplayer.mvp.contract.UserContract;
import clem.app.musicplayer.mvp.model.UserModel;
import clem.app.musicplayer.mvp.model.entity.User;
import clem.app.musicplayer.mvp.view.adapter.UserAdapter;
import clem.app.mvp.di.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    private UserContract.View view;

    /**
     * 构建 UserModule 时,将 View 的实现类传进来,这样就可以提供 View 的实现类给 Presenter
     *
     * @param view
     */
    public UserModule(UserContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserContract.View provideUserView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    UserContract.Model provideUserModel(UserModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    RxPermissions provideRxPermissions() {
        return new RxPermissions(view.getActivity());
    }

    @ActivityScope
    @Provides
    List<User> provideUserList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    UserAdapter provideUserAdapter(List<User> list){
        return new UserAdapter(R.layout.recycle_list, list);
    }
}
