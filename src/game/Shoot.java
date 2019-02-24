package MyFirstGame;



import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;


public class Shoot extends JPanel implements MouseMotionListener, MouseListener{
	
	static boolean displayScope;
	double cursorX;
	double cursorY;
	Point p = new Point(0,0);
	static double angle;

	double bulletVelo = 3;

	
	//constructor
	public Shoot() {}
	
	
//	//return cursor format 
//	public Cursor setCursor() {
//		
//		if(displayScope) {
//			Gameplay.cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
//			return Gameplay.cursor;
//		}else {
//			Gameplay.cursor = (getToolkit().createCustomCursor(
//	                new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
//	                new Point(),
//	                null));
//			return Gameplay.cursor;
//		}
//		
//	}
	
	//find location of player x, centered
	public static double startX() {
		return(Gameplay.player.getX() + Gameplay.player.getWidth()/2);
	}
	//find location of player y, centered
	public static double startY() {
		return(Gameplay.player.getY() + Gameplay.player.getHeight()/2);
	}
	
	
	
	//draw line connecting player to cursor
	public void drawScope(Graphics2D g2) {
		
		if(displayScope) {
			g2.setColor(Color.RED);
			g2.drawLine((int)startX(), (int)startY(), (int)cursorX, (int)cursorY);
		}
		withinWindow();
	}
	
	//calculate angle formed from cursor to the horizontal
	public void angleFormed() {
		double slope = -(cursorY - startY())/(cursorX - startX());
		angle = Math.atan(slope);
		
		//2nd quadrant
		if(cursorY<startY() && cursorX < startX()) {
			angle = angle + Math.PI;
		}
		//3rd quadrant
		else if(cursorY>startY() && cursorX < startX()) {
			angle = angle + Math.PI;
		}
		//4th quadrant
		else if(cursorY>startY() && cursorX > startX()) {
			angle = angle + 2*Math.PI;
		}
		//vertical down
		else if(cursorX == startX() && cursorY>=startY()) {
			angle = 3*Math.PI/2;
		}
		//horizontal left
		else if(cursorY == startY() && cursorX<=startX()) {
			angle = Math.PI;
		}
		
	}

	
	
	public void updateCursor(MouseEvent e) {
		p = e.getPoint();
		cursorX = p.x;
		cursorY = p.y;
	}

	
	public void withinWindow() {
		if (Gameplay.player.playerCursorRange().contains(p)){
			displayScope = true;
		}else displayScope = false;
	}
	
	//=================================================================================
	//key events
	@Override
	public void mouseDragged(MouseEvent e) {
		updateCursor(e);
		//withinWindow();
		if(displayScope) {
			angleFormed();
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		updateCursor(e);
		
		//withinWindow();
		if(displayScope) {
			angleFormed();
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {}	
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	
}
