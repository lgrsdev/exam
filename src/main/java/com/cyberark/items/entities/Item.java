package com.cyberark.items.entities;

import java.io.Serializable;

public class Item implements Serializable {

    private static final long serialVersionUID = 5630264440931055927L;

    private long id;
    private String name;
    private int sellIn;
    private int quality;

    public Item(long id, String name, int sellIn, int quality) {
        this.id = id;
        this.setName(name);
        this.setSellIn(sellIn);
        this.setQuality(quality);
    }

    public Item(){}

    /* Generated getter and setter code */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sellIn=" + sellIn +
                ", quality=" + quality +
                '}';
    }
}
