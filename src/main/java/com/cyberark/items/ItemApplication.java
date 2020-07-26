package com.cyberark.items;

import com.cyberark.items.services.ItemService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ItemApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ItemApplication.class, args);

        ItemService itemService = context.getBean(ItemService.class);
        System.out.println("Current items: " + itemService.getItems());
    }
}