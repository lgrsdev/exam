package com.cyberark.items.services;

import com.cyberark.items.entities.FeaturedItem;
import com.cyberark.items.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CalcServiceFactory {

    @Autowired
    private ApplicationContext сontext;

    public CalcService getCalcService(Item item) {
        if (item instanceof FeaturedItem) {
            return (CalcService) сontext.getBean(((FeaturedItem)item).getFeature() + CalcServiceImpl.class.getSimpleName());
        }
        if ("Scotch Bottle".equals(item.getName())) {
            return new ScotchCalcServiceImpl();
        }
        if ("Basketball".equals(item.getName())) {
            return new BasketballCalcServiceImpl();
        }
        return new CalcServiceImpl();
    }

}
