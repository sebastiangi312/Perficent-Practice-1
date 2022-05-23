package com.perficient.praxis.gildedrose.model.type;

import com.perficient.praxis.gildedrose.factory.ItemFactory;
import com.perficient.praxis.gildedrose.model.Item;

public class LegendaryItem extends Item {


    public LegendaryItem(int id, String name, int sellIn, int quality, ItemFactory.Type type) {
        super(id, name, sellIn, quality, type);
    }

    @Override
    public void updateQuality() {
        // The LEGENDARY items, never has to be sold or decreases in Quality
    }
}
