package com.zelda.world;

import com.zelda.engine.Camera;
import com.zelda.engine.Game;
import com.zelda.entity.Enemy;
import com.zelda.entity.Entity;
import com.zelda.tile.Tile;
import com.zelda.tile.Solid;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class World {

	public static Tile[] tiles;

	private int[] pixels;

	public static int WIDTH;

	public static int HEIGHT;

	public static final int TILE_SIZE = 16;

	private List<Enemy> enemies = new ArrayList<>();
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));

			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();

			this.pixels = new int[WIDTH * HEIGHT];
			tiles = new Tile[WIDTH * HEIGHT];

			map.getRGB(0, 0, WIDTH, HEIGHT, this.pixels, 0, WIDTH);

			getMapProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void getMapProperties() {
		int position;

		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {

				position = i + (j * WIDTH);

				tiles[position] = new Tile(i * TILE_SIZE, j * TILE_SIZE, Tile.TILE_GRASS);

				switch (this.pixels[position]) {
					case 0xFFFF6A00 -> tiles[position] = new Tile(i * TILE_SIZE, j * TILE_SIZE, Tile.TILE_FLOOR);
					case 0xFF7F3300 -> tiles[position] = new Solid(i * TILE_SIZE, j * TILE_SIZE, Tile.TILE_WALL);
					case 0xFFFF0000 -> this.enemies.add(new Enemy(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, Entity.ENEMY));
				}
				
			}
		}
	}

	public void Render(Graphics graphics) {
		if (tiles.length > 0) {

			int xStart = Camera.x >> 4;
			int yStart = Camera.y >> 4;

			int xFinal = xStart + (Game.WIDTH >> 4);
			int yFinal = yStart + (Game.HEIGHT >> 4);

			for (int i = xStart; i <= xFinal + 2; i++) {
				for (int j = yStart; j <= yFinal + 2; j++) {

					if (i < 0 || j < 0 || i >= WIDTH || j >= HEIGHT)
						continue;

					Tile tile = tiles[i + (j * WIDTH)];
					tile.Render(graphics);
				}
			}
		}

		if (this.enemies.size() > 0)
			for (Enemy enemy : this.enemies) enemy.Render(graphics);
	}

}
