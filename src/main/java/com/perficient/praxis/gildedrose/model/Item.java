package com.perficient.praxis.gildedrose.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;


@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public String name;

    public int sellIn;

    public int quality;

    public Type type;

    public Item() {
    }

    public Item(int id, String name, int sellIn, int quality, Type type) {
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

    public enum Type {
        AGED,
        NORMAL,
        LEGENDARY,
        TICKETS
    }

    @Override
    public String toString() {
        return this.id+ ", " +this.name + ", " + this.sellIn + ", " + this.quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return sellIn == item.sellIn && quality == item.quality && Objects.equals(name, item.name) && type == item.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellIn, quality, type);
    }
}