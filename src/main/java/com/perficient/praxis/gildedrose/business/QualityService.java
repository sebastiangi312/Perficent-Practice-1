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
        var items = itemsList.toArray(new Item[itemsList.size()]);

        for (int i = 0; i < items.length; i++) {
            var item = items[i];
            switch (item.type){
                case AGED:
                    updateQualityAgedItem(item);
                    break;
                case NORMAL:
                    updateQualityNormalItem(item);
                    break;
                case TICKETS:
                    updateQualityTicketsItem(item);
                    break;
                case LEGENDARY:
                    break;
            }
            itemRepository.save(items[i]);
        }
        return Arrays.asList(items);
    }

    private void updateQualityTicketsItem(Item item) {
        item.sellIn--;
        if(item.sellIn <= 0)
            item.quality=0;
        else if(item.sellIn < 6)
            item.quality+=3;
        else if(item.sellIn < 11)
            item.quality+=2;
        else
            item.quality++;
    }

    private void updateQualityNormalItem(Item item) {
        if(item.quality > 0)
            item.quality--;
        if(item.sellIn < 0 && item.quality > 0)
            item.quality--;
        item.sellIn--;
    }

    private void updateQualityAgedItem(Item item) {
        if(item.quality < 50)
            item.quality++;
        if(item.sellIn < 0 && item.quality < 50)
            item.quality++;
        item.sellIn--;
    }
}