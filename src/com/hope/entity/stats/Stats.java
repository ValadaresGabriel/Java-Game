package com.hope.entity.stats;

public class Stats {

    protected double life;

    protected double maxLife;

    protected double mana;

    protected double maxMana;

    protected int maxManaWidth;

    protected int level;

    protected int strength;

    public Stats() {
        //code
    }

    public void setLife(double life) {
        this.life = life;
    }

    public void restoreLife() {
        this.life = this.maxLife;
    }

    protected void setMaxLife(double maxLife) {
        this.maxLife = maxLife;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    protected void setMaxMana(double maxMana) {
        this.maxMana = maxMana;
    }

    protected void setMaxManaWidth(int maxManaWidth) {
        this.maxManaWidth = maxManaWidth;
    }

    protected void setLevel(int level) {
        this.level = level;
    }

    protected void setStrength(int strength) {
        this.strength = strength;
    }

    public double getLife() {
        return this.life;
    }

    public double getMaxLife() {
        return this.maxLife;
    }

    public double getMana() {
        return this.mana;
    }

    public double getMaxMana() {
        return this.maxMana;
    }

    public int getMaxManaWidth() {
        return this.maxManaWidth;
    }

    public int getLevel() {
        return this.level;
    }

    public int getStrength() {
        return this.strength;
    }

}
