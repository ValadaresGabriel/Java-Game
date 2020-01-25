package com.zelda.entity;

import com.zelda.engine.Game;
import com.zelda.world.World;

import java.awt.image.BufferedImage;

public class EnemyEntity extends Entity {

    public static final BufferedImage ENEMY = Game.enemySpritesheet.getSprite(0, 0, World.TILE_SIZE, World.TILE_SIZE);

}
