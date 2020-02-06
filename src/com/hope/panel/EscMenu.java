package com.hope.panel;

import com.hope.engine.Game;

import java.awt.*;

public class EscMenu {

    private static final int width = Game.WIDTH * Game.SCALE;

    private static final int height = Game.HEIGHT * Game.SCALE;

    private String[] options = {"Resume", "Save", "Exit"};

    public final static Rectangle resume = new Rectangle((width - 150) / 2, (height - 30) / 2, 150, 30);

    public final static Rectangle save = new Rectangle((width - 150) / 2, (height - 30) / 2 + 40, 150, 30);;

    public final static Rectangle exit = new Rectangle((width - 150) / 2, (height - 30) / 2 + 80, 150, 30);;

    private int currentOption = 0;

    private final int maxOption = options.length - 1;

    private void createButton(Graphics graphics, Color color, Rectangle rectangle) {
        graphics.setColor(color);
        graphics.fillRect((int)rectangle.getX(), (int)rectangle.getY(), (int)rectangle.getWidth(), (int)rectangle.getHeight());
    }

    private void createText(Graphics graphics, int index, int heightModifier) {
        Font font = new Font("Arial", Font.BOLD, 20);
        FontMetrics fontMetrics = graphics.getFontMetrics(font);

        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString(this.options[index], (width - fontMetrics.stringWidth(this.options[index])) / 2, ((height - fontMetrics.getHeight()) / 2) + fontMetrics.getAscent() + heightModifier);
    }

    private void createMenu(Graphics graphics) {
        Color color = new Color(175, 195, 208, 123);

        createButton(graphics, color, resume);
        createText(graphics, 0, 0);

        createButton(graphics, color, save);
        createText(graphics, 1, 40);

        createButton(graphics, color, exit);
        createText(graphics, 2, 80);
    }

    public void Update() {
        //
    }

    public void Render(Graphics graphics) {
        String name = "Pause";
        Font font = new Font("Arial", Font.BOLD, 30);
        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(new Color(0, 0, 0, 150));
        graphics2D.fillRect(0, 0, width, height);

        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString(name, (width - fontMetrics.stringWidth(name)) / 2, 40);

        createMenu(graphics);
    }

}
