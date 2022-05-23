package com.perficient.praxis.gildedrose.factory;

import com.perficient.praxis.gildedrose.model.Item;
import com.perficient.praxis.gildedrose.model.type.AgedItem;
import com.perficient.praxis.gildedrose.model.type.LegendaryItem;
import com.perficient.praxis.gildedrose.model.type.NormalItem;
import com.perficient.praxis.gildedrose.model.type.TicketItem;
import org.springframework.stereotype.Service;

@Service
public class ItemFactory {

    public Item createItem(int id, String name, int sellIn, int quality, Type type) {
        if(type == Type.AGED)
            return new AgedItem(id, name, sellIn, quality, type);
        if(type == Type.LEGENDARY)
            return new LegendaryItem(id, name, sellIn, quality, type);
        if(type == Type.TICKETS)
                return new TicketItem(id, name, sellIn, quality, type);

        return new NormalItem(id, name, sellIn, quality, Type.NORMAL);
    }

    public enum Type {
        AGED,
        NORMAL,
        LEGENDARY,
        TICKETS
    }

}
