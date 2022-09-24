package com.anlia.library.treerecylerview.item;



import java.util.List;

public interface ItemManager<T extends BaseItem> {
    //增
    void addItem(T item);

    void addItem(int position, T item);

    void addItems(List<T> items);

    void addItems(int position, List<T> items);

    //删
    void removeItem(T item);

    void removeItem(int position);

    void removeItems(List<T> items);


    //改
    void replaceItem(int position, T item);

    //查
    T getItem(int position);

    void notifyDataChanged();

    int getItemPosition(T item);

}