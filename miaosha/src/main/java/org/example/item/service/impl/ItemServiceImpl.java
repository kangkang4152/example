package org.example.item.service.impl;

import org.example.item.service.ItemService;
import org.example.item.service.model.ItemModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ItemServiceImpl implements ItemService {



    @Override
    @Transactional
    public boolean createItem(ItemModel itemModel) {
        return false;
    }

    @Override
    public List<ItemModel> listItem() {
        return null;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        return null;
    }
}
