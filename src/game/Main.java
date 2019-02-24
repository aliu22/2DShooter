package MyFirstGame;

import javax.swing.*;



public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;
	public static final int xDim = 900;
	public static final int yDim = 600;
	public static void main(String[] args) {
		JFrame obj = new JFrame();
		
		Gameplay gamePlay = new Gameplay();
		obj.setBounds(0,0,xDim,yDim+22);
		obj.setTitle("Play!");
		//obj.setResizable(false);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		obj.add(gamePlay);
		obj.setVisible(true);
	}
	
	
	
}
