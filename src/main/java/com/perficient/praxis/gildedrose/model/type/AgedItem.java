package com.perficient.praxis.gildedrose.model.type;

import com.perficient.praxis.gildedrose.factory.ItemFactory;
import com.perficient.praxis.gildedrose.model.Item;

public class AgedItem extends Item {

    public AgedItem(int id, String name, int sellIn, int quality, ItemFactory.Type type) {
        super(id, name, sellIn, quality, type);
    }

    @Override
    public void updateQuality() {
        if(quality < 50)
            quality++;
        if(sellIn < 0 && quality < 50)
            quality++;
        sellIn--;
    }
}
