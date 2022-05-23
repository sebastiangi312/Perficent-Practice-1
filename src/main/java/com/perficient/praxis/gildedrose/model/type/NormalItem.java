package com.perficient.praxis.gildedrose.model.type;

import com.perficient.praxis.gildedrose.factory.ItemFactory;
import com.perficient.praxis.gildedrose.model.Item;

public class NormalItem extends Item {

    public NormalItem(int id, String name, int sellIn, int quality, ItemFactory.Type type) {
        super(id, name, sellIn, quality, type);
    }

    @Override
    public void updateQuality() {
        if(quality > 0)
            quality--;
        if(sellIn < 0 && quality > 0)
            quality--;
        sellIn--;
    }
}
