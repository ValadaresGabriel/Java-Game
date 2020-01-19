package com.zelda.world;

import com.zelda.engine.Camera;
import com.zelda.engine.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    protected static final BufferedImage TILE_GRASS = Game.spritesheet.getSprite(0, 0, 16, 16);

    protected static final BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(16, 0, 16, 16);

    protected static final BufferedImage TILE_WALL = Game.spritesheet.getSprite(0, 16, 16, 16);

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
