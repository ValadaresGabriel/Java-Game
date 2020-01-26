package com.zelda.entity;

import com.zelda.engine.Camera;
import com.zelda.engine.Collision;
import com.zelda.engine.Game;
import com.zelda.entity.stats.EnemyStats;
import com.zelda.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Avatar {

    private static final int MOVEMENT_SPEED = 1;

    private List<BufferedImage> rightEnemy;

    private List<BufferedImage> leftEnemy;

    private BufferedImage lastDirection;

    private boolean right;

    private boolean left;

    private int frame = 0;

    private int delay = 0;

    private int animationLength;

    private EnemyStats enemyStats;

    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

        this.enemyStats = new EnemyStats(sprite);

        this.rightEnemy = new ArrayList<>();
        this.leftEnemy = new ArrayList<>();

        for (int i = 0; i < 2; i++)
            this.rightEnemy.add(Game.enemySpritesheet.getSprite((i * World.TILE_SIZE), 0, World.TILE_SIZE, World.TILE_SIZE));

        for (int i = 0; i < 2; i++)
            this.leftEnemy.add(Game.enemySpritesheet.getSprite(World.TILE_SIZE * 2 + (i * World.TILE_SIZE), 0, World.TILE_SIZE, World.TILE_SIZE));

        this.animationLength = this.rightEnemy.size();
    }

    @Override
    public void Update() {
        int firstCollision = getX() + MOVEMENT_SPEED;
        int secondCollision = getX() - MOVEMENT_SPEED;
        int thirdCollision = getY() + MOVEMENT_SPEED;
        int fourthCollision = getY() - MOVEMENT_SPEED;

        if (!Collision.isEnemyCollidingPlayer(this)) {
            if (getX() < Game.getPlayer().getX() && Collision.isFree(firstCollision, getY()) &&
                    Collision.isEnemyFree(this, firstCollision, getY())) {
                this.x += MOVEMENT_SPEED;
                this.right = true;
                this.left = false;
                this.lastDirection = this.rightEnemy.get(0);
            } else if (getX() > Game.getPlayer().getX() && Collision.isFree(secondCollision, getY()) &&
                    Collision.isEnemyFree(this, secondCollision, getY())) {
                this.x -= MOVEMENT_SPEED;
                this.right = false;
                this.left = true;
                this.lastDirection = this.leftEnemy.get(0);
            }

            if (getY() < Game.getPlayer().getY() && Collision.isFree(getX(), thirdCollision) &&
                    Collision.isEnemyFree(this, getX(), thirdCollision))
                this.y += MOVEMENT_SPEED;
            else if (getY() > Game.getPlayer().getY() && Collision.isFree(getX(), fourthCollision) &&
                    Collision.isEnemyFree(this, getX(), fourthCollision))
                this.y -= MOVEMENT_SPEED;
        } else {
            Game.getPlayer().reduceLife(getEnemyStats().getStrength());
        }

        this.delay++;

        if (this.delay == 7) {
            this.delay = 0;
            this.frame++;
        }

        if (this.frame == this.animationLength)
            this.frame = 0;
    }

    @Override
    public void Render(Graphics graphics) {
        if (this.right)
            graphics.drawImage(this.rightEnemy.get(this.frame), getX() - Camera.x, getY() - Camera.y, null);
        else if (this.left)
            graphics.drawImage(this.leftEnemy.get(this.frame), getX() - Camera.x, getY() - Camera.y, null);
        else
            graphics.drawImage(this.lastDirection, getX() - Camera.x, getY() - Camera.y, null);
    }

    private EnemyStats getEnemyStats() {
        return this.enemyStats;
    }

}
