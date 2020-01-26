package com.zelda.panel;

import com.zelda.engine.Game;

import java.awt.*;

public class Ui {

    private static final int maxLifeWidth = 50;

    public void Render(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(4, 4, maxLifeWidth, 8);

        graphics.setColor(Color.green);
        graphics.fillRect(4, 4, (int)((Game.getPlayer().life / Game.getPlayer().maxLife) * maxLifeWidth), 8);
    }

}
