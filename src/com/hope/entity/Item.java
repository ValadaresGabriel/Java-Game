package com.hope.entity;

import com.hope.engine.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {

    protected int x;

    protected int y;

    protected int width;

    protected int height;

    private BufferedImage sprite;

    public Item(int x, int y, int width, int height, BufferedImage sprite) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setSprite(sprite);
    }

    public void Render(Graphics graphics) {
        graphics.drawImage(getSprite(), getX() - Camera.x, getY() - Camera.y, null);
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    private void setWidth(int width) {
        this.width = width;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    private void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public BufferedImage getSprite() {
        return this.sprite;
    }

}
