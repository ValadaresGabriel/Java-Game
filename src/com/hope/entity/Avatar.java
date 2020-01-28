package com.hope.entity;

import com.hope.engine.Camera;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Avatar {
	
	protected int x;
	
	protected int y;
	
	protected int width;
	
	protected int height;
	
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
