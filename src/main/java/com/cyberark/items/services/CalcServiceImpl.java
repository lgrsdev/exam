package com.cyberark.items.services;

import com.cyberark.items.entities.Item;

public class CalcServiceImpl implements CalcService{

    @Override
    public void calcQuality(Item item) {

        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }

        item.setSellIn(item.getSellIn() - 1);

        if (item.getSellIn() < 0) {
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 1);
            }
        }
    }
}
