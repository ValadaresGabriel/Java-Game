package com.zelda.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Move implements KeyListener {

    private Game game;

    public Move(Game game) {
        this.game = game;
        game.addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT)
            this.game.getPlayer().setRight(true);
        else if (event.getKeyCode() == KeyEvent.VK_LEFT)
            this.game.getPlayer().setLeft(true);

        if (event.getKeyCode() == KeyEvent.VK_UP)
            this.game.getPlayer().setUp(true);
        else if (event.getKeyCode() == KeyEvent.VK_DOWN)
            this.game.getPlayer().setDown(true);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        this.game.getPlayer().isMoving = false;
        if (event.getKeyCode() == KeyEvent.VK_RIGHT)
            this.game.getPlayer().setRight(false);
        else if (event.getKeyCode() == KeyEvent.VK_LEFT)
            this.game.getPlayer().setLeft(false);

        if (event.getKeyCode() == KeyEvent.VK_UP)
            this.game.getPlayer().setUp(false);
        else if (event.getKeyCode() == KeyEvent.VK_DOWN)
            this.game.getPlayer().setDown(false);
    }

    @Override
    public void keyTyped(KeyEvent event) {
        // TODO Auto-generated method stub

    }
}
