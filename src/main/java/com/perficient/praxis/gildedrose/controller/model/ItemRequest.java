package com.perficient.praxis.gildedrose.controller.model;

import com.perficient.praxis.gildedrose.type.Type;

import java.util.Objects;


public class ItemRequest {

    private int id;

    public String name;

    public int sellIn;

    public int quality;

    public Type type;

    public ItemRequest() {
    }

    public ItemRequest(int id, String name, int sellIn, int quality, Type type) {
        this.id = id;
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return this.id+ ", " +this.name + ", " + this.sellIn + ", " + this.quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemRequest)) return false;
        ItemRequest item = (ItemRequest) o;
        return sellIn == item.sellIn && quality == item.quality && Objects.equals(name, item.name) && type == item.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellIn, quality, type);
    }
}