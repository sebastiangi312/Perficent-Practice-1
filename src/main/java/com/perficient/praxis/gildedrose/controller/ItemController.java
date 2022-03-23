package com.perficient.praxis.gildedrose.controller;

import com.perficient.praxis.gildedrose.business.ItemService;
import com.perficient.praxis.gildedrose.error.ResourceNotFoundException;
import com.perficient.praxis.gildedrose.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public ResponseEntity<List<Item>> listItems(){
        var items = itemService.listItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findById(@PathVariable int id){
        var item = itemService.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        Item createdItem = itemService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable int id,
                                           @RequestBody Item item){
        Item createdItem = itemService.updateItem(id, item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PostMapping("/quality")
    public ResponseEntity<List<Item>> updateItemsQuality() {
        var items = itemService.updateQuality();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<List<Item>> createItems(@RequestBody List<Item> items){
        List<Item> createdItems = itemService.createItems(items);
        return new ResponseEntity<>(createdItems, HttpStatus.OK);
    }
}
