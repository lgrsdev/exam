package com.cyberark.items.services;

import com.cyberark.items.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InMemoryItemService implements ItemService {

    @Autowired
    private CalcServiceFactory calcServiceFactory;

    private long idSequence;
    private List<Item> items = new LinkedList<>();

    @PostConstruct
    public void init() {
        idSequence = 1;
        items.add(new Item(idSequence++,"T-Shirt", 10, 20));
        items.add(new Item(idSequence++, "Scotch Bottle", 2, 0));
        items.add(new Item(idSequence++, "Beer", 5, 7));
        items.add(new Item(idSequence++,"Basketball", 0, 50));
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public Item getItem(long id) {
        Map<Long, Item> itemsMap = items.stream().
                collect(Collectors.toMap(Item::getId, Function.identity()));

        return itemsMap.get(id);
    }

    @Override
    public Item createItem(Item item) {
        if (item == null) {
            return null;
        }

        Item createdItem = new Item(idSequence++, item.getName(), item.getSellIn(), item.getQuality());

        items.add(createdItem);

        return createdItem;
    }

    @Override
    public void updateQuality(List<Item> items) {
        for (Item item : items) {
            calcServiceFactory.getCalcService(item).calcQuality(item);
        }
    }
}