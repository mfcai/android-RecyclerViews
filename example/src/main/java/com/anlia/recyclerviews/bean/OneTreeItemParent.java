package com.anlia.recyclerviews.bean;



import com.anlia.library.treerecylerview.item.TreeItem;
import com.anlia.library.treerecylerview.item.TreeItemGroup;
import com.anlia.library.treerecylerview.wapper.ItemFactory;
import com.anlia.library.treerecylerview.wapper.ViewHolder;
import com.anlia.recyclerviews.R;

import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class OneTreeItemParent extends TreeItemGroup<CityBean> {
    @Override
    public List<? extends TreeItem> initChildsList(CityBean data) {
        return ItemFactory.createTreeItemList(data.getCitys(), TwoTreeItemParent.class, this);
    }

    @Override
    public int initLayoutId() {
        return R.layout.itme_one;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getProvinceName());
    }

    @Override
    public boolean canExpandOrCollapse() {
        return false;
    }
}
