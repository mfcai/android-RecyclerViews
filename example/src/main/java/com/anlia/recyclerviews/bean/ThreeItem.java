package com.anlia.recyclerviews.bean;


import com.anlia.library.treerecylerview.wapper.ViewHolder;
import com.anlia.recyclerviews.R;

/**
 */
public class ThreeItem extends TreeItem<CityBean.CitysBean.AreasBean> {


    @Override
    public int initLayoutId() {
        return R.layout.item_three;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getAreaName());
    }
}
