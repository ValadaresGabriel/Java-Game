package com.hope.entity;

import com.hope.engine.Camera;
import com.hope.engine.Collision;
import com.hope.engine.Game;
import com.hope.entity.stats.EnemyStats;
import com.hope.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Avatar {

    private static final int MOVEMENT_SPEED = 1;

    private List<BufferedImage> rightEnemy;

    private List<BufferedImage> leftEnemy;

    private BufferedImage lastDirection;

    private int animationLength;

    private EnemyStats enemyStats;

    private final Player player = Game.getPlayer();

    public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);

        this.enemyStats = new EnemyStats(sprite);

        this.rightEnemy = new ArrayList<>();
        this.leftEnemy = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            this.rightEnemy.add(Game.enemySpritesheet.getSprite((i * World.TILE_SIZE), 0, World.TILE_SIZE, World.TILE_SIZE));
            this.leftEnemy.add(Game.enemySpritesheet.getSprite(World.TILE_SIZE * 2 + (i * World.TILE_SIZE), 0, World.TILE_SIZE, World.TILE_SIZE));
        }

        this.animationLength = this.rightEnemy.size();
    }

    @Override
    public void Update() {
        int firstCollision = getX() + MOVEMENT_SPEED;
        int secondCollision = getX() - MOVEMENT_SPEED;
        int thirdCollision = getY() + MOVEMENT_SPEED;
        int fourthCollision = getY() - MOVEMENT_SPEED;

        if (!Collision.isEnemyCollidingPlayer(this)) {
            if (getX() < getPlayer().getX() && Collision.isFree(firstCollision, getY()) &&
                    Collision.isEnemyFree(this, firstCollision, getY())) {
                this.x += MOVEMENT_SPEED;
                setRight(true);
                setLeft(false);
                setLastDirection(this.rightEnemy.get(0));
            } else if (getX() > getPlayer().getX() && Collision.isFree(secondCollision, getY()) &&
                    Collision.isEnemyFree(this, secondCollision, getY())) {
                this.x -= MOVEMENT_SPEED;
                setRight(false);
                setLeft(true);
                setLastDirection(this.leftEnemy.get(0));
            }

            if (getY() < getPlayer().getY() && Collision.isFree(getX(), thirdCollision) &&
                    Collision.isEnemyFree(this, getX(), thirdCollision))
                this.y += MOVEMENT_SPEED;
            else if (getY() > getPlayer().getY() && Collision.isFree(getX(), fourthCollision) &&
                    Collision.isEnemyFree(this, getX(), fourthCollision))
                this.y -= MOVEMENT_SPEED;
        } else {
            getPlayer().reduceLife(getEnemyStats().getStrength());
        }

        movingAnimation();
    }

    private void movingAnimation() {
        this.delay++;

        if (getDelay() == 7) {
            resetDelay();
            this.frame++;
        }

        if (getFrame() == getAnimationLength())
            resetFrame();
    }

    public int getAnimationLength() {
        return this.animationLength;
    }

    private void setLastDirection(BufferedImage lastDirection) {
        this.lastDirection = lastDirection;
    }

    private List<BufferedImage> getRightEnemy() {
        return this.rightEnemy;
    }

    private List<BufferedImage> getLeftEnemy() {
        return this.leftEnemy;
    }

    private BufferedImage getLastDirection() {
        return this.lastDirection;
    }

    private Player getPlayer() {
        return this.player;
    }

    private void getEnemyHp(Graphics graphics) {
        int maxLifeWidth = getEnemyStats().getMaxLifeWidth();
        int lifeWidth = (int) ((getEnemyStats().getLife() / getEnemyStats().getMaxLife()) * maxLifeWidth);

        int maxManaWidth = getEnemyStats().getMaxManaWidth();
        int manaWidth = (int) ((getEnemyStats().getMana() / getEnemyStats().getMaxMana()) * maxManaWidth);

        int lifeX;
        int lifeY;

        int manaX;
        int manaY;

        int height;

        if (!getEnemyStats().isBoss()) {
            lifeX = getX() - Camera.x + 3;
            lifeY = getY() - Camera.y + World.TILE_SIZE + 1;

            manaX = lifeX;
            manaY = lifeY + 1;

            height = 1;
        } else {
            lifeX = (Game.WIDTH - getEnemyStats().getMaxLifeWidth()) / 2;
            lifeY = 20;

            manaX = (Game.WIDTH - getEnemyStats().getMaxManaWidth()) / 2;
            manaY = lifeY + 5;

            height = 5;
        }

        graphics.setColor(Color.black);
        graphics.fillRect(lifeX, lifeY, maxLifeWidth, height);

        graphics.setColor(Color.green);
        graphics.fillRect(lifeX, lifeY, lifeWidth, height);

        graphics.setColor(Color.black);
        graphics.fillRect(manaX, manaY, maxManaWidth, height);

        graphics.setColor(Color.blue);
        graphics.fillRect(manaX, manaY, manaWidth, height);
    }

    @Override
    public void Render(Graphics graphics) {
        getEnemyHp(graphics);

        if (isRight())
            graphics.drawImage(getRightEnemy().get(getFrame()), getX() - Camera.x, getY() - Camera.y, null);
        else if (isLeft())
            graphics.drawImage(getLeftEnemy().get(getFrame()), getX() - Camera.x, getY() - Camera.y, null);
        else
            graphics.drawImage(getLastDirection(), getX() - Camera.x, getY() - Camera.y, null);
    }

    private EnemyStats getEnemyStats() {
        return this.enemyStats;
    }

}
