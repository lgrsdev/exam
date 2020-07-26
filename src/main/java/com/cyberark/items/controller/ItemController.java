package com.cyberark.items.controller;

import com.cyberark.items.entities.Item;
import com.cyberark.items.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> findAll() {
        return ResponseEntity.of(Optional.of(itemService.getItems()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findById(@PathVariable("id") long id) {
        Optional<Item> item = Optional.ofNullable(itemService.getItem(id));
        if(item.isPresent()) {
            return ResponseEntity.of(item);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> create(@RequestBody Item item) {
        return new ResponseEntity<>(itemService.createItem(item), HttpStatus.CREATED);
    }
}
