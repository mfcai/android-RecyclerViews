package com.anlia.recyclerviews.helper;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zgj on 2017/11/6:11:09.
 * des:
 */

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    /**
     * 标记是否正在向上滑动
     */
    boolean isSlidingUpward = false;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        isSlidingUpward = dy>0;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if(newState == RecyclerView.SCROLL_STATE_IDLE){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
            int item_count =linearLayoutManager.getItemCount();
            int lastCompletedVisiblePos =linearLayoutManager.findLastCompletelyVisibleItemPosition();
            if(lastCompletedVisiblePos == item_count - 1 && isSlidingUpward){
                onLoadMoreData();
            }
        }
    }

    /**
     * 加载更多数据的方法
     */
    public abstract  void onLoadMoreData() ;
}
