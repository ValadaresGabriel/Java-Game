package com.zelda.entity.stats;

import com.zelda.entity.enemies.Enemies;

import java.awt.image.BufferedImage;

public class EnemyStats {

    private int life;

    private int mana;

    private int strength;

    public EnemyStats(BufferedImage enemy) {
        if (Enemies.ZENO.equals(enemy))
            zeno();
    }

    private void zeno() {
        setLife(50);
        setMana(100);
        setStrength(10);
    }

    private void setLife(int life) {
        this.life = life;
    }

    private void setMana(int mana) {
        this.mana = mana;
    }

    private void setStrength(int strength) {
        this.strength = strength;
    }

    public int getLife() {
        return this.life;
    }

    public int getMana() {
        return this.mana;
    }

    public int getStrength() {
        return this.strength;
    }

}
