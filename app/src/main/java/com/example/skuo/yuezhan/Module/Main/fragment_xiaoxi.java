package com.example.skuo.yuezhan.Module.Main;

import android.os.Bundle;

import com.example.skuo.yuezhan.Base.BaseFragment;
import com.example.skuo.yuezhan.Module.Main.adapter.MyAdapter;
import com.example.skuo.yuezhan.R;

import butterknife.BindView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by apple on 2016/12/12.
 */

public class fragment_xiaoxi extends BaseFragment {
    private StickyListHeadersListView lv_sticky;

    @BindView(R.id.lv_sticky)StickyListHeadersListView stickyListHeadersListView;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_xiaoxi;
    }



    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

        lv_sticky = (StickyListHeadersListView) mRootView.findViewById(R.id.lv_sticky);
        lv_sticky.setAdapter(new MyAdapter(getActivity()));


    }
}
