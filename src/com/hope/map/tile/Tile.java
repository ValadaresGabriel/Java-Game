package com.hope.map.tile;

import com.hope.engine.Camera;
import com.hope.engine.Game;
import com.hope.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static final BufferedImage TILE_GRASS = Game.worldSpritesheet.getSprite(0, 0, World.TILE_SIZE, World.TILE_SIZE);

    public static final BufferedImage TILE_FLOOR = Game.worldSpritesheet.getSprite(World.TILE_SIZE, 0, World.TILE_SIZE, World.TILE_SIZE);

    public static final BufferedImage TILE_WALL = Game.worldSpritesheet.getSprite(World.TILE_SIZE * 2, 0, World.TILE_SIZE, World.TILE_SIZE);

    private BufferedImage sprite;

    private int x;

    private int y;

    public Tile(int x, int y, BufferedImage sprite) {
        setX(x);
        setY(y);
        setSprite(sprite);
    }

    public void Render(Graphics graphics) {
        graphics.drawImage(this.sprite, getX() - Camera.x, getY() - Camera.y, null);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    private void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

}
