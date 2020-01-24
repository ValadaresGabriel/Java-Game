package com.zelda.engine;

import com.zelda.tile.Solid;
import com.zelda.world.World;

public class Collision {

    private static boolean simulate(int length) {
        return length >= Game.world.getTiles().length;
    }

    private static boolean simulatePosition(int x, int y) {
        return x == 0 || x == World.WIDTH || y < 0 || y >= World.HEIGHT;
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

        if (simulatePosition(x / World.TILE_SIZE, y / World.TILE_SIZE))
            return false;

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

}
