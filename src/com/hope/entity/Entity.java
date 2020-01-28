package com.hope.entity;

import com.hope.engine.Game;
import com.hope.world.World;

import java.awt.image.BufferedImage;

public class Entity {

    public static final BufferedImage SWORD = Game.itemSpritesheet.getSprite(0, 0, World.TILE_SIZE, World.TILE_SIZE);

    public static final BufferedImage LIFE_PACK = Game.itemSpritesheet.getSprite(World.TILE_SIZE, 0, World.TILE_SIZE, World.TILE_SIZE);

}
