package com.perficient.praxis.gildedrose.handler;

import com.perficient.praxis.gildedrose.business.factory.ItemFactory;
import com.perficient.praxis.gildedrose.business.model.Item;
import com.perficient.praxis.gildedrose.error.ResourceNotFoundException;
import com.perficient.praxis.gildedrose.repository.ItemRepository;
import com.perficient.praxis.gildedrose.repository.factory.ItemStoredFactory;
import com.perficient.praxis.gildedrose.repository.model.ItemStored;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepositoryHandler {
    private final ItemRepository itemRepository;
    private final ItemFactory itemFactory;
    private final ItemStoredFactory itemStoredFactory;

    public RepositoryHandler(ItemRepository itemRepository, ItemFactory itemFactory, ItemStoredFactory itemStoredFactory) {
        this.itemRepository = itemRepository;
        this.itemFactory = itemFactory;
        this.itemStoredFactory = itemStoredFactory;
    }

    public Item createItem(Item item) {
        ItemStored itemStored = itemStoredFactory.createItemStoredFromItem(item);
        itemStored = itemRepository.save(itemStored);
        Item itemSaved = itemFactory.createItemFromItemStored(itemStored);
        return itemSaved;
    }

    public Item updateItem(Item item) {
        ItemStored itemStored = itemStoredFactory.createItemStoredFromItem(item);
        itemStored = itemRepository.save(itemStored);
        return itemFactory.createItemFromItemStored(itemStored);
    }

    public List<Item> listItems() {
        return transformListItemStoredIntoListItem(itemRepository.findAll());
    }

    public Item findById(int id) {
        ItemStored itemStored = itemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(""));
        return itemFactory.createItemFromItemStored(itemStored);
    }

    public List<Item> createItems(List<Item> items) {
        List<ItemStored> itemsToSave = transformListItemIntoListItemStored(items);
        List<ItemStored> itemsSaved = itemRepository.saveAll(itemsToSave);
        return transformListItemStoredIntoListItem(itemsSaved);
    }

    public Item deleteItem(int itemID) {
        ItemStored itemToEliminate = itemRepository.findById(itemID)
                .orElseThrow(() -> new ResourceNotFoundException("Item no Founded"));
        itemRepository.deleteById(itemID);
        return itemFactory.createItemFromItemStored(itemToEliminate);
    }

    private List<Item> transformListItemStoredIntoListItem(List<ItemStored> itemStoredList){
        return itemStoredList.stream().map(item -> itemFactory.createItemFromItemStored(item)).collect(Collectors.toList());
    }

    private List<ItemStored> transformListItemIntoListItemStored(List<Item> items){
        return items.stream().map(item -> itemStoredFactory.createItemStoredFromItem(item)).collect(Collectors.toList());
    }
}
