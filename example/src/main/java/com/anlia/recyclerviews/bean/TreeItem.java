package com.anlia.recyclerviews.bean;

import android.support.annotation.Nullable;

import com.anlia.library.treerecylerview.item.BaseItem;
import com.anlia.library.treerecylerview.item.BaseItemData;
import com.anlia.library.treerecylerview.item.TreeItemGroup;


/**
 * TreeRecyclerAdapter的item
 */
public abstract class TreeItem<D extends BaseItemData> extends BaseItem<D> {
    private TreeItemGroup parentItem;

    public void setParentItem(TreeItemGroup parentItem) {
        this.parentItem = parentItem;
    }

    /**
     * 获取当前item的父级
     *
     * @return
     */
    @Nullable
    public TreeItemGroup getParentItem() {
        return parentItem;
    }

}
