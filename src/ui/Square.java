package ui;

import java.awt.Point;

import items.Piece;

public class Square {

	private Piece piece;
	public boolean occupied = false;
	public Point point;
	
	public Square(Point p){
		this.point = p;
	}
	 
	
	
	public boolean hasPiece(){
		return piece != null;
	}
	
	public void setPiece(Piece p){
		this.piece = p;
		occupied = hasPiece(); 
	}
	
	public Piece getPiece(){
		return this.piece;
	}
	
	
	
	
	
	
	
	
	
	
}
