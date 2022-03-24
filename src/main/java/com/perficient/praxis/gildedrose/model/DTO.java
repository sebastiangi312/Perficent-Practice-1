package com.perficient.praxis.gildedrose.model;

import java.util.LinkedList;
import java.util.List;

public class DTO {

    public List<Item> items;

    public DTO() {
        this.items = new LinkedList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
