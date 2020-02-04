package com.hope.panel;

import com.hope.engine.Game;
import com.hope.entity.stats.PlayerStats;

import java.awt.*;

public class Ui {

    private static final int maxLifeWidth = 50;

    public void Render(Graphics graphics) {
        PlayerStats playerStats = Game.getPlayer().getPlayerStats();

        //Life
        graphics.setColor(Color.black);
        graphics.fillRect(4, 4, maxLifeWidth, 5);

        graphics.setColor(Color.green);
        graphics.fillRect(4, 4, (int)(playerStats.getLife() / playerStats.getMaxLife() * maxLifeWidth), 5);

        //Mana
        graphics.setColor(Color.black);
        graphics.fillRect(4, 9, playerStats.getMaxManaWidth(), 5);

        graphics.setColor(Color.blue);
        graphics.fillRect(4, 9, (int)(playerStats.getMana() / playerStats.getMaxMana() * playerStats.getMaxManaWidth()), 5);
    }

}
