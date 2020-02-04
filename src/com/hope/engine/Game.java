package com.hope.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.hope.panel.MainPanel;
import com.hope.panel.Ui;
import com.hope.spritesheet.EnemySpriteSheet;
import com.hope.spritesheet.ItemSpriteSheet;
import com.hope.spritesheet.PlayerSpriteSheet;
import com.hope.spritesheet.WorldSpriteSheet;
import com.hope.world.World;
import com.hope.entity.*;

public class Game extends MainPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private boolean isRunning = true;
	
	private Thread thread;
	
	private BufferedImage image;
	
	public static Player player;

	public static ItemSpriteSheet itemSpritesheet;

	public static WorldSpriteSheet worldSpritesheet;

	public static PlayerSpriteSheet playerSpritesheet;

	public static EnemySpriteSheet enemySpritesheet;

	private Ui ui;
	
	public static World world;
	
	public Game() {
		new Events(this);

		this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		itemSpritesheet = new ItemSpriteSheet("/ItemSpriteSheet.png");

		worldSpritesheet = new WorldSpriteSheet("/WorldSpriteSheet.png");

		playerSpritesheet = new PlayerSpriteSheet("/PlayerSpriteSheet.png");

		player = new Player(World.TILE_SIZE * 2, World.TILE_SIZE * 2, World.TILE_SIZE, World.TILE_SIZE, worldSpritesheet.getSprite(0, 0, World.TILE_SIZE, World.TILE_SIZE));

		enemySpritesheet = new EnemySpriteSheet("/EnemySpriteSheet.png");

		world = new World("/Prontera.png");

		this.ui = new Ui();
		
		Start();
	}
	
	private void Start() {
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	private void Stop() {
		this.isRunning = false;
		
		try {
			this.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void Update() {
		getPlayer().Update();

		for (Enemy enemy : world.getEnemies()) enemy.Update();
	}
	
	private void Render() {
		BufferStrategy bufferStrategy = getBufferStrategy();
		
		if (bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = this.image.getGraphics();

		graphics.setColor(new Color(40, 40, 40));
		graphics.fillRect(0, 0, WIDTH, HEIGHT);

		world.Render(graphics);

		getPlayer().Render(graphics);

		for (Enemy enemy : world.getEnemies()) enemy.Render(graphics);

		for (Item item : world.getItems()) item.Render(graphics);

		this.ui.Render(graphics);

		graphics.dispose();
		graphics = bufferStrategy.getDrawGraphics();
		graphics.drawImage(this.image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		bufferStrategy.show();
	}

	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		final long nano = 1000000000; //1 billion
		final double FPS = 60.0; //FPS fixed
		double ns = nano / FPS;
		double delta = 0;

		//debug
		int frames = 0;
		double timer = System.currentTimeMillis();

		while (this.isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				Update();
				Render();
				frames++;
				delta--;
			}

			//debug
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}

		Stop();
	}

	public static Player getPlayer() {
		return player;
	}

}
