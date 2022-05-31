package com.perficient.praxis.gildedrose.repository.factory;

import com.perficient.praxis.gildedrose.business.model.Item;
import com.perficient.praxis.gildedrose.repository.model.ItemStored;
import com.perficient.praxis.gildedrose.type.Type;
import org.springframework.stereotype.Service;

@Service
public class ItemStoredFactory {

    public ItemStored createItemStoredFromItem(Item item) {
        int id = item.getId();
        String name = item.name;
        int quality = item.quality;
        int sellIn = item.sellIn;
        Type type = item.type;
        return new ItemStored(id, name, sellIn, quality, type);
    }


}
