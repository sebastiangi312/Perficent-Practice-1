package com.perficient.praxis.gildedrose.business.model;

import com.perficient.praxis.gildedrose.type.Type;

public class TicketItem extends Item{

    public TicketItem(int id, String name, int sellIn, int quality, Type type) {
        super(id, name, sellIn, quality, type);
    }

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
