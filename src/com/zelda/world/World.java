package com.zelda.world;

import com.zelda.entity.Enemy;
import com.zelda.entity.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class World {

	private Tile[] tiles;

	private int[] pixels;

	private static int WIDTH;

	private static int HEIGHT;

	private List<Enemy> enemies = new ArrayList<>();
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));

			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();

			this.pixels = new int[WIDTH * HEIGHT];
			this.tiles = new Tile[WIDTH * HEIGHT];

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

				this.tiles[position] = new Tile(i * 16, j * 16, Tile.TILE_GRASS);

				switch (this.pixels[position]) {
					case 0xFFFF6A00 -> this.tiles[position] = new Tile(i * 16, j * 16, Tile.TILE_FLOOR);
					case 0xFF7F3300 -> this.tiles[position] = new Tile(i * 16, j * 16, Tile.TILE_WALL);
					case 0xFFFF0000 -> this.enemies.add(new Enemy(i * 16, j * 16, 16, 16, Entity.ENEMY));
				}
				
			}
		}
	}

	public void Render(Graphics graphics) {
		if (this.tiles.length > 0) {
			for (int i = 0; i < WIDTH; i++) {
				for (int j = 0; j < HEIGHT; j++) {
					Tile tile = this.tiles[i + (j * WIDTH)];
					tile.Render(graphics);
				}
			}
		}

		if (this.enemies.size() > 0)
			for (Enemy enemy : this.enemies) enemy.Render(graphics);
	}

}
