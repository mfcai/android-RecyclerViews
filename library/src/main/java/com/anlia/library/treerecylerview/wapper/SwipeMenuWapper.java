package com.anlia.library.treerecylerview.wapper;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import com.anlia.library.treerecylerview.adapter.BaseRecyclerAdapter;
import com.anlia.library.treerecylerview.item.BaseItem;

import java.util.List;

/**
 * Created by baozi on 2017/5/5.
 * 待开发
 */

public class SwipeMenuWapper<T extends BaseItem> extends BaseRecyclerAdapter<T> {
    private BaseRecyclerAdapter<T> mAdapter;

    public SwipeMenuWapper(BaseRecyclerAdapter<T> adapter) {
        mAdapter = adapter;
        mAdapter.setItemManager(getItemManager());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mAdapter.onBindViewHolder(holder, position);
    }


    @Override
    public int getItemCount() {
        return mAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return mAdapter.getItemViewType(position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public List<T> getDatas() {
        return mAdapter.getDatas();
    }

    @Override
    public void setDatas(List<T> datas) {
        mAdapter.setDatas(datas);
    }
}
