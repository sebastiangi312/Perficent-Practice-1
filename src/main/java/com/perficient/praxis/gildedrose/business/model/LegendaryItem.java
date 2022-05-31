package com.perficient.praxis.gildedrose.business.model;

import com.perficient.praxis.gildedrose.type.Type;

public class LegendaryItem extends Item{

    public LegendaryItem(int id, String name, int sellIn, int quality, Type type) {
        super(id, name, sellIn, quality, type);
    }

    public void updateQuality() {
        // DO NOTHING
    }
}
