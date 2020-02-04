package com.hope.entity.items;

import com.hope.engine.Game;
import com.hope.entity.Entity;
import com.hope.entity.Item;
import com.hope.entity.Player;
import com.hope.world.World;

public class ItemValidity {

    public static void validatingItem(Item item) {
        switch (item.type) {
            case 0 -> lifePack(item);
            case 1 -> weapon(item);
        }
    }

    private static void validatingWeapon(Item item) {
        int i = 0;

        if (item.getSprite().equals(Entity.SWORD)) {
            for (i = 0; i < 2; i++)
                Game.getPlayer().weapons.add(Game.itemSpritesheet.getSprite(i * World.TILE_SIZE, 0, World.TILE_SIZE, World.TILE_SIZE));
        }
    }

    private static void lifePack(Item item) {
        Player player = Game.getPlayer();
        double heal = item.getHeal(0.25);
        double maxLife = player.getPlayerStats().getMaxLife();

        player.addLife(heal);

        if (player.getPlayerStats().getLife() > maxLife)
            player.getPlayerStats().restoreLife();

        Game.world.getItems().remove(item);
    }

    private static void weapon(Item item) {
        Player player = Game.getPlayer();

        player.weapon = item;

        validatingWeapon(item);

        Game.world.getItems().remove(item);
    }

}
