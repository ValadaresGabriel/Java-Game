package com.zelda.world;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {

	private Tile[] tiles;

	private int pixels[];

	private static int WIDTH;

	private static int HEIGHT;
	
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

				switch (this.pixels[position]) {
					case 0xFF007C10:
						this.tiles[position] = new Tile(i * 16, j * 16, Tile.TILE_GRASS);
						break;
					case 0xFFFF6A00:
						this.tiles[position] = new Tile(i * 16, j * 16, Tile.TILE_FLOOR);
						break;
					case 0xFF7F3300:
						this.tiles[position] = new Tile(i * 16, j * 16, Tile.TILE_WALL);
						break;
					default:
						this.tiles[position] = new Tile(i * 16, j * 16, Tile.TILE_GRASS);
				}
				
			}
		}
	}

	public void Render(Graphics graphics) {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				Tile tile = this.tiles[i + (j * WIDTH)];
				tile.Render(graphics);
			}
		}
	}

}
