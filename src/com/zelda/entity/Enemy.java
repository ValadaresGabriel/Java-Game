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
        int firstCollision = getX() + MOVEMENT_SPEED;
        int secondCollision = getX() - MOVEMENT_SPEED;
        int thirdCollision = getY() + MOVEMENT_SPEED;
        int fourthCollision = getY() - MOVEMENT_SPEED;

        if (getX() < Game.getPlayer().getX() &&Collision.isFree(firstCollision, getY()) &&
                Collision.isEnemyFree(this, firstCollision, getY()))
            this.x += MOVEMENT_SPEED;
        else if (getX() > Game.getPlayer().getX() && Collision.isFree(secondCollision, getY()) &&
                Collision.isEnemyFree(this, secondCollision, getY()))
            this.x -= MOVEMENT_SPEED;

        if (getY() < Game.getPlayer().getY() && Collision.isFree(getX(), thirdCollision) &&
                Collision.isEnemyFree(this, getX(), thirdCollision))
            this.y += MOVEMENT_SPEED;
        else if (getY() > Game.getPlayer().getY() && Collision.isFree(getX(), fourthCollision) &&
                Collision.isEnemyFree(this, getX(), fourthCollision))
            this.y -= MOVEMENT_SPEED;
    }

}
