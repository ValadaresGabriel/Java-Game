package com.zelda.entity;

import com.zelda.engine.Collision;
import com.zelda.engine.Game;

import java.awt.image.BufferedImage;

public class Enemy extends Avatar {

    private static final int MOVEMENT_SPEED = 1;

    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    @Override
    public void Update() {
        if (getX() < Game.getPlayer().getX() && Collision.isFree(getX() + MOVEMENT_SPEED, getY()))
            this.x += MOVEMENT_SPEED;
        else if (getX() > Game.getPlayer().getX() && Collision.isFree(getX() - MOVEMENT_SPEED, getY()))
            this.x -= MOVEMENT_SPEED;

        if (getY() < Game.getPlayer().getY() && Collision.isFree(getX(), getY() + MOVEMENT_SPEED))
            this.y += MOVEMENT_SPEED;
        else if (getY() > Game.getPlayer().getY() && Collision.isFree(getX(), getY() - MOVEMENT_SPEED))
            this.y -= MOVEMENT_SPEED;
    }

}
