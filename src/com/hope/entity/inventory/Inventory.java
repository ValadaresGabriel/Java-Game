package com.hope.entity.inventory;

import com.hope.entity.Item;

import java.util.List;

public class Inventory {

    private int gold;

    private List<Item> items;

    public Inventory(int gold, List<Item> items) {
        this.gold = gold;
        this.items = items;
    }

    public int getGold() {
        return this.gold;
    }

    public List<Item> getItems() {
        return this.items;
    }

}
