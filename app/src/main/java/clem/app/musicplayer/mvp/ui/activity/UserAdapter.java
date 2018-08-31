package clem.app.musicplayer.mvp.ui.activity;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import clem.app.musicplayer.R;
import clem.app.musicplayer.mvp.model.entity.User;

public class UserAdapter extends BaseQuickAdapter<User, BaseViewHolder> {
    public UserAdapter(int layoutResId, @Nullable List<User> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {
        helper.setText(R.id.tv_name, item.getId());
    }
}
