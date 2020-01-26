package com.zelda.entity.items;

import com.zelda.engine.Game;
import com.zelda.entity.Item;

import java.awt.image.BufferedImage;

public class LifePack extends Item {

    public LifePack(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    public double getLife() {
        return Game.getPlayer().getMaxLife() * 0.25;
    }

}
