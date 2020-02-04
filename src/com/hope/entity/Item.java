package com.hope.entity;

import com.hope.engine.Camera;
import com.hope.engine.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item extends Entity {

    public int id;

    protected int x;

    protected int y;

    protected int width;

    protected int height;

    public int type;

    private BufferedImage sprite;

    public Item(int id, int x, int y, int width, int height, int type, BufferedImage sprite) {
        setId(id);
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setType(type);
        setSprite(sprite);
    }

    public void Render(Graphics graphics) {
        graphics.drawImage(getSprite(), getX() - Camera.x, getY() - Camera.y, null);
    }

    public double getHeal(double heal) {
        return Game.getPlayer().getPlayerStats().getMaxLife() * heal;
    }

    private void setId(int id) {
        this.id = id;
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

    private void setType(int type) {
        this.type = type;
    }

    private void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public int getId() {
        return this.id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getType() {
        return this.type;
    }

    public BufferedImage getSprite() {
        return this.sprite;
    }

}
