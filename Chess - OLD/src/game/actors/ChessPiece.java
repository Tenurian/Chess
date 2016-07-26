package game.actors;

import game.world.Board;
import game.world.Board.player;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
/**
 * The Basic actor class for all the Chess Pieces.\nContains all the images and has an owner of type player.\nNo Constructor; must create a child class to access the methods
 * @author Tenurian
 * @version 1.0.0
 */
public class ChessPiece extends Actor {
	final static GreenfootImage 
	W_Ki = new GreenfootImage("images/WhiteKing.png"), 
	W_Qu = new GreenfootImage("images/WhiteQueen.png"), 
	W_Bi = new GreenfootImage("images/WhiteBishop.png"), 
	W_Kn = new GreenfootImage("images/WhiteKnight.png"), 
	W_Ro = new GreenfootImage("images/WhiteRook.png"), 
	W_Pn = new GreenfootImage("images/WhitePawn.png"),
	B_Ki = new GreenfootImage("images/BlackKing.png"), 
	B_Qu = new GreenfootImage("images/BlackQueen.png"), 
	B_Bi = new GreenfootImage("images/BlackBishop.png"), 
	B_Kn = new GreenfootImage("images/BlackKnight.png"), 
	B_Ro = new GreenfootImage("images/BlackRook.png"), 
	B_Pn = new GreenfootImage("images/BlackPawn.png");

	private player owner;
	private int xpos = 0,ypos = 0;
	private boolean FIRST_ACT;

	/**
	 * Used to determine the owner of the ChessPiece
	 * @return owner The Owner of this ChessPiece, type player
	 */
	public player getOwner() {
		return owner;
	}

	/**
	 * Used by the child classes to set the owner
	 * @param owner The Owner player
	 */
	public void setOwner(player owner) {
		this.owner = owner;
	}

	/**
	 * Used by child class to generate the correct image based on the owner player and the White/Black images passed in.
	 * @param w The White GreenfootImage of this piece
	 * @param b The Black GreenfootImage of this piece
	 */
	public void genImage(GreenfootImage w, GreenfootImage b){
		if(this.owner == player.WHITE){
			this.setImage(w);
		}else if(this.owner == player.BLACK){
			this.setImage(b);
		}	
	}
	/**
	 * Overridden by child classes. Used to highlight the Tiles that this chess piece can legally move to
	 */
	public void highlightNodes(){

	}
	
	public void firstAct(){
		FIRST_ACT = true;
	}
	
	public void posUpdate(int x, int y){
		this.xpos = x;
		this.ypos = y;
	}
	
	public void kingCheck(){
		
	}
	
	public void pawnUpdate(){
		
	}
	

	/**
	 * Used to determine if/when the user clicks on this ChessPiece, then calls highlightNodes()
	 */
	public void act(){
		
		cheatCheck();
		
		if(Greenfoot.mouseClicked(this)){
			if(Board.gm.getClicked() == null){
				if(Board.gm.getCurrentPlayer() == this.getOwner()){
					Board.gm.setClicked(this);
					pieceAccessableToggle();
				}
			}else{
				if(Board.gm.getClicked().getOwner() != this.getOwner()){
					if(Board.gm.getClicked().pieceLogic(this.getX(), this.getY())){
						Board.gm.moveClicked(getX(), getY());
						this.getWorld().removeObject(this);
					}
				}else{
					Board.gm.setClicked(this);
				}
			}
		}
		kingCheck();
	}
	
	protected void attacking(){
		Board.gm.setAttacker(this);
	}

	private void cheatCheck() {
		if(this.FIRST_ACT){
			posUpdate(this.getX(), this.getY());
			this.FIRST_ACT = false;
		}
		
		if(this.xpos != this.getX() || this.ypos != this.getY()){
			this.setLocation(xpos, ypos);
		}
	}
	
	/**
	 * to be overridden by the child classes
	 */
	public void pieceAccessableToggle(){}
	
	/**
	 * to be overridden by the child classes
	 */
	public void kingInDangerToggle(){}

	/**
	 * to be overridden by the child classes
	 * @param x The x location of the tile that needs to be tested
	 * @param y The y location of the tile that needs to be tested
	 */
	public boolean pieceLogic(int x, int y) {
		System.out.println("ChessPiece piecelogic ran");
		return true;
	}
}