package com.hope.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.hope.engine.Camera;
import com.hope.engine.Game;
import com.hope.entity.inventory.Inventory;
import com.hope.entity.stats.PlayerStats;
import com.hope.world.World;

import com.hope.engine.Collision;

public class Player extends Avatar {
	
	private boolean up;
	
	private boolean down;

	public boolean moving;

	public boolean damaged;

	public boolean levelUp;
	
	private static final int MOVEMENT_SPEED = 2;
	
	private List<BufferedImage> rightPlayer;
	
	private List<BufferedImage> leftPlayer;

	private List<BufferedImage> damagedRightPlayer;

	private List<BufferedImage> damagedLeftPlayer;

	public List<BufferedImage> weapons;

	public Inventory inventory;

	public Item weapon;

	private int damageDelay = 0;
	
	private BufferedImage lastDirection;

	private PlayerStats playerStats;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		this.rightPlayer = new ArrayList<>();
		this.leftPlayer = new ArrayList<>();
		this.damagedRightPlayer = new ArrayList<>();
		this.damagedLeftPlayer = new ArrayList<>();
		this.weapons = new ArrayList<>();

		this.playerStats = new PlayerStats();
		
		for (int i = 0; i < 2; i++) {
			this.rightPlayer.add(Game.playerSpritesheet.getSprite(World.TILE_SIZE * i, 0, World.TILE_SIZE, World.TILE_SIZE));
			this.leftPlayer.add(Game.playerSpritesheet.getSprite(World.TILE_SIZE - (World.TILE_SIZE * i), 16, World.TILE_SIZE, World.TILE_SIZE));
			this.damagedRightPlayer.add(Game.playerSpritesheet.getSprite(World.TILE_SIZE * i, World.TILE_SIZE * 2, World.TILE_SIZE, World.TILE_SIZE));
			this.damagedLeftPlayer.add(Game.playerSpritesheet.getSprite(World.TILE_SIZE * i, World.TILE_SIZE * 3, World.TILE_SIZE, World.TILE_SIZE));
		}
		
		this.lastDirection = this.rightPlayer.get(0);
	}
	
	public void Update() {
		int firstCollision = getX() + MOVEMENT_SPEED;
		int secondCollision = getX() - MOVEMENT_SPEED;
		int thirdCollision = getY() - MOVEMENT_SPEED;
		int fourthCollision = getY() + MOVEMENT_SPEED;

		if (getPlayerStats().getLife() <= 0)
			getPlayerStats().setLife(0);

		if (isRight() && Collision.isFree(firstCollision, getY())) {
			setMoving(true);
			x += MOVEMENT_SPEED;
			this.lastDirection = this.rightPlayer.get(0);
		} else if (isLeft() && Collision.isFree(secondCollision, getY())) {
			setMoving(true);
			x -= MOVEMENT_SPEED;
			this.lastDirection = this.leftPlayer.get(0);
		}
		
		if (isUp() && Collision.isFree(getX(), thirdCollision)) {
			setMoving(true);
			y -= MOVEMENT_SPEED;
		} else if (isDown() && Collision.isFree(getX(), fourthCollision)) {
			setMoving(true);
			y += MOVEMENT_SPEED;
		}

		movingAnimation();

		damagedAnimation();

		Collision.gettingItems();

		Camera.x = Camera.cameraLimit(getX() - (Game.WIDTH / 2), 0, (World.WIDTH * World.TILE_SIZE) - Game.WIDTH);

		Camera.y = Camera.cameraLimit(getY() - (Game.HEIGHT / 2), 0, (World.HEIGHT * World.TILE_SIZE) - Game.HEIGHT);
	}

	private void movingAnimation() {
		if (isMoving()) {
			this.delay++;

			if (getDelay() == 7) {
				resetDelay();
				this.frame++;
			}

			if (getFrame() == getRightPlayer().size())
				resetFrame();
		}
	}

	private void damagedAnimation() {
		if (isDamaged()) {
			this.damageDelay++;

			if (getDamageDelay() == 8) {
				this.damaged = false;
				resetDamageDelay();
			}
		}
	}

	public void reduceLife(double life) {
		this.damaged = true;
		getPlayerStats().setLife(getPlayerStats().getLife() - life);
	}

	public void addLife(double life) {
		getPlayerStats().setLife(getPlayerStats().getLife() + life);
	}

	public boolean isUp() {
		return this.up;
	}

	public boolean isDown() {
		return this.down;
	}

	public boolean isMoving() {
		return this.moving;
	}

	public boolean isDamaged() {
		return this.damaged;
	}

	public List<BufferedImage> getRightPlayer() {
		return this.rightPlayer;
	}

	public List<BufferedImage> getLeftPlayer() {
		return this.leftPlayer;
	}

	public BufferedImage getLastDirection() {
		return this.lastDirection;
	}

	public List<BufferedImage> getDamagedRightPlayer() {
		return damagedRightPlayer;
	}

	public List<BufferedImage> getDamagedLeftPlayer() {
		return damagedLeftPlayer;
	}

	public Item hasWeapon() {
		return this.weapon;
	}

	public int getDamageDelay() {
		return this.damageDelay;
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

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	private void resetDamageDelay() {
		this.damageDelay = 0;
	}

	public PlayerStats getPlayerStats() {
		return this.playerStats;
	}

	private void validatingPlayerAnimation(Graphics graphics) {
		BufferedImage rightPlayer = getRightPlayer().get(0);
		BufferedImage leftPlayer = getLeftPlayer().get(0);

		if (isMoving()) {
			if (isRight() || (getLastDirection().equals(rightPlayer) && (isUp() || isDown())))
				graphics.drawImage(getRightPlayer().get(getFrame()), getX() - Camera.x, getY() - Camera.y, null);
			else if (isLeft() || (getLastDirection().equals(leftPlayer) && (isUp() || isDown())))
				graphics.drawImage(getLeftPlayer().get(getFrame()), getX() - Camera.x, getY() - Camera.y, null);
			else
				graphics.drawImage(getLastDirection(), getX() - Camera.x, getY() - Camera.y, null);
		} else {
			graphics.drawImage(getLastDirection(), getX() - Camera.x, getY() - Camera.y, null);
		}
	}

	private void validatingPlayerAnimationDamaged(Graphics graphics) {
		BufferedImage rightPlayer = getRightPlayer().get(0);
		BufferedImage damagedRightPlayer = getDamagedRightPlayer().get(0);
		BufferedImage damagedLeftPlayer = getDamagedLeftPlayer().get(0);

		if (isMoving()) {
			if (getLastDirection().equals(rightPlayer))
				graphics.drawImage(getDamagedRightPlayer().get(getFrame()), getX() - Camera.x, getY() - Camera.y, null);
			else
				graphics.drawImage(getDamagedLeftPlayer().get(getFrame()), getX() - Camera.x, getY() - Camera.y, null);
		} else {
			if (getLastDirection().equals(rightPlayer))
				graphics.drawImage(damagedRightPlayer, getX() - Camera.x, getY() - Camera.y, null);
			else
				graphics.drawImage(damagedLeftPlayer, getX() - Camera.x, getY() - Camera.y, null);
		}
	}

	private void validatingWeaponAnimation(Graphics graphics) {
		BufferedImage rightPlayer = getRightPlayer().get(0);

		if (hasWeapon() != null) {
			if (getLastDirection().equals(rightPlayer))
				graphics.drawImage(this.weapons.get(0), getX() - Camera.x + 6, getY() - Camera.y - 2, null);
			else
				graphics.drawImage(this.weapons.get(1), getX() - Camera.x - 6, getY() - Camera.y - 2, null);
		}
	}
	
	@Override
	public void Render(Graphics graphics) {
		if (!isDamaged())
			validatingPlayerAnimation(graphics);
		else
			validatingPlayerAnimationDamaged(graphics);

		validatingWeaponAnimation(graphics);
	}

}
