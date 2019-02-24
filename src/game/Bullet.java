package MyFirstGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bullet {
	
	
	double vx;
	double vy;
	
	double bulletVelo = 10;
	double bulletX;
	double bulletY;
	int bulletSize = 10;
	boolean borderCollide; 
	boolean mapCollide;
	boolean targetCollide;
	MapObjects map = new MapObjects();
	
	//allow gameplay to reference target object
//	TargetManage target = Gameplay.target;
	
	public Bullet() {
		initBullet();
	}
	
	
	
	public void initBullet() {
			
				
		vx = Math.cos(Shoot.angle) * bulletVelo;
		vy = Math.sin(Shoot.angle) * bulletVelo;
		bulletX = Shoot.startX() - bulletSize/2;
		bulletY = Shoot.startY() - bulletSize/2;
				
				
	}

		
	public void tick() {
		bulletX += vx;
		bulletY -= vy;
		mapCollision();
		targetCollision();
		if (!mapCollide && !targetCollide) {
			borderCollision();
		}
	}
	
	
	public Rectangle getRect() {
		return new Rectangle((int)bulletX, (int)bulletY, bulletSize, bulletSize);
	}
	
	
	public void mapCollision() {
		Rectangle bulletRect = getRect();
		//bullet collision to walls
		for (int i = 0; i < MapObjects.numObj; i++) {
			if (bulletRect.intersects(Player.map.getRect(i))) {
				mapCollide = true;
			}
		}
	}
	
	public void targetCollision() {
		Rectangle bulletRect = getRect();
		
		//bullet collision to target manager
		for (int i = 0; i < Gameplay.target.t.size(); i++) {
			
			
			Target enemy = Gameplay.target.t.get(i);
			if (bulletRect.intersects(enemy.getRect())) {
				enemy.tickDamage();
				targetCollide = true;
				
			}
		}
	}
	
	
	public void borderCollision() {
		Rectangle bulletRect = getRect();
		
		//bullet collision to border boundaries
		if (bulletRect.intersects(Player.leftBorder) ||
			bulletRect.intersects(Player.rightBorder) ||
			bulletRect.intersects(Player.topBorder) ||
			bulletRect.intersects(Player.botBorder)){
			
			borderCollide = true;
		}
	}
	
	
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		//tick();
		g2.fillOval((int)bulletX, (int)bulletY, bulletSize, bulletSize);
		
		
	}	
}
