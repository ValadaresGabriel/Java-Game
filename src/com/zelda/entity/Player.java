package com.zelda.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.zelda.engine.Game;

public class Player extends Avatar {

	private boolean right;
	
	private boolean left;
	
	private boolean up;
	
	private boolean down;
	
	private static final double MOVEMENT_SPEED = 1.0;
	
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
		if (this.right) {
			this.isMoving = true;
			x += MOVEMENT_SPEED;
			this.lastDirection = this.rightPlayer.get(0);
		} else if (this.left) {
			this.isMoving = true;
			x -= MOVEMENT_SPEED;
			this.lastDirection = this.leftPlayer.get(0);
		}
		
		if (this.up) {
			this.isMoving = true;
			y -= MOVEMENT_SPEED;
		} else if (this.down) {
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
				graphic.drawImage(this.rightPlayer.get(this.frame), (int)getX(), (int)getY(), null);
			else if (this.left)
				graphic.drawImage(this.leftPlayer.get(this.frame), (int)getX(), (int)getY(), null);
			else
				graphic.drawImage(this.lastDirection, (int)getX(), (int)getY(), null);
		} else {
			graphic.drawImage(this.lastDirection, (int)getX(), (int)getY(), null);
		}
	}

}
