package com.cyberark.items.services;

import com.cyberark.items.entities.Item;

import java.util.List;

public interface ItemService {
    List<Item> getItems();
    Item getItem(long id);
    Item createItem(Item item);
    void updateQuality(List<Item> items);
}
