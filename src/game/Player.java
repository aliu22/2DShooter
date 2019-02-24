package MyFirstGame;

import java.awt.Image;
import java.awt.Rectangle;


import javax.swing.ImageIcon;

public class Player {
	
	ImageIcon player;
	Rectangle ballRect;
	
	//Define borders
	//leftBorder
	public static int thickness = 4;
	static Rectangle leftBorder = new Rectangle(0,0,thickness,Main.yDim);
	//right border
	static Rectangle rightBorder = new Rectangle(Main.xDim-thickness,0,thickness,Main.yDim);
	// top border
	static Rectangle topBorder = new Rectangle(0,0,Main.xDim,thickness);
	//bottom border
	static Rectangle botBorder = new Rectangle(0,Main.yDim-thickness,Main.xDim,thickness);
	
	//Map Object collisions
	static MapObjects map = new MapObjects();
	
	double windowSize = 200;
	
	private static double playerX = 310;
	public static double playerY = 10;
	private double xSpeed;
	public double ySpeed;
	public double gravity = 1.7;
	private double playerSpeedx = gravity*4;
	private double playerSpeedy = gravity*15;
	public boolean onGround; 
	public boolean [] keyLog = new boolean [4];
	
	public Player(String file){
		player = new ImageIcon(Player.class.getResource(file));
		
	}

	public void updatePlayer() {
		addGravity();
		keyEventLoop();
		mapCollision();
		wallCollision();
		
		speedCap();
		preventSkid();
		
		playerX = playerX + xSpeed;
		playerY = playerY + ySpeed;
		
		checkOnGround();
		
	}
	
	
	public Image getPlayer(){
		return player.getImage();
	}
	public double getX() {
		return playerX;
	}
	public double getY() {
		return playerY;
	}

	
	public double getWidth() {
		return player.getIconWidth();
	}
	
	public double getHeight() {
		return player.getIconHeight();
	}
	

	public Rectangle getRect() {
		return new Rectangle((int)(playerX+xSpeed), (int)(playerY+ySpeed), (int)this.getWidth(), (int)this.getHeight());
	}
	
	
	public Rectangle getCurRect() {
		return new Rectangle((int)(playerX), (int)(playerY), (int)this.getWidth(), (int)this.getHeight());
	}
	
	
	public void wallCollision() {
		ballRect = this.getRect();
		
		//right wall collision
		if(ballRect.intersects(rightBorder)) {
			playerX = (Main.xDim - this.getWidth() - 1);
			xSpeed = 0;
		}
		//define left wall collision
		if(ballRect.intersects(leftBorder)) {
			playerX = (thickness);
			xSpeed = 0;
		}
		//define top wall collision
		if(ballRect.intersects(topBorder)) {
			playerY = (thickness);
			ySpeed = -0.01*ySpeed;
		}
		//define bottom wall collision
		if(ballRect.intersects(botBorder)) {
			playerY = (Main.yDim - this.getHeight() - thickness);
			ySpeed = 0;
		}
			
	
	}
	public void addGravity() {
		ySpeed += gravity;
	}
	
	public void preventSkid() {
		if ((!keyLog[0] && !keyLog[1]) && onGround) {
			xSpeed = 0;
		}
	}
	
	public void speedCap() {
		if (Math.abs(xSpeed) > playerSpeedx) {
			if (xSpeed>0) xSpeed = playerSpeedx;
			else xSpeed = -playerSpeedx;
		}
	}
	
	public void checkOnGround() {
		if(ySpeed == 0) {
			onGround = true;
		} else onGround = false;
	}
	
	

	
	
	public void pressedKey(int i) {
		if(i == 0) {
			this.keyLog[i] = true;
		}
		if(i == 1) {
			this.keyLog[i] = true;
		}
		if(i == 2) {
			this.keyLog[i] = true;
		}
		if(i == 3) {
			this.keyLog[i] = true;
		}
		
	}
	
	public void releasedKey(int i) {
		if(i == 0) {
			if(onGround) {xSpeed = 0;}
			keyLog[i] = false;
		}
		if(i == 1) {
			if(onGround) {xSpeed = 0;}
			keyLog[i] = false;
		}
		if(i == 2) {
			keyLog[i] = false;
		}
		if(i == 3) {
			keyLog[i] = false;
		}
		
	}
	
	
	public void keyEventLoop() {
		for (int i = 0; i < this.keyLog.length; i++) {
			if (this.keyLog[i])
					
					readKeyLog(i);
					
		}	
	}
	
	
	private void readKeyLog(int keyLogIndex) {
		
		ballRect = this.getRect();
		
		if (keyLogIndex == 0 && !ballRect.intersects(rightBorder))
			moveRight();
		if (keyLogIndex == 1 && !ballRect.intersects(leftBorder))
			moveLeft();
		
		//only allow jump if onGround
		if (keyLogIndex == 2 && !ballRect.intersects(topBorder) && onGround)
			moveUp();
		if (keyLogIndex == 3 && !ballRect.intersects(botBorder))
			moveDown();
	}
	
	//define directional functions
	public void moveRight() {
		xSpeed = playerSpeedx;
	}
	
	public void moveLeft() {
		xSpeed = -playerSpeedx;
	}
	
	
	public void moveUp() {
		ySpeed -= playerSpeedy;
	}
	
	public void moveDown() {
		ySpeed += playerSpeedy;
	}
	public void jumpUp() {
		ySpeed = -playerSpeedy*1.4;
	}
	
	public void mapCollision() {
		
		Rectangle playerRect = getRect();
		for (int i = 0; i< MapObjects.numObj; i++) {
			if (playerRect.intersects(map.getRect(i))){
				//above platform
				if(playerY + getHeight() - ySpeed <= map.yPos[i]) {
					playerY = map.yPos[i] - getHeight();
					ySpeed = 0;
					
				}
				//below platform
				if(playerY >= map.yPos[i] + map.height[i]) {
					playerY = map.yPos[i] + map.height[i];
					ySpeed = -0.01*ySpeed;
				}	
				//left of platform
				if (playerX + getWidth() -xSpeed <= map.xPos[i]) {
					playerX = map.xPos[i] - getWidth();
					xSpeed = 0;
				}
				
				//right of platform
				if (playerX -xSpeed >= map.xPos[i] + map.width[i]) {
					playerX = map.xPos[i] + map.width[i];
					xSpeed = 0;
					
				}
				
			}
		
		}
		
	}
	
	public Rectangle playerCursorRange() {
		return new Rectangle((int)(playerX-windowSize), (int)(playerY-windowSize), 
				(int)(this.getHeight()+windowSize*2), (int)(this.getHeight()+windowSize*2));
	}
}
