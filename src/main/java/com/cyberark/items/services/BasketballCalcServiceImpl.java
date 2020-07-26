package com.cyberark.items.services;

import com.cyberark.items.entities.Item;

public class BasketballCalcServiceImpl implements CalcService {

    @Override
    public void calcQuality(Item item) {
        if (item.getQuality() > 0) {
            if (!"Basketball".equals(item.getName())) {
                item.setQuality(item.getQuality() - 1);
            }
            if (item.getSellIn() < 0 && item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 1);
            }
        }
    }
}
