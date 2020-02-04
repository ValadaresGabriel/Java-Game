package com.hope.entity;

import com.hope.engine.Camera;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Avatar {
	
	protected int x;
	
	protected int y;
	
	protected int width;
	
	protected int height;

	protected boolean right;

	protected boolean left;

	protected int frame = 0;

	protected int delay = 0;
	
	private BufferedImage sprite;
	
	public Avatar(int x, int y, int width, int height, BufferedImage sprite) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setSprite(sprite);
	}

	public void Update() {
		//
	}
	
	public void Render(Graphics graphics) {
		graphics.drawImage(getSprite(), getX() - Camera.x, getY() - Camera.y, null);
	}

	private void setX(int x) {
		this.x = x;
	}

	private void setY(int y) {
		this.y = y;
	}
	
	private void setWidth(int width) {
		this.width = width;
	}
	
	private void setHeight(int height) {
		this.height = height;
	}
	
	private void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public boolean isRight() {
		return this.right;
	}

	public boolean isLeft() {
		return this.left;
	}

	protected void setRight(boolean right) {
		this.right = right;
	}

	protected void setLeft(boolean left) {
		this.left = left;
	}

	protected void resetDelay() {
		this.delay = 0;
	}

	protected void resetFrame() {
		this.frame = 0;
	}

	public int getDelay() {
		return this.delay;
	}

	public int getFrame() {
		return frame;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
	
	public BufferedImage getSprite() {
		return this.sprite;
	}

}
