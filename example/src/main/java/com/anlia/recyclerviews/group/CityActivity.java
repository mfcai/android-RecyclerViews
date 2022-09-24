package com.anlia.recyclerviews.group;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.alibaba.fastjson.JSON;
import com.anlia.library.treerecylerview.adapter.TreeRecyclerAdapter;
import com.anlia.library.treerecylerview.wapper.ItemFactory;
import com.anlia.recyclerviews.R;
import com.anlia.recyclerviews.bean.CityBean;
import com.anlia.recyclerviews.bean.OneTreeItemParent;

import java.util.List;

public class CityActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rl_content);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 10;
                if (view.getLayoutParams() instanceof GridLayoutManager.LayoutParams) {
                    GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                    int spanIndex = layoutParams.getSpanIndex();//在一行中所在的角标，第几列
                    if (spanIndex != ((GridLayoutManager) parent.getLayoutManager()).getSpanCount() - 1) {
                        outRect.right = 10;
                    }
                }
            }
        });
        List<CityBean> cityBeen = JSON.parseArray(getResources().getString(R.string.location), CityBean.class);

        List<OneTreeItemParent> itemList = ItemFactory.createItemList(cityBeen, OneTreeItemParent.class);
        for(int i=0;i<itemList.size();i++){
            OneTreeItemParent treeItem = itemList.get(i);
            Log.d("caimingfu","treeItem province name:"+treeItem.getData().getProvinceName());
            List<CityBean.CitysBean> cityList =treeItem.getData().getCitys();
            for(int j=0;j<cityList.size();j++){
                CityBean.CitysBean citysBean =cityList.get(j);
                Log.d("caimingfu","city name:"+citysBean.getCityName());
                for (CityBean.CitysBean.AreasBean areaBean:citysBean.getAreas()){
                    Log.d("caimingfu","area name:"+areaBean.getAreaName());
                }
            }
        }
        TreeRecyclerAdapter<OneTreeItemParent> treeRecyclerAdapter = new TreeRecyclerAdapter<OneTreeItemParent>();
        treeRecyclerAdapter.setDatas(itemList);
        recyclerView.setAdapter(treeRecyclerAdapter);
    }
}
