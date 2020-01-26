package com.zelda.entity.items;

import com.zelda.entity.Item;

import java.awt.image.BufferedImage;

public class Sword extends Item {

    private double damage;

    public Sword(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    public double getDamage() {
        return this.damage;
    }

}
