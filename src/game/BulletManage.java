package MyFirstGame;


import java.awt.Graphics2D;
import java.util.LinkedList;


public class BulletManage {
	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	
	Bullet bullet;
	
	
	public void render(Graphics2D g2) {
		for(int i=0; i<b.size(); i++) {
			bullet = b.get(i);
			bullet.tick();
			
			if (bullet.mapCollide) {
				b.remove(i);
			}
			else if (bullet.borderCollide) {
				b.remove(i);
			}
			else if (bullet.targetCollide) {
				b.remove(i);
			}
			bullet.draw(g2);
			
		}
	}
	
	public void addBullet() {
		if(Shoot.displayScope) {
			b.add(new Bullet());
		}else System.out.println("Put mouse inside range");
		
		
	}
	
	
}
