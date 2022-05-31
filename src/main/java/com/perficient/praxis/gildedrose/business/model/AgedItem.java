package com.perficient.praxis.gildedrose.business.model;


import com.perficient.praxis.gildedrose.type.Type;

public class AgedItem extends Item {

    public AgedItem(int id, String name, int sellIn, int quality, Type type) {
        super(id, name, sellIn, quality, type);
    }

    public void updateQuality(){
        if(quality < 50)
            quality++;
        if(sellIn < 0 && quality < 50)
            quality++;
        sellIn--;
    }
}
