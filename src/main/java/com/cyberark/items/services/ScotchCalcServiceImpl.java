package com.cyberark.items.services;

import com.cyberark.items.entities.Item;

public class ScotchCalcServiceImpl implements CalcService {

    @Override
    public void calcQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }

        item.setSellIn(item.getSellIn() - 1);

        if (item.getSellIn() < 0 && item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }


}
