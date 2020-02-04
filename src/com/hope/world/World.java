package com.hope.world;

import com.hope.engine.Camera;
import com.hope.engine.Game;
import com.hope.entity.Enemy;
import com.hope.entity.Entity;
import com.hope.entity.Item;
import com.hope.entity.enemies.Enemies;
import com.hope.map.MapSetting;
import com.hope.spritesheet.MapSpriteSheet;
import com.hope.map.tile.Tile;
import com.hope.map.tile.Solid;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class World {

	private Tile[] tiles;

	private int[] pixels;

	public static int WIDTH;

	public static int HEIGHT;

	public static final int TILE_SIZE = 16;

	private List<Enemy> enemies = new ArrayList<>();

	private List<Item> items = new ArrayList<>();

	private MapSetting mapSetting;
	
	public World(String path) {
		BufferedImage map = new MapSpriteSheet(path).getBufferedImage();

		this.mapSetting = new MapSetting(path);

		WIDTH = map.getWidth();
		HEIGHT = map.getHeight();

		this.pixels = new int[WIDTH * HEIGHT];
		this.tiles = new Tile[WIDTH * HEIGHT];

		map.getRGB(0, 0, WIDTH, HEIGHT, this.pixels, 0, WIDTH);

		getMapProperties();
	}
	
	private void getMapProperties() {
		int position;

		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {

				position = i + (j * WIDTH);

				getTiles()[position] = new Tile(i * TILE_SIZE, j * TILE_SIZE, Tile.TILE_GRASS);

				switch (this.pixels[position]) {
					case 0xFFFF6A00 -> getTiles()[position] = new Tile(i * TILE_SIZE, j * TILE_SIZE, Tile.TILE_FLOOR);
					case 0xFF7F3300 -> getTiles()[position] = new Solid(i * TILE_SIZE, j * TILE_SIZE, Tile.TILE_WALL);
					case 0xFF00FF21 -> getItems().add(new Item(getItems().size(), i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, 0, Entity.LIFE_PACK));
					case 0xFFFFC300 -> getItems().add(new Item(getItems().size(),i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, 1, Entity.SWORD));
					case 0xFFFF0000 -> getEnemies().add(new Enemy(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, Enemies.getEnemyByMap(this.mapSetting.name)));
				}

			}
		}
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public List<Enemy> getEnemies() {
		return this.enemies;
	}

	public List<Item> getItems() {
		return this.items;
	}

	private void setMapName(Graphics graphics) {
		graphics.setColor(Color.white);
		graphics.setFont(new Font("Arial", Font.BOLD, 7));
		graphics.drawString(this.mapSetting.name, Game.WIDTH - 35, 10);
	}

	public void Render(Graphics graphics) {
		if (getTiles().length > 0) {

			int xStart = Camera.x >> 4;
			int yStart = Camera.y >> 4;

			int xFinal = xStart + (Game.WIDTH / World.TILE_SIZE);
			int yFinal = yStart + (Game.HEIGHT / World.TILE_SIZE);

			for (int i = xStart; i <= xFinal + 2; i++) {
				for (int j = yStart; j <= yFinal + 2; j++) {

					if (i < 0 || j < 0 || i >= WIDTH || j >= HEIGHT)
						continue;

					Tile tile = getTiles()[i + (j * WIDTH)];
					tile.Render(graphics);
				}
			}
		}

		if (getEnemies().size() > 0)
			for (Enemy enemy : getEnemies()) enemy.Render(graphics);

		setMapName(graphics);
	}

}
