package MyFirstGame;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Target{
	public static int numObj = 1;
	
	public Rectangle targetRect;
	public Rectangle healthBar;
	
	double totalHealth = 100;
	
	//unit length of one health point
	double unit = 0.5;
	
	//damage per shot
	double damage = 20;
	double barThickness = 3;
	double barLength = totalHealth * unit;
	double health;
	double amountBar = barLength;
	
	public double height;
	public double width;
	
	
	public double initX;
	public double initY;
	
	public double xPos;
	public double yPos;
	ImageIcon enemy;
	public double vy = -1;
	
	public Target(double x, double y, String file){
		enemy = new ImageIcon(Player.class.getResource(file)); 
		height = enemy.getIconHeight();
		width = enemy.getIconWidth();
		initX = x;
		initY = y;
		xPos = initX;
		yPos = initY;
		targetRect = new Rectangle((int)xPos, (int)yPos, (int)width, (int)height);
		//initialize health of target
		health = totalHealth;
		healthBar = new Rectangle((int)(x - barLength/2 + width/2), 
					(int)(y - barThickness*2), (int)barLength, (int)barThickness);
			
		
	}
	
	public void tickDamage() {
		health -= damage;
		amountBar -= damage*unit;
		//shorten length of health bar per shot taken
		healthBar = new Rectangle((int)(xPos - barLength/2 + width/2), 
				(int)(yPos - barThickness*2), (int)amountBar, (int)barThickness);
	}
	
	
	public Rectangle getRect() {
		return new Rectangle((int)xPos, (int)yPos, (int)width, (int)height);
	}
	
	public void tickPosition() {
		if (yPos < initY -50) {vy *= -1;}
		if (yPos > initY  + 50) {vy *= -1;}
		yPos = yPos += vy;
		healthBar = new Rectangle((int)(xPos - barLength/2 + width/2), 
				(int)(yPos - barThickness*2), (int)amountBar, (int)barThickness);
	}
	
	public void draw(Graphics2D g2) {
		//g2.setColor(Color.GRAY);
		//g2.draw(targetRect);
		tickPosition();
		g2.drawImage(enemy.getImage(), (int) xPos, (int) yPos, null);
		
		
		if (health >= totalHealth*0.66) {
			g2.setColor(Color.GREEN);
		}
		else if (health >= totalHealth*0.33) {
			g2.setColor(Color.YELLOW);
		}
		else if (health >= 0) {
			g2.setColor(Color.RED);
		}
		g2.fill(healthBar);
		//tickDamage();
	}
	
	
}