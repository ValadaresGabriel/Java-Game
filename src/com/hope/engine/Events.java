package com.hope.engine;

import com.hope.panel.EscMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Events implements KeyListener, MouseListener {

    public Events(Game game) {
        game.addKeyListener(this);
        game.addMouseListener(this);
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

        if (event.getKeyCode() == KeyEvent.VK_ESCAPE && Game.gameState == 0)
            Game.gameState = 2;
        else
            Game.gameState = 0;
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

    @Override
    public void mouseClicked(MouseEvent event) {
        if (Game.gameState == 2) {
            if (EscMenu.resume.contains(event.getPoint()))
                Game.gameState = 0;
            else if (EscMenu.save.contains(event.getPoint()))
                System.out.println("save");
            else if (EscMenu.exit.contains(event.getPoint()))
                System.exit(1);
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseReleased(MouseEvent event) {

    }

    @Override
    public void mouseEntered(MouseEvent event) {

    }

    @Override
    public void mouseExited(MouseEvent event) {

    }
}
