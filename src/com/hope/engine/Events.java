package com.hope.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Events implements KeyListener {

    public Events(Game game) {
        game.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT)
            Game.getPlayer().setRight(true);
        else if (event.getKeyCode() == KeyEvent.VK_LEFT)
            Game.getPlayer().setLeft(true);

        if (event.getKeyCode() == KeyEvent.VK_UP)
            Game.getPlayer().setUp(true);
        else if (event.getKeyCode() == KeyEvent.VK_DOWN)
            Game.getPlayer().setDown(true);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        Game.getPlayer().setMoving(false);
        if (event.getKeyCode() == KeyEvent.VK_RIGHT)
            Game.getPlayer().setRight(false);
        else if (event.getKeyCode() == KeyEvent.VK_LEFT)
            Game.getPlayer().setLeft(false);

        if (event.getKeyCode() == KeyEvent.VK_UP)
            Game.getPlayer().setUp(false);
        else if (event.getKeyCode() == KeyEvent.VK_DOWN)
            Game.getPlayer().setDown(false);
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // TODO Auto-generated method stub

    }
}
