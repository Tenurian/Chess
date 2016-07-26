package game.actors;

import game.world.Board;
import game.world.Board.player;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.awt.Color;

public class Tile extends greenfoot.Actor{
	int i, j;
	private GreenfootImage img = new GreenfootImage(Board.CELL_SIZE,Board.CELL_SIZE); 
	private boolean black, whiteKingAccessable, blackKingAccessable;
	public Tile(boolean black, int i, int j){
		whiteKingAccessable = true;
		blackKingAccessable = true;
		this.black = black;
		imgUpdate();
		this.setImage(img);
		this.i = i;
		this.j = j;
	}

	public void imgUpdate() {
		if(this.black){
			img.setColor(new Color(50,50,50));
		}else{
			img.setColor(Color.WHITE);
		}
		img.fill();
	}
	
	@SuppressWarnings("rawtypes")
	public Actor getOneIntersecting(Class cls){
		return this.getOneIntersectingObject(cls);
	}

	public void highlight(ChessPiece c){
		if(this.getOneIntersectingObject(ChessPiece.class)!=null){
			if(this.getOneIntersectingObject(King.class) != null){
				if(((King) this.getOneIntersectingObject(King.class)).getOwner() != c.getOwner()){
					highlight(false);
				}
			}
		}else{
			if(Board.gm.getClicked() == c){
				highlight(true);
			}
		}
	}
	
	public void kingDangerHighlight(ChessPiece c){
		if(this.getOneIntersectingObject(King.class) != null){
			if(((King) this.getOneIntersectingObject(King.class)).getOwner() != c.getOwner()){
				highlight(false);
			}
		}
	}

	public void highlight(boolean b){
		if(!b){
			img.setColor(new Color(255,126,126));
			img.fill();
			this.setImage(img);
		}else{
			img.setColor(new Color(109,255,109));
			img.fill();
			this.setImage(img);
		}
	}

	public void act(){

		if(Board.gm.getClicked() != null){
			if(Board.gm.getClicked().getX() == this.getX() && Board.gm.getClicked().getY() == this.getY()){
				this.highlight(true);
			}else if(!whiteKingAccessable && this.getOneIntersectingObject(King.class)!=null){
				if(((King) this.getOneIntersectingObject(King.class)).getOwner()==player.WHITE)
					this.highlight(false);
			}else if(!blackKingAccessable && this.getOneIntersectingObject(King.class)!=null){
				if(((King) this.getOneIntersectingObject(King.class)).getOwner()==player.BLACK)
					this.highlight(false);
			}
		}

		if(Greenfoot.mouseClicked(this)){
			if(Board.gm.getClicked() != null){
				if(Board.gm.getClicked().pieceLogic(getX(), getY())){
					Board.gm.moveClicked(getX(), getY());
				}
			}
		}

		if(this.getX() != j || this.getY() != i)
			this.setLocation(j, i);
	}

	private boolean isWhiteKingAccessable() {
		return whiteKingAccessable;
	}

	private void setWhiteKingAccessable(boolean whiteKingAccessable) {
		this.whiteKingAccessable = whiteKingAccessable;
	}

	private boolean isBlackKingAccessable() {
		return blackKingAccessable;
	}

	private void setBlackKingAccessable(boolean blackKingAccessable) {
		this.blackKingAccessable = blackKingAccessable;
	}
	
	public void setKingAccessable(player p){
		if(p==null){
			this.setBlackKingAccessable(true);
			this.setWhiteKingAccessable(true);
		}
		
		if(p==player.BLACK){
			this.setWhiteKingAccessable(false);
		}
		if(p==player.WHITE){
			this.setBlackKingAccessable(false);
		}
	}

	public boolean isKingAccessable(player owner) {
		boolean out = false;
		if(this.getOneIntersectingObject(ChessPiece.class)!=null){
			if(((ChessPiece) this.getOneIntersectingObject(ChessPiece.class)).getOwner() == player.BLACK){
				this.setBlackKingAccessable(false);
			}
			if(((ChessPiece) this.getOneIntersectingObject(ChessPiece.class)).getOwner() == player.WHITE){
				this.setWhiteKingAccessable(false);
			}
		}
		if(owner == player.BLACK)
			out = isBlackKingAccessable();
		if(owner == player.WHITE)
			out = isWhiteKingAccessable();
		return out;
	}
}