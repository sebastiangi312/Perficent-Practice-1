package com.perficient.praxis.gildedrose.model.type;

import com.perficient.praxis.gildedrose.factory.ItemFactory;
import com.perficient.praxis.gildedrose.model.Item;

public class TicketItem extends Item {

    public TicketItem(int id, String name, int sellIn, int quality, ItemFactory.Type type) {
        super(id, name, sellIn, quality, type);
    }

    @Override
    public void updateQuality() {
        sellIn--;
        if(sellIn <= 0)
            quality=0;
        else if(sellIn < 6)
            quality+=3;
        else if(sellIn < 11)
            quality+=2;
        else
            quality++;
    }
}
