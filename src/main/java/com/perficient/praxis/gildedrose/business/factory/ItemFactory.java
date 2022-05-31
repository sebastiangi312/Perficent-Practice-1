package com.perficient.praxis.gildedrose.business.factory;

import com.perficient.praxis.gildedrose.business.model.*;
import com.perficient.praxis.gildedrose.controller.model.ItemRequest;
import com.perficient.praxis.gildedrose.repository.model.ItemStored;
import com.perficient.praxis.gildedrose.type.Type;
import org.springframework.stereotype.Service;

@Service
public class ItemFactory {

    private Item createItem(int id, String name, int sellIn, int quality, Type type){
        if(type == null) return new NormalItem(id, name, sellIn, quality, Type.NORMAL);
        Item newItem = null;
        switch (type){
            case AGED:
                newItem = new AgedItem(id, name, sellIn, quality, type);
                break;
            case NORMAL:
                newItem = new NormalItem(id, name, sellIn, quality, type);
                break;
            case TICKETS:
                newItem = new TicketItem(id, name, sellIn, quality, type);
                break;
            case LEGENDARY:
                newItem = new LegendaryItem(id, name, sellIn, quality, type);
                break;
        }
        return newItem;
    }

    public Item createItemFromItemStored(ItemStored itemStored){
        int id = itemStored.getId();
        String name = itemStored.name;
        int sellIn = itemStored.sellIn;
        int quality = itemStored.quality;
        Type type = itemStored.type;
        return createItem(id, name, sellIn, quality, type);
    }

    public Item createItemFromItemRequest(ItemRequest itemRequest){
        int id = itemRequest.getId();
        String name = itemRequest.name;
        int sellIn = itemRequest.sellIn;
        int quality = itemRequest.quality;
        Type type = itemRequest.type;
        return createItem(id, name, sellIn, quality, type);
    }
}
