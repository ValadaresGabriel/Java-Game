package com.hope.entity.stats;

import com.hope.entity.enemies.Enemies;

import java.awt.image.BufferedImage;

public class EnemyStats extends Stats {

    private boolean boss;

    private int maxLifeWidth;

    public EnemyStats(BufferedImage enemy) {
        if (Enemies.ZENO.equals(enemy))
            zeno();
    }

    private void zeno() {
        setBoss(false);
        setLife(50);
        setMaxLife(50);
        setMaxLifeWidth(10);
        setMana(100);
        setMaxMana(100);
        setMaxManaWidth(10);
        setStrength(10);
    }

    private void setBoss(boolean boss) {
        this.boss = boss;
    }

    private void setMaxLifeWidth(int maxLifeWidth) {
        this.maxLifeWidth = maxLifeWidth;
    }

    public boolean isBoss() {
        return boss;
    }

    public int getMaxLifeWidth() {
        return this.maxLifeWidth;
    }

}
