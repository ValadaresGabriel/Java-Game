package com.zelda.entity;

import com.zelda.engine.Game;

import java.awt.image.BufferedImage;

public class Entity {

    public static final BufferedImage LIFE_PACK = Game.spritesheet.getSprite(16 * 4, 0, 16, 16);

    public static final BufferedImage SWORD = Game.spritesheet.getSprite(16 * 5, 0, 16, 16);

    public static final BufferedImage ENEMY = Game.spritesheet.getSprite(16 * 4, 16, 16, 16);

}
