package com.perficient.praxis.gildedrose.controller.factory;

import com.perficient.praxis.gildedrose.business.model.Item;
import com.perficient.praxis.gildedrose.controller.model.ItemRequest;
import com.perficient.praxis.gildedrose.type.Type;
import org.springframework.stereotype.Service;

@Service
public class ItemRequestFactory {

    private ItemRequest createItem(int id, String name, int sellIn, int quality, Type type){
        return new ItemRequest(id, name, sellIn,quality,type);
    }

    public ItemRequest createItemRequestFromItem(Item item){
        int id = item.getId();;
        String name = item.name;
        int sellIn = item.sellIn;
        int quality = item.quality;
        Type type = item.type;
        return createItem(id, name, sellIn, quality, type);
    }
}
