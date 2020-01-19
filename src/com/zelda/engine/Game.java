package com.zelda.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.zelda.panel.MainPanel;
import com.zelda.spritesheet.Spritesheet;
import com.zelda.world.World;
import com.zelda.entity.*;

public class Game extends MainPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private boolean isRunning = true;
	
	private Thread thread;
	
	private BufferedImage image;
	
	private Player player;
	
	private List<Player> players = new ArrayList<>();
	
	public static Spritesheet spritesheet;
	
	public static World world;
	
	public Game() {
		new Move(this);
		this.image = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);

		spritesheet = new Spritesheet("/SpriteSheet.png");

		world = new World("/Map_1.png");

		this.player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
		
		this.players.add(this.player);
		
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
		for (Player value : this.players) value.Update();
	}
	
	private void Render() {
		BufferStrategy bufferStrategy = getBufferStrategy();
		
		if (bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = this.image.getGraphics();

		graphics.setColor(new Color(40, 40, 40));
		graphics.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

		world.Render(graphics);

		for (Player player : this.players) player.Render(graphics);

		graphics.dispose();
		graphics = bufferStrategy.getDrawGraphics();
		graphics.drawImage(this.image, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
		
		bufferStrategy.show();
	}

	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		final long nano = 1000000000;
		final double FPS = 60.0; //FPS setado
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

	public Player getPlayer() {
		return this.player;
	}

}
