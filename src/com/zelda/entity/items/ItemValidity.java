package com.zelda.entity.items;

import com.zelda.engine.Game;
import com.zelda.entity.Item;
import com.zelda.entity.Player;

public class ItemValidity {

    public static void validatingItem(Item item) {
        if (item instanceof LifePack)
            lifePack((LifePack) item);
    }

    private static void lifePack(LifePack lifePack) {
        Player player = Game.getPlayer();
        double heal = lifePack.getLife();
        double maxLife = player.getMaxLife();

        player.addLife(heal);

        if (player.getLife() > maxLife)
            player.setLife(maxLife);

        Game.world.getItems().remove(lifePack);
    }

}
