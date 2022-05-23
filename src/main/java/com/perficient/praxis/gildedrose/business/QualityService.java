package com.perficient.praxis.gildedrose.business;

import com.perficient.praxis.gildedrose.model.Item;
import com.perficient.praxis.gildedrose.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class QualityService {

    private final ItemRepository itemRepository;

    Item[] items;

    public QualityService(ItemRepository itemRepository, Item[] items) {
        this.itemRepository = itemRepository;
        this.items = items;
    }

    public List<Item> updateQuality() {
        var itemsList = itemRepository.findAll();
        for (Item item: itemsList) {
            item.updateQuality();
            itemRepository.save(item);
        }
        return itemsList;
    }

}

