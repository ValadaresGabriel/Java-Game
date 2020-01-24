package com.zelda.entity;

import com.zelda.engine.Game;
import com.zelda.world.World;

import java.awt.image.BufferedImage;

public class Entity {

    public static final BufferedImage LIFE_PACK = Game.spritesheet.getSprite(World.TILE_SIZE * 4, 0, World.TILE_SIZE, World.TILE_SIZE);

    public static final BufferedImage SWORD = Game.spritesheet.getSprite(World.TILE_SIZE * 5, 0, World.TILE_SIZE, World.TILE_SIZE);

    public static final BufferedImage ENEMY = Game.spritesheet.getSprite(World.TILE_SIZE * 4, 16, World.TILE_SIZE, World.TILE_SIZE);

}
