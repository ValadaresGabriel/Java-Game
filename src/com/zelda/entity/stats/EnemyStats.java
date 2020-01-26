package com.zelda.entity.stats;

import com.zelda.entity.enemies.Enemies;

import java.awt.image.BufferedImage;

public class EnemyStats {

    private double life;

    private double mana;

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

    private void setLife(double life) {
        this.life = life;
    }

    private void setMana(double mana) {
        this.mana = mana;
    }

    private void setStrength(int strength) {
        this.strength = strength;
    }

    public double getLife() {
        return this.life;
    }

    public double getMana() {
        return this.mana;
    }

    public int getStrength() {
        return this.strength;
    }

}
