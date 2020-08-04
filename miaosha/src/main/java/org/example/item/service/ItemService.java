package org.example.item.service;

import org.example.item.service.model.ItemModel;

import java.util.List;

public interface ItemService {

    public boolean createItem(ItemModel itemModel);

    //商品列表浏览
    public List<ItemModel> listItem();

    //商品详单
    public  ItemModel getItemById(Integer id);
}
