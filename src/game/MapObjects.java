package MyFirstGame;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class MapObjects{
	public static int numObj = 5;
	
	public Rectangle aMap[] = new Rectangle[numObj];
	public double height[] = {100, 300, 10, 10, 10};
	public double width[] = {150,10, 200, 200, 200};
	public double xPos[] = {150, 350, 300, 450, 600};
	public double yPos[] = {500, 500, 350, 200, 150};
	
	public MapObjects(){
		for (int i=0; i<numObj; i++) {
			aMap[i] = new Rectangle((int)xPos[i], (int)yPos[i], (int)width[i], (int)height[i]);
			
		}
	}
	
	
	public Rectangle getRect(int i) {
		return aMap[i];
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.GRAY);
		for (int i=0; i<numObj; i++) {
			g2.fill(aMap[i]);
		}
	}
	
	
}