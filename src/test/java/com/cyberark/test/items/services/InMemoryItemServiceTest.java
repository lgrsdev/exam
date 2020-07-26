package com.cyberark.test.items.services;

import com.cyberark.items.entities.FeaturedItem;
import com.cyberark.items.entities.Item;
import com.cyberark.items.services.CalcServiceFactory;
import com.cyberark.items.services.InMemoryItemService;
import com.cyberark.items.services.ItemService;
import com.cyberark.items.services.PremiumCalcServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@Import({InMemoryItemService.class, PremiumCalcServiceImpl.class})
@SpringBootTest(classes = {InMemoryItemService.class, CalcServiceFactory.class, PremiumCalcServiceImpl.class})
public class InMemoryItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void checkScotchBottleQuality()
    {
        List<Item> items = new ArrayList<>();
        Item item = new Item(1, "Scotch Bottle", 10, 10);
        items.add(item);

        itemService.updateQuality(items);
        Assertions.assertThat(item.getQuality()).isEqualTo(11);
    }

    @Test
    public void checkTShirtQuality()
    {
        List<Item> items = new ArrayList<>();
            Item item = new Item(1, "T-Shirt", 10, 10);
        items.add(item);

        itemService.updateQuality(items);
        Assertions.assertThat(item.getQuality()).isEqualTo(9);
    }

    @Test
    public void checkPremiumTShirtQuality()
    {
        List<Item> items = new ArrayList<>();
        FeaturedItem item = new FeaturedItem(1, "T-Shirt", 10, 10, "Premium");
        items.add(item);

        itemService.updateQuality(items);
        Assertions.assertThat(item.getQuality()).isEqualTo(8);
    }

    @Test
    public void checkBeerQuality()
    {
        List<Item> items = new ArrayList<Item>();
        Item item = new Item(1, "Beer", 10, 10);
        items.add(item);

        itemService.updateQuality(items);
        Assertions.assertThat(item.getQuality()).isEqualTo(9);
    }

    @Test
    public void checkBasketballQuality()
    {
        List<Item> items = new ArrayList<Item>();
        Item item = new Item(1, "Basketball", 10, 10);
        items.add(item);

        itemService.updateQuality(items);
        Assertions.assertThat(item.getQuality()).isEqualTo(10);
    }

    @Test
    public void checkNoBiggerThan50Quality()
    {
        List<Item> items = new ArrayList<Item>();
        Item item = new Item(1, "Scotch Bottle", 10, 50);
        items.add(item);

        itemService.updateQuality(items);
        Assertions.assertThat(item.getQuality()).isEqualTo(50);
    }

    @Test
    public void checkNoLessThan0Quality()
    {
        List<Item> items = new ArrayList<Item>();
        Item item = new Item(1, "Beer", 10, 0);
        items.add(item);

        itemService.updateQuality(items);
        Assertions.assertThat(item.getQuality()).isEqualTo(0);
    }

    @Test
    public void checkSellinPassQuality2TimeFaster()
    {
        List<Item> items = new ArrayList<Item>();
        Item item = new Item(1, "Beer", 0, 2);
        items.add(item);

        itemService.updateQuality(items);
        Assertions.assertThat(item.getQuality()).isEqualTo(0);
    }

}