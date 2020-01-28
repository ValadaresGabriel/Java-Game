package com.hope.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.hope.engine.Camera;
import com.hope.engine.Game;
import com.hope.world.World;

import com.hope.engine.Collision;

public class Player extends Avatar {

	private boolean right;
	
	private boolean left;
	
	private boolean up;
	
	private boolean down;

	public boolean isMoving;

	public boolean isDamaged;
	
	private static final int MOVEMENT_SPEED = 2;
	
	private List<BufferedImage> rightPlayer;
	
	private List<BufferedImage> leftPlayer;

	private List<BufferedImage> damagedPlayer;
	
	private int frame = 0;
	
	private int delay = 0;

	private int damageDelay = 0;
	
	private BufferedImage lastDirection; //Right = true / Left = false

	public double life = 100.0;

	public double maxLife = 100.0;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		this.rightPlayer = new ArrayList<>();
		this.leftPlayer = new ArrayList<>();
		this.damagedPlayer = new ArrayList<>();
		
		for (int i = 0; i < 2; i++) {
			this.rightPlayer.add(Game.playerSpritesheet.getSprite((i * World.TILE_SIZE), 0, World.TILE_SIZE, World.TILE_SIZE));
			this.leftPlayer.add(Game.playerSpritesheet.getSprite(World.TILE_SIZE - (i * World.TILE_SIZE), 16, World.TILE_SIZE, World.TILE_SIZE));
			this.damagedPlayer.add(Game.playerSpritesheet.getSprite(0, (World.TILE_SIZE * 2) + (i * World.TILE_SIZE), World.TILE_SIZE, World.TILE_SIZE));
		}
		
		this.lastDirection = this.rightPlayer.get(0);
	}
	
	public void Update() {
		int firstCollision = getX() + MOVEMENT_SPEED;
		int secondCollision = getX() - MOVEMENT_SPEED;
		int thirdCollision = getY() - MOVEMENT_SPEED;
		int fourthCollision = getY() + MOVEMENT_SPEED;

		if (this.right && Collision.isFree(firstCollision, getY())) {
			this.isMoving = true;
			x += MOVEMENT_SPEED;
			this.lastDirection = this.rightPlayer.get(0);
		} else if (this.left && Collision.isFree(secondCollision, getY())) {
			this.isMoving = true;
			x -= MOVEMENT_SPEED;
			this.lastDirection = this.leftPlayer.get(0);
		}
		
		if (this.up && Collision.isFree(getX(), thirdCollision)) {
			this.isMoving = true;
			y -= MOVEMENT_SPEED;
		} else if (this.down && Collision.isFree(getX(), fourthCollision)) {
			this.isMoving = true;
			y += MOVEMENT_SPEED;
		}
		
		if (this.isMoving) {
			this.delay++;
			
			if (delay == 7) {
				this.delay = 0;
				this.frame++;
			}
			
			if (this.frame == this.rightPlayer.size())
				this.frame = 0;
		}

		if (this.isDamaged) {
			this.damageDelay++;

			if (this.damageDelay == 8) {
				this.isDamaged = false;
				this.damageDelay = 0;
			}
		}

		Collision.gettingItems();

		Camera.x = Camera.cameraLimit(getX() - (Game.WIDTH / 2), 0, World.WIDTH * World.TILE_SIZE - Game.WIDTH);

		Camera.y = Camera.cameraLimit(getY() - (Game.HEIGHT / 2), 0, World.HEIGHT * World.TILE_SIZE - Game.HEIGHT);
	}

	public double getLife() {
		return this.life;
	}

	public double getMaxLife() {
		return this.maxLife;
	}

	public void reduceLife(double life) {
		this.isDamaged = true;
		this.life -= life;
	}

	public void addLife(double life) {
		this.life += life;
	}

	public boolean isRight() {
		return this.right;
	}

	public boolean isLeft() {
		return this.left;
	}

	public void setLife(double life) {
		this.life = life;
	}

	public void setMaxLife(double maxLife) {
		this.maxLife = maxLife;
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public void setUp(boolean up) {
		this.up = up;
	}
	
	public void setDown(boolean down) {
		this.down = down;
	}
	
	@Override
	public void Render(Graphics graphics) {
		if (!this.isDamaged) {
			if (this.isMoving) {
				if (isRight())
					graphics.drawImage(this.rightPlayer.get(this.frame), getX() - Camera.x, getY() - Camera.y, null);
				else if (isLeft())
					graphics.drawImage(this.leftPlayer.get(this.frame), getX() - Camera.x, getY() - Camera.y, null);
				else
					graphics.drawImage(this.lastDirection, getX() - Camera.x, getY() - Camera.y, null);
			} else {
				graphics.drawImage(this.lastDirection, getX() - Camera.x, getY() - Camera.y, null);
			}
		} else {
			if (this.lastDirection.equals(this.rightPlayer.get(0)))
				graphics.drawImage(this.damagedPlayer.get(0), getX() - Camera.x, getY() - Camera.y, null);
			else
				graphics.drawImage(this.damagedPlayer.get(1), getX() - Camera.x, getY() - Camera.y, null);
		}
	}

}
