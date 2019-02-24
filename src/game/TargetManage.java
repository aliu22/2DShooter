package MyFirstGame;


import java.awt.Graphics2D;
import java.util.LinkedList;


public class TargetManage {
	LinkedList<Target> t = new LinkedList<Target>();
	
	Target target;
	
	public TargetManage() {
		addTarget();
	}
	
	public void render(Graphics2D g2) {
		for(int i=0; i<t.size(); i++) {
			target = t.get(i);
			if (target.health <= 0) {
				t.remove(i);
				
			}
			target.draw(g2);
		}
	}
	
	public void addTarget() {
		t.add(new Target(700,400, "/Images/badGuy.png"));
		t.add(new Target(200,200, "/Images/badGuy.png"));
	}
	
	
}
