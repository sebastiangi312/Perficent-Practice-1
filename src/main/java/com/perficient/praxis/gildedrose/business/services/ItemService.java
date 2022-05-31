package com.perficient.praxis.gildedrose.business.services;

import com.perficient.praxis.gildedrose.error.DuplicateItemException;
import com.perficient.praxis.gildedrose.error.ResourceNotFoundException;
import com.perficient.praxis.gildedrose.business.model.Item;
import com.perficient.praxis.gildedrose.handler.RepositoryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final RepositoryHandler repositoryHandler;

    public ItemService(RepositoryHandler repositoryHandler) {
        this.repositoryHandler = repositoryHandler;
    }


    public Item createItem(Item item) {
        List<Item> itemsInRepository = repositoryHandler.listItems();
        if (itemsInRepository.contains(item))
            throw new DuplicateItemException("");
        return repositoryHandler.createItem(item);
    }

    public Item updateItem(int id, Item item) {
        try {
            Item oldItem = findById(id);
            if (item.name == null)
                item.name = oldItem.name;
            if (item.type == null)
                item.type = oldItem.type;
            return repositoryHandler.updateItem(item);
        } catch (ResourceNotFoundException exception) {
            throw exception;
        }
    }

    public List<Item> listItems() {
        return repositoryHandler.listItems();
    }

    public Item findById(int id) {
        return repositoryHandler.findById(id);
    }

    public List<Item> createItems(List<Item> items) {
        List<Item> itemsInRepository = repositoryHandler.listItems();
        for (Item item : items) {
            if (itemsInRepository.contains(item))
                throw new DuplicateItemException("");
            itemsInRepository.add(item);
        }
        return repositoryHandler.createItems(items);
    }

    public Item deleteItem(int itemID) {
        return repositoryHandler.deleteItem(itemID);
    }
}