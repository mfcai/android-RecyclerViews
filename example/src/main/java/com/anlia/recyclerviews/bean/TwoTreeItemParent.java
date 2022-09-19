package com.anlia.recyclerviews.bean;



import com.anlia.library.treerecylerview.item.BaseItem;
import com.anlia.library.treerecylerview.item.TreeItemGroup;
import com.anlia.library.treerecylerview.wapper.ItemFactory;
import com.anlia.library.treerecylerview.wapper.ViewHolder;
import com.anlia.recyclerviews.R;

import java.util.List;

/**
 */
public class TwoTreeItemParent extends TreeItemGroup<CityBean.CitysBean> {

    @Override
    public List<? extends BaseItem> initChildsList(CityBean.CitysBean data) {
        return ItemFactory.createItemList(data.getAreas(), ThreeItem.class);
    }


    @Override
    public int initLayoutId() {
        return R.layout.item_two;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, getData().getCityName());
    }

    @Override
    public boolean isCanChangeExpand() {
        return data.getCityName().equals("朝阳区");
    }

    @Override
    public boolean canExpandOrCollapse() {
        return false;
    }
}
