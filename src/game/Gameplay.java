package MyFirstGame;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	
	private static final long serialVersionUID = 1L;


	public static boolean play;
	private Timer time;
	private int delay =  20;
	static Cursor cursor;
	
	
	static Player player = new Player("/Images/guy.png");
	MapObjects map = new MapObjects();
	Shoot shoot = new Shoot();
	BulletManage bullet = new BulletManage();
	static TargetManage target = new TargetManage();
	
	
	
	
	//constructor
	public Gameplay() {
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		addKeyListener(this);
		addMouseMotionListener(shoot);
		addMouseListener(shoot);
		//addKeyListener(shoot);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
		
	}
	
	
	//object painting
	public void paint(Graphics g) {
		
		super.paintComponent(g);
		//background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Main.xDim, Main.yDim);
		
		Graphics2D g2 = (Graphics2D) g;
		
		//borders
		g.setColor(Color.GREEN);
		g2.fill(Player.leftBorder);
		g2.fill(Player.rightBorder);
		g2.fill(Player.botBorder);
		g2.fill(Player.topBorder);
		
		//draw map objects
		map.draw(g2);
	
		
		g.setColor(Color.YELLOW);

		//player
		g2.drawImage(player.getPlayer(), (int) player.getX(), (int) player.getY(), null);
		g2.draw(player.getCurRect());
		//g2.draw(player.playerCursorRange());
		
		//scope
		//draw cursor, crosshair when s pressed
		
		shoot.drawScope(g2);
		
		//draw bullet
		bullet.render(g2);
		
		//draw target
		target.render(g2);
	}

	//repaint 
	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		if(play) {
			player.updatePlayer();
		}
		
		
		repaint();
		
	}

	
	
	
	
	//define key events
	@Override
	public void keyPressed(KeyEvent e) {
		play = true;
		
		//user movement
		if (e.getKeyCode() == KeyEvent.VK_D) {player.pressedKey(0);}			
		if (e.getKeyCode() == KeyEvent.VK_A) {player.pressedKey(1);}			
		if (e.getKeyCode() == KeyEvent.VK_W) {player.pressedKey(2);}	
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {player.pressedKey(3);}
		
		
		
		//exit system
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {System.exit(0);}
	}	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_D) {player.releasedKey(0);}	
		if (e.getKeyCode() == KeyEvent.VK_A) {player.releasedKey(1);}
		if (e.getKeyCode() == KeyEvent.VK_W) {player.releasedKey(2);}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {player.releasedKey(3);}
		
		
//		//turn on shoot 
//		if (e.getKeyCode() == KeyEvent.VK_S) {
//			Shoot.shootOn = !Shoot.shootOn;
//		}	
		
		//shoot bullet
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			bullet.addBullet();
		}	
		
//		//add target
//		if (e.getKeyCode() == KeyEvent.VK_T) {
//			target.addTarget();
//		}
	}
	@Override
	public void keyTyped(KeyEvent e) {}

	
}
	
	
