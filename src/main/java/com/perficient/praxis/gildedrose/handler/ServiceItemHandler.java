package com.perficient.praxis.gildedrose.handler;

import com.perficient.praxis.gildedrose.business.factory.ItemFactory;
import com.perficient.praxis.gildedrose.business.model.Item;
import com.perficient.praxis.gildedrose.business.services.ItemService;
import com.perficient.praxis.gildedrose.controller.factory.ItemRequestFactory;
import com.perficient.praxis.gildedrose.controller.model.ItemRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceItemHandler {

    private final ItemService itemService;

    private final ItemRequestFactory itemRequestFactory;

    private final ItemFactory itemFactory;

    public ServiceItemHandler(ItemService itemService, ItemRequestFactory itemRequestFactory, ItemFactory itemFactory) {
        this.itemService = itemService;
        this.itemRequestFactory = itemRequestFactory;
        this.itemFactory = itemFactory;
    }

    public ItemRequest createItem(ItemRequest itemRequest) {
        Item item = itemFactory.createItemFromItemRequest(itemRequest);
        return itemRequestFactory.createItemRequestFromItem(itemService.createItem(item));
    }

    public ItemRequest updateItem(int id, ItemRequest itemRequest) {
        Item item = itemFactory.createItemFromItemRequest(itemRequest);
        return itemRequestFactory.createItemRequestFromItem(itemService.updateItem(id, item));
    }

    public List<ItemRequest> listItems() {
        return transformListItemIntoListItemRequest(itemService.listItems());
    }

    public ItemRequest findById(int id) {
        return itemRequestFactory.createItemRequestFromItem(itemService.findById(id));
    }

    public List<ItemRequest> createItems(List<ItemRequest> itemRequestList) {
        List<Item> items =transformListItemRequestIntoListItem(itemRequestList);
        return transformListItemIntoListItemRequest(itemService.createItems(items));
    }

    public ItemRequest deleteItem(int itemID) {
        return itemRequestFactory.createItemRequestFromItem(itemService.deleteItem(itemID));
    }

    private List<Item> transformListItemRequestIntoListItem(List<ItemRequest> itemRequestList){
        return itemRequestList.stream().map(item -> itemFactory.createItemFromItemRequest(item)).collect(Collectors.toList());
    }

    private List<ItemRequest> transformListItemIntoListItemRequest(List<Item> items){
        return items.stream().map(item -> itemRequestFactory.createItemRequestFromItem(item)).collect(Collectors.toList());
    }
}
