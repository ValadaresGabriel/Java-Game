package com.zelda.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Avatar {
	
	protected double x;
	
	protected double y;
	
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
		graphics.drawImage(getSprite(), (int)getX(), (int)getY(), null);
	}
	
	private void setX(double x) {
		this.x = x;
	}
	
	private void setY(double y) {
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
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
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
