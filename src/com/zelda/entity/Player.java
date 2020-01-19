package com.zelda.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.zelda.engine.Camera;
import com.zelda.engine.Game;
import com.zelda.tile.Solid;
import com.zelda.world.World;

public class Player extends Avatar {

	private static final int TILE_SIZE = 16;
	private boolean right;
	
	private boolean left;
	
	private boolean up;
	
	private boolean down;
	
	private static final int MOVEMENT_SPEED = 1;
	
	private List<BufferedImage> rightPlayer;
	
	private List<BufferedImage> leftPlayer;
	
	private int frame = 0;
	
	private int delay = 0;
	
	public boolean isMoving;
	
	private BufferedImage lastDirection; //Right = true / Left = false
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		this.rightPlayer = new ArrayList<>();
		this.leftPlayer = new ArrayList<>();
		
		for (int i = 0; i < 2; i++)
			this.rightPlayer.add(Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16));
		
		for (int i = 0; i < 2; i++)
			this.leftPlayer.add(Game.spritesheet.getSprite(48 - (i * 16), 16, 16, 16));
		
		this.lastDirection = this.rightPlayer.get(0);
	}
	
	public void Update() {
		if (this.right && isFree(getX() + MOVEMENT_SPEED, getY())) {
			this.isMoving = true;
			x += MOVEMENT_SPEED;
			this.lastDirection = this.rightPlayer.get(0);
		} else if (this.left && isFree(getX() - MOVEMENT_SPEED, getY())) {
			this.isMoving = true;
			x -= MOVEMENT_SPEED;
			this.lastDirection = this.leftPlayer.get(0);
		}
		
		if (this.up && isFree(getX(), getY() - MOVEMENT_SPEED)) {
			this.isMoving = true;
			y -= MOVEMENT_SPEED;
		} else if (this.down && isFree(getX(), getY() + MOVEMENT_SPEED)) {
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

		Camera.x = Camera.cameraLimit((int)getX() - (Game.WIDTH / 2), 0, World.WIDTH * 16 - Game.WIDTH);

		Camera.y = Camera.cameraLimit((int)getY() - (Game.HEIGHT / 2), 0, World.HEIGHT * 16 - Game.HEIGHT);
	}

	public static boolean isFree(int x, int y) {
		int firstTestX = x / TILE_SIZE;
		int firstTestY = y / TILE_SIZE;

		int secondTestX = (x + TILE_SIZE - 1) / TILE_SIZE;
		int secondTestY = y / TILE_SIZE;

		int thirdTestX = x / TILE_SIZE;
		int thirdTestY = (y + TILE_SIZE - 1) / TILE_SIZE;

		int fourthTestX = (x + TILE_SIZE - 1) / TILE_SIZE;
		int fourthTestY = (y + TILE_SIZE - 1) / TILE_SIZE;

		return !(World.tiles[firstTestX + (firstTestY * World.WIDTH)] instanceof Solid ||
				World.tiles[secondTestX + (secondTestY * World.WIDTH)] instanceof Solid ||
				World.tiles[thirdTestX + (thirdTestY * World.WIDTH)] instanceof Solid ||
				World.tiles[fourthTestX + (fourthTestY * World.WIDTH)] instanceof Solid);
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
				graphic.drawImage(this.rightPlayer.get(this.frame), (int)getX() - Camera.x, (int)getY() - Camera.y, null);
			else if (this.left)
				graphic.drawImage(this.leftPlayer.get(this.frame), (int)getX() - Camera.x, (int)getY() - Camera.y, null);
			else
				graphic.drawImage(this.lastDirection, (int)getX() - Camera.x, (int)getY() - Camera.y, null);
		} else {
			graphic.drawImage(this.lastDirection, (int)getX() - Camera.x, (int)getY() - Camera.y, null);
		}
	}

}
