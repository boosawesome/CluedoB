package ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import game.Player;
import items.Card;

public class CardPanel extends JPanel{
	
	public JPanel parent;
	
	
	private JLabel info; 
	private JPanel panel; 
	public Player p;
	private ArrayList<Card> cards;
	

	
	public JButton c1;
	public JButton c2;
	public JButton c3;
	public JButton c4;
	public JButton c5;
	public JButton c6;
	
	public ArrayList<JButton> slots;
	
	public CardPanel(){
		
		
		
		panel = new JPanel();
		if(p != null){
		info = new JLabel("Player "+ p.num+" Information:\nName: "+p.getName()+")\nToken:"
				+ ""+p.getToken().token+"\nLocation: "+p.getLocation());
		
		cards = p.getHand();
		
		}
		
		panel.setLayout(new CardLayout());
		
		slots = new ArrayList<JButton>();		
		slots.add(c1);
		slots.add(c2);
		slots.add(c3);
		slots.add(c4);
		slots.add(c5);
		slots.add(c6);
		
		int n = 6;
		if(p != null)
		n = p.getHand().size();
		
		
		for(int i = 0; i < n; i++){
			JButton b = slots.get(i);
			b = new JButton();
			
			int x = (500 + 50 * i);
			int y = (500 + 50 * i);
			
			
			
			Border border = (BorderFactory.createLoweredBevelBorder());
			b.setBorder(border);
		}
		
		
		
	}
	
	public void drawCard(Graphics g, String path){
		
		File f = new File(path);
		try {
			Image image = ImageIO.read(f);
			//g.drawImage(image, x, y, observer)
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}
	
	
	
	
	
	

}
