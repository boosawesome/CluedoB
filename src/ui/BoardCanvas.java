package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Board;
import items.Piece;

public class BoardCanvas extends Canvas{
	
	private final int squareSize = 25;
	public final int numSquare = 25;
	Square selected = null;
	Square[][] squares;
	String[][] map = Board.map;
	
	public BoardCanvas(){
		this.setBackground(Color.BLACK);
		squares = new Square[numSquare][numSquare];
		
		for(int x = 0; x < 24; x++){
			for(int y = 0; y < 25; y++){
				if(map[x][y].equals("x") || map[x][y].equals("s")){
					squares[x][y] = new Square(new Point(x,y));
				}
			}
		}
		
	}
	
	public void paint(Graphics g){
		int width = 24*25;
		int height = 25*25;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 5000, 5000);
		
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, width, height);
		drawSquares(g);
		drawRooms(g);
		drawNames(g);
		
		if(selected != null){
			drawSelected(g, selected);
		}
	}
	
	public void drawNames(Graphics g){
		g.setColor(Color.BLACK);
		
		g.drawString("Kitchen", 2*25, 4*25);
		g.drawString("Ball Room", 11*25, 4*25);
		g.drawString("Dining Room", 2*25, 13*25);
		g.drawString("Conservatory", 20*25, 3*25);
		g.drawString("Billiard Room", 20*25, 11*25);
		g.drawString("Library", 20*25, 17*25);
		g.drawString("Lounge", 2*25, 22*25);
		g.drawString("Study", 19*25, 23*25);
		g.drawString("Hall", 11*25, 21*25);
		
	}
	
	public void setSelected(Square s){
		this.selected = s;
	}
	
	public void drawSelected(Graphics g, Square s){
		g.setColor(Color.MAGENTA);
		Point p = s.point;
		g.drawRect(p.x*25, p.y*25, squareSize, squareSize);
	}
	
	public void drawSquares(Graphics g){
		g.setColor(Color.BLACK);
		
		for(int x = 0; x < 24; x++){
			for(int y = 0; y < 25; y++){
				g.drawRect(x*25, y*25, squareSize, squareSize);
			}
		}
	}
	
	public void drawRooms(Graphics g){
		g.setColor(Color.WHITE);
		
		//Kitchen
		for(int x = 0; x <= 5; x++){
			for(int y = 1; y <= 6 ; y++){
				g.fillRect(x*25, y*25, squareSize, squareSize);
			}
		}
		//Ball Room
		for(int x = 8; x <= 15; x++){
			for(int y = 2; y <= 7; y++){
				g.fillRect(x*25, y*25, squareSize, squareSize);
			}
		}
		for(int x = 10; x <= 13; x++){
			g.fillRect(x*25, 1*25, squareSize, squareSize);
		}
		//Conservatory
		for(int x = 19; x <= 23; x++){
			for(int y = 1; y <= 4; y++){
				g.fillRect(x*25, y*25, squareSize, squareSize);
			}
		}
		for(int x = 20; x <= 23; x++){
				g.fillRect(x*25, 5*25, squareSize, squareSize);			
		}
		//Dining Room
		for(int x = 0; x <= 7; x++){
			for(int y = 11; y <= 16; y++){
				g.fillRect(x*25, y*25, squareSize, squareSize);
			}
		}
		for(int x = 0; x <= 5; x++){
			g.fillRect(x*25, 10*25, squareSize, squareSize);
		}
		//Billiard Room
		for(int x = 18; x <= 23; x++){
			for(int y = 8; y <= 12; y++){
				g.fillRect(x*25, y*25, squareSize, squareSize);
			}
		}
		//Library
		for(int x = 17; x <= 23; x++){
			for(int y = 15; y <= 17; y++){
				g.fillRect(x*25, y*25, squareSize, squareSize);
			}
		}
		for(int x = 18; x <= 23; x++){
			g.fillRect(x*25, 14*25, squareSize, squareSize);
		}
		for(int x = 18; x <= 23; x++){
			g.fillRect(x*25, 18*25, squareSize, squareSize);
		}
		//Lounge
		for(int x = 0; x <= 6; x++){
			for(int y = 19; y <= 24; y++){
				g.fillRect(x*25, y*25, squareSize, squareSize);
			}
		}
		//Hall
		for(int x = 9; x <= 14; x++){
			for(int y = 18; y <= 24; y++){
				g.fillRect(x*25, y*25, squareSize, squareSize);
			}
		}
		//Study
		for(int x = 17; x <= 23; x++){
			for(int y = 21; y <= 24; y++){
				g.fillRect(x*25, y*25, squareSize, squareSize);
			}
		}
		//Cellar
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/images/cluedo.png"));
		} catch (IOException e) {
		}
		g.drawImage(img, 10*25, 10*25, 5*25, 7*25, null);
		
		//draws walls
		g.setColor(Color.GREEN);
		g.fillRect(0, 7*25, squareSize, squareSize);
		g.fillRect(6*25, 1*25, squareSize, squareSize);
		g.fillRect(18*25, 1*25, squareSize, squareSize);
		g.fillRect(23*25, 5*25, squareSize, squareSize);
		g.fillRect(0,  9*25, squareSize, squareSize);
		g.fillRect(23*25, 8*25, squareSize, squareSize);
		g.fillRect(0, 16*25, squareSize, squareSize);
		g.fillRect(0, 18*25, squareSize, squareSize);
		g.fillRect(23*25, 13*25, squareSize, squareSize);
		g.fillRect(23*25, 14*25, squareSize, squareSize);
		g.fillRect(23*25, 18*25, squareSize, squareSize);
		g.fillRect(23*25, 20*25, squareSize, squareSize);
		g.fillRect(6*25, 24*25, squareSize, squareSize);
		g.fillRect(8*25, 24*25, squareSize, squareSize);
		g.fillRect(15*25, 24*25, squareSize, squareSize);
		for(int x = 0; x <= 23; x++){
			g.fillRect(x*25, 0, squareSize, squareSize);
		}
		
		//draws stairwells
		g.setColor(Color.ORANGE);
		g.fillRect(5*25, 1*25, squareSize, squareSize);
		g.fillRect(0,  19*25, squareSize, squareSize);
		g.fillRect(22*25, 5*25, squareSize, squareSize);
		g.fillRect(23*25, 21*25, squareSize, squareSize);
		
		
		
		//draws doors
		g.setColor(new Color(102, 51, 0));
		g.fillRect(4*25, 6*25, squareSize, squareSize);
		
		g.fillRect(6*25, 19*25, squareSize, squareSize); 
		
		g.fillRect(19*25, 4*25, squareSize, squareSize);
		
		g.fillRect(17*25, 21*25, squareSize, squareSize);
		
		g.fillRect(7*25, 12*25, squareSize, squareSize); 
		g.fillRect(6*25, 16*25, squareSize, squareSize); 
		
		g.fillRect(18*25, 9*25, squareSize, squareSize); 
		g.fillRect(22*25, 12*25, squareSize, squareSize);
		
		g.fillRect(20*25, 14*25, squareSize, squareSize); 
		g.fillRect(17*25, 16*25, squareSize, squareSize);
		
		g.fillRect(8*25, 5*25, squareSize, squareSize); 
		g.fillRect(9*25, 7*25, squareSize, squareSize); 
		g.fillRect(14*25, 7*25, squareSize, squareSize); 
		g.fillRect(15*25, 5*25, squareSize, squareSize);
		
		g.fillRect(11*25, 18*25, squareSize, squareSize); 
		g.fillRect(12*25, 18*25, squareSize, squareSize); 
		g.fillRect(14*25, 20*25, squareSize, squareSize); 
	}
	
}