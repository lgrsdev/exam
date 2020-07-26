package com.cyberark.items.entities;

import java.util.List;

public class FeaturedItem extends Item {

    private String feature;

    public FeaturedItem(long id, String name, int sellIn, int quality, String feature) {
        super(id, name, sellIn, quality);
        this.feature = feature;
    }

    public String getFeature() {
        return this.feature;
    }
}
