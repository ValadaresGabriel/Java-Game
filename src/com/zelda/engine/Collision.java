package com.zelda.engine;

import com.zelda.entity.Enemy;
import com.zelda.tile.Solid;
import com.zelda.world.World;

import java.awt.*;

public class Collision {

    private static boolean simulate(int length) {
        return length >= Game.world.getTiles().length || length <= 0;
    }

    public static boolean isFree(int x, int y) {
        int firstTestX = x / World.TILE_SIZE;
        int firstTestY = y / World.TILE_SIZE;

        int secondTestX = (x + World.TILE_SIZE - 1) / World.TILE_SIZE;
        int secondTestY = y / World.TILE_SIZE;

        int thirdTestX = x / World.TILE_SIZE;
        int thirdTestY = (y + World.TILE_SIZE - 1) / World.TILE_SIZE;

        int fourthTestX = (x + World.TILE_SIZE - 1) / World.TILE_SIZE;
        int fourthTestY = (y + World.TILE_SIZE - 1) / World.TILE_SIZE;

        if (simulate(firstTestX + (firstTestY * World.WIDTH)) ||
                simulate(secondTestX + (secondTestY * World.WIDTH)) ||
                simulate(thirdTestX + (thirdTestY * World.WIDTH)) ||
                simulate(fourthTestX + (fourthTestY * World.WIDTH)))
            return false;

        return !(Game.world.getTiles()[firstTestX + (firstTestY * World.WIDTH)] instanceof Solid ||
                Game.world.getTiles()[secondTestX + (secondTestY * World.WIDTH)] instanceof Solid ||
                Game.world.getTiles()[thirdTestX + (thirdTestY * World.WIDTH)] instanceof Solid ||
                Game.world.getTiles()[fourthTestX + (fourthTestY * World.WIDTH)] instanceof Solid);
    }

    public static boolean isEnemyFree(Enemy cEnemy, int x, int y) {
        Rectangle currentEnemy = new Rectangle(x, y, World.TILE_SIZE, World.TILE_SIZE);

        for (Enemy enemy : Game.world.getEnemies()) {

            if (enemy == cEnemy)
                continue;

            Rectangle targetEnemy = new Rectangle(enemy.getX(), enemy.getY(), World.TILE_SIZE, World.TILE_SIZE - 3);

            if (currentEnemy.intersects(targetEnemy))
                return false;
        }

        return true;
    }

}
