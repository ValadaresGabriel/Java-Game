package com.hope.entity.enemies;

import com.hope.engine.Game;
import com.hope.world.World;

import java.awt.image.BufferedImage;

public class Enemies {

    public static final BufferedImage ZENO = Game.enemySpritesheet.getSprite(0, 0, World.TILE_SIZE, World.TILE_SIZE);

    public static final BufferedImage ZENO2 = Game.enemySpritesheet.getSprite(0, 0, World.TILE_SIZE, World.TILE_SIZE);

    public static BufferedImage getEnemyByMap(String mapSource) {
        BufferedImage enemy = null;

        switch (mapSource) {
            case "/Map_1.png" -> enemy = ZENO;
            case "/Map_2.pnh" -> enemy = ZENO2;
        }

        return enemy;
    }

}
