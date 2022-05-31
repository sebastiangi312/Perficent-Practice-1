package com.perficient.praxis.gildedrose.controller;

import com.perficient.praxis.gildedrose.controller.model.ItemRequest;
import com.perficient.praxis.gildedrose.handler.ServiceItemHandler;
import com.perficient.praxis.gildedrose.handler.QualityServiceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/items")
public class ItemController {

    private final ServiceItemHandler serviceItemHandler;
    private final QualityServiceHandler qualityServiceHandler;

    public ItemController(ServiceItemHandler serviceItemHandler, QualityServiceHandler qualityServiceHandler) {
        this.serviceItemHandler = serviceItemHandler;
        this.qualityServiceHandler = qualityServiceHandler;
    }

    @GetMapping()
    public ResponseEntity<List<ItemRequest>> listItems(){
        var items = serviceItemHandler.listItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemRequest> findById(@PathVariable int id){
        var item = serviceItemHandler.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemRequest> createItem(@RequestBody ItemRequest item){
        ItemRequest createdItem = serviceItemHandler.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemRequest> updateItem(@PathVariable int id,
                                                  @RequestBody ItemRequest item){
        ItemRequest createdItem = serviceItemHandler.updateItem(id, item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PostMapping("/quality")
    public ResponseEntity<List<ItemRequest>> updateItemsQuality() {
        var items = qualityServiceHandler.updateQuality();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<List<ItemRequest>> createItems(@RequestBody List<ItemRequest> items){
        List<ItemRequest> createdItems = serviceItemHandler.createItems(items);
        return new ResponseEntity<>(createdItems, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemRequest> deleteItem(@PathVariable int id){
        ItemRequest itemDeleted = serviceItemHandler.deleteItem(id);
        return new ResponseEntity<>(itemDeleted, HttpStatus.OK);
    }
}
