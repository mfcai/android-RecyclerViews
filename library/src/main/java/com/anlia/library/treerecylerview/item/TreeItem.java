package com.anlia.library.treerecylerview.item;

import android.support.annotation.Nullable;



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
