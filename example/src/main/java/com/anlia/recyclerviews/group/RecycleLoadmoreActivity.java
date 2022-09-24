package com.anlia.recyclerviews.group;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.anlia.recyclerviews.R;
import com.anlia.recyclerviews.adapter.LoadMoreAdapter;
import com.anlia.recyclerviews.helper.EndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RecycleLoadmoreActivity extends AppCompatActivity {

    RecyclerView arlRvRecycleview;
    SwipeRefreshLayout arlSrlRefresh;

    private LoadMoreAdapter loadMoreAdapter;

    private ArrayList<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_loadmore);
        arlRvRecycleview = (RecyclerView)findViewById(R.id.arl_rv_recycleview);
        arlSrlRefresh=(SwipeRefreshLayout)findViewById(R.id.arl_srl_refresh);
        initListener();
        loadData();
        initAdapter();

    }

    /**
     * 加载监听
     */
    private void initListener() {
        arlSrlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataList.clear();
                loadData();
                loadMoreAdapter.setLoadState(loadMoreAdapter.LOADING_COMPLETE);
                loadMoreAdapter.notifyDataSetChanged();


                // 延时1s关闭下拉刷新
                arlSrlRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (arlSrlRefresh != null && arlSrlRefresh.isRefreshing()) {
                            arlSrlRefresh.setRefreshing(false);
                        }
                    }
                }, 1000);
            }
        });

    }

    private void initAdapter() {
        loadMoreAdapter = new LoadMoreAdapter(dataList);
        arlRvRecycleview.setAdapter(loadMoreAdapter);

        arlRvRecycleview.setLayoutManager(new LinearLayoutManager(this));

        arlRvRecycleview.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMoreData() {
                loadMoreAdapter.setLoadState(loadMoreAdapter.LOADING);

                if (dataList.size() < 52) {
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    loadData();
                                    loadMoreAdapter.setLoadState(
                                            loadMoreAdapter.LOADING_COMPLETE);
                                }
                            });
                        }
                    },3000);
                }else{
                    loadMoreAdapter.setLoadState(
                            loadMoreAdapter.LOADING_END);
                }
            }
        });
    }

    /**
     * 加载数据
     */
    private void loadData() {
        char letter = 'A';
        for (int i = 0; i < 26; i++) {
            dataList.add(String.valueOf(letter));
            letter++;
        }
    }
}


