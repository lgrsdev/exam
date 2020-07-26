package com.cyberark.items.services;

import com.cyberark.items.entities.Item;
import org.springframework.stereotype.Service;

@Service("PremiumCalcServiceImpl")
public class PremiumCalcServiceImpl implements CalcService {

    @Override
    public void calcQuality(Item item) {

        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 2);
        }

        item.setSellIn(item.getSellIn() - 1);

        if (item.getSellIn() < 0) {
            if (item.getQuality() > 0) {
                item.setQuality(item.getQuality() - 2);
            }
        }
    }


}
