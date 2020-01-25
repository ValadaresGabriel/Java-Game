package com.zelda.tile;

import com.zelda.engine.Camera;
import com.zelda.engine.Game;
import com.zelda.spritesheet.WorldSpritesheet;
import com.zelda.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static final BufferedImage TILE_GRASS = Game.worldSpritesheet.getSprite(0, 0, World.TILE_SIZE, World.TILE_SIZE);

    public static final BufferedImage TILE_FLOOR = Game.worldSpritesheet.getSprite(16, 0, World.TILE_SIZE, World.TILE_SIZE);

    public static final BufferedImage TILE_WALL = Game.worldSpritesheet.getSprite(0, 16, World.TILE_SIZE, World.TILE_SIZE);

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
