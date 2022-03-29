package com.perficient.praxis.gildedrose.business;

import com.perficient.praxis.gildedrose.error.ResourceNotFoundException;
import com.perficient.praxis.gildedrose.model.Item;
import com.perficient.praxis.gildedrose.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    Item[] items;

    public ItemService(ItemRepository itemRepository, Item[] items) {
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
                    if(item.quality < 50)
                        item.quality++;
                    if(item.sellIn < 0 && item.quality < 50)
                        item.quality++;
                    item.sellIn--;
                break;
                case NORMAL:
                    if(item.quality > 0)
                        item.quality--;
                    if(item.sellIn < 0 && item.quality > 0)
                        item.quality--;
                    item.sellIn--;
                break;
                case TICKETS:
                    if(item.sellIn <= 0)
                        item.quality=0;
                    else if(item.sellIn < 6)
                        item.quality+=3;
                    else if(item.sellIn < 11)
                        item.quality+=2;
                    else
                        item.quality++;
                break;
                case LEGENDARY:
                    break;
            }
            itemRepository.save(items[i]);
        }
        return Arrays.asList(items);
    }


    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(int id, Item item) {
        try {
            Item oldItem = findById(id);
            if (item.name == null)
                item.name = oldItem.name;
            if (item.type == null)
                item.type = oldItem.type;
            itemRepository.save(new Item(id, item.name, item.sellIn, item.quality, item.type));
            return item;
        } catch (ResourceNotFoundException exception) {
            throw exception;
        }
    }

    public List<Item> listItems() {
        return itemRepository.findAll();
    }

    public Item findById(int id) {
        return itemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(""));
    }

    public List<Item> createItems(List<Item> items) {
        HashSet<Integer> idList = new HashSet<>();
        for (Item item : items) {
            if (idList.contains(item.getId()) && (itemRepository.findById(item.getId())==null))
                throw new ResourceNotFoundException("There are two items with the same ID") ;
            idList.add(item.getId());
        }
        return itemRepository.saveAll(items);
    }
}
