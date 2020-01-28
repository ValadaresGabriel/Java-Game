package com.hope.entity.items;

import com.hope.engine.Game;
import com.hope.entity.Item;

import java.awt.image.BufferedImage;

public class LifePack extends Item {

    public LifePack(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    public double getLife() {
        return Game.getPlayer().getMaxLife() * 0.25;
    }

}
