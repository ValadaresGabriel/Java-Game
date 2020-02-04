package com.hope.engine;

import com.hope.entity.Enemy;
import com.hope.entity.Item;
import com.hope.entity.items.ItemValidity;
import com.hope.map.tile.Solid;
import com.hope.map.tile.Tile;
import com.hope.world.World;

import java.awt.*;

public class Collision {

    private static boolean simulate(int length) {
        return length >= Game.world.getTiles().length || length < 0;
    }

    private static boolean simulateX(int length) {
        return length > World.WIDTH - 1 || length < 0;
    }

    private static boolean simulateY(int length) {
        return length > World.HEIGHT - 1;
    }

    public static boolean isFree(int x, int y) {
        Tile[] tiles = Game.world.getTiles();

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

        if (simulateX(firstTestX) || simulateX(secondTestX) || simulateX(thirdTestX) || simulateX(fourthTestX))
            return false;

        if (simulateY(firstTestY) || simulateY(secondTestY) || simulateY(thirdTestY) || simulateY(fourthTestY))
            return false;

        return !(tiles[firstTestX + (firstTestY * World.WIDTH)] instanceof Solid ||
                tiles[secondTestX + (secondTestY * World.WIDTH)] instanceof Solid ||
                tiles[thirdTestX + (thirdTestY * World.WIDTH)] instanceof Solid ||
                tiles[fourthTestX + (fourthTestY * World.WIDTH)] instanceof Solid);
    }

    public static boolean isEnemyCollidingPlayer(Enemy cEnemy) {
        Rectangle enemy = new Rectangle(cEnemy.getX(), cEnemy.getY(), World.TILE_SIZE - 3, World.TILE_SIZE);
        Rectangle player = new Rectangle(Game.getPlayer().getX(), Game.getPlayer().getY(), World.TILE_SIZE - 3, World.TILE_SIZE);

        return enemy.intersects(player);
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

    public static boolean isCollidingItems(Item cItem) {
        Rectangle item = new Rectangle(cItem.getX(), cItem.getY(), World.TILE_SIZE - 3, World.TILE_SIZE);
        Rectangle player = new Rectangle(Game.getPlayer().getX(), Game.getPlayer().getY(), World.TILE_SIZE - 3, World.TILE_SIZE);

        return item.intersects(player);
    }

    public static void gettingItems() {
        for (Item item : Game.world.getItems()) {
            if (item != null) {
                if (isCollidingItems(item)) {
                    ItemValidity.validatingItem(item);
                    return;
                }
            }
        }
    }

}
