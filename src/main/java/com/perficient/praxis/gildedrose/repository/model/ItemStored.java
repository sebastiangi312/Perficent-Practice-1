package com.perficient.praxis.gildedrose.repository.model;

import com.perficient.praxis.gildedrose.type.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "items")
public class ItemStored {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public String name;
    public int sellIn;
    public int quality;
    public Type type;

    public ItemStored(int id, String name, int sellIn, int quality, Type type) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        ItemStored that = (ItemStored) o;
        return id == that.id && sellIn == that.sellIn && quality == that.quality && Objects.equals(name, that.name) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sellIn, quality, type);
    }
}
