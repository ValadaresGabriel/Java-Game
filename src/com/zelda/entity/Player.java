package com.zelda.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.zelda.engine.Camera;
import com.zelda.engine.Game;
import com.zelda.world.World;

import com.zelda.engine.Collision;

public class Player extends Avatar {

	private boolean right;
	
	private boolean left;
	
	private boolean up;
	
	private boolean down;
	
	private static final int MOVEMENT_SPEED = 2;
	
	private List<BufferedImage> rightPlayer;
	
	private List<BufferedImage> leftPlayer;
	
	private int frame = 0;
	
	private int delay = 0;
	
	public boolean isMoving;
	
	private BufferedImage lastDirection; //Right = true / Left = false

	public double life = 100.0;

	public double maxLife = 100.0;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		this.rightPlayer = new ArrayList<>();
		this.leftPlayer = new ArrayList<>();
		
		for (int i = 0; i < 2; i++)
			this.rightPlayer.add(Game.worldSpritesheet.getSprite(World.TILE_SIZE * 2 + (i * World.TILE_SIZE), 0, World.TILE_SIZE, World.TILE_SIZE));
		
		for (int i = 0; i < 2; i++)
			this.leftPlayer.add(Game.worldSpritesheet.getSprite(World.TILE_SIZE * 3 - (i * World.TILE_SIZE), 16, World.TILE_SIZE, World.TILE_SIZE));
		
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

		Camera.x = Camera.cameraLimit(getX() - (Game.WIDTH / 2), 0, World.WIDTH * World.TILE_SIZE - Game.WIDTH);

		Camera.y = Camera.cameraLimit(getY() - (Game.HEIGHT / 2), 0, World.HEIGHT * World.TILE_SIZE - Game.HEIGHT);
	}

	public void reduceLife(double life) {
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
	public void Render(Graphics graphic) {
		if (this.isMoving) {
			if (this.right)
				graphic.drawImage(this.rightPlayer.get(this.frame), getX() - Camera.x, getY() - Camera.y, null);
			else if (this.left)
				graphic.drawImage(this.leftPlayer.get(this.frame), getX() - Camera.x, getY() - Camera.y, null);
			else
				graphic.drawImage(this.lastDirection, getX() - Camera.x, getY() - Camera.y, null);
		} else {
			graphic.drawImage(this.lastDirection, getX() - Camera.x, getY() - Camera.y, null);
		}
	}

}
