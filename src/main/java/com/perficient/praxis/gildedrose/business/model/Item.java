package com.perficient.praxis.gildedrose.business.model;

import com.perficient.praxis.gildedrose.type.Type;

import java.util.Objects;

public abstract class Item {

    private int id;
    public String name;
    public int sellIn;
    public int quality;
    public Type type;

    public Item(int id, String name, int sellIn, int quality, Type type) {
        this.id = id;
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.type = type;
    }

    public abstract void updateQuality();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Item item = (Item) o;
        return id == item.id && sellIn == item.sellIn && quality == item.quality && Objects.equals(name, item.name) && type == item.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sellIn, quality, type);
    }
}
