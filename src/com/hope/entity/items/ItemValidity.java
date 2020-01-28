package com.hope.entity.items;

import com.hope.engine.Game;
import com.hope.entity.Item;
import com.hope.entity.Player;

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
