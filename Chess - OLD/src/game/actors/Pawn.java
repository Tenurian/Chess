package game.actors;

import game.world.Board;
import game.world.Board.player;

public class Pawn extends ChessPiece {
	private boolean firstturn;
	public Pawn(player owner){
		firstturn = true;
		this.setOwner(owner);
		this.genImage(W_Pn, B_Pn);
		this.firstAct();
	}
	
	@Override
	public void pawnUpdate(){
		this.firstturn = false;
	}
	
	@Override
	public void pieceAccessableToggle(){
		if(getOwner() == player.WHITE){
			if(Board.gm.tilegrid[this.getY()-1][this.getX()].getOneIntersecting(ChessPiece.class)==null){
				Board.gm.tilegrid[this.getY()-1][this.getX()].highlight(true);
			}
			if(firstturn){
				if(Board.gm.tilegrid[this.getY()-2][this.getX()].getOneIntersecting(ChessPiece.class)==null){
					Board.gm.tilegrid[this.getY()-2][this.getX()].highlight(true);
				}
			}
		}else if(getOwner() == player.BLACK){
			if(Board.gm.tilegrid[this.getY()+1][this.getX()].getOneIntersecting(ChessPiece.class)==null){
				Board.gm.tilegrid[this.getY()+1][this.getX()].highlight(true);
			}
			if(firstturn){
				if(Board.gm.tilegrid[this.getY()+2][this.getX()].getOneIntersecting(ChessPiece.class)==null){
					Board.gm.tilegrid[this.getY()+2][this.getX()].highlight(true);
				}
			}
		}
	}

	@Override
	public boolean pieceLogic(int x, int y){
		boolean out = false;

		if(x == this.getX()){
			if(getOwner() == player.BLACK){
				if(firstturn){
					out = ((Board.gm.tilegrid[y][x].getOneIntersecting(ChessPiece.class))==null)  && ((y==(this.getY()+2)) || (y==(this.getY()+1)));
				}else{
					if(this.getY() < Board.HEIGHT){
						out = ((Board.gm.tilegrid[y][x].getOneIntersecting(ChessPiece.class))==null)  && (y==(this.getY()+1));
					}
				}

			}else if(getOwner() == player.WHITE){
				if(firstturn){
					out = ((Board.gm.tilegrid[y][x].getOneIntersecting(ChessPiece.class))==null)  && ((y==(this.getY()-2)) || (y==(this.getY()-1)));
				}else{
					if(this.getY() > 0){
						out = ((Board.gm.tilegrid[y][x].getOneIntersecting(ChessPiece.class))==null)  && (y==(this.getY()-1));
					}
				}
			}
		}else{
			if(getOwner() == player.BLACK){
				if(this.getY() < Board.HEIGHT){
					out = ((Board.gm.tilegrid[y][x].getOneIntersecting(ChessPiece.class))!=null)  && (y==(this.getY()+1)) && ((x==(this.getX()+1))||(x==(this.getX()-1))) ;
				}
			}else if(getOwner() == player.WHITE){
				if(this.getY() > 0){
					out = ((Board.gm.tilegrid[y][x].getOneIntersecting(ChessPiece.class))!=null)  && (y==(this.getY()-1)) && ((x==(this.getX()+1))||(x==(this.getX()-1))) ;
				}
			}
		}
		return out;
	}
	
	@Override
	public void kingInDangerToggle(){
		try{
			
			if(getX()>0){
				if(getOwner() == player.WHITE){
					if(getY() > 0){
						Board.gm.tilegrid[getY()-1][getX()-1].setKingAccessable(getOwner());
						Board.gm.RedTiles.add(Board.gm.tilegrid[getY()-1][getX()-1]);
						if(Board.gm.tilegrid[getY()-1][getX()-1].getOneIntersecting(King.class)!=null){
							if(((King)Board.gm.tilegrid[getY()-1][getX()-1].getOneIntersecting(King.class)).getOwner() != this.getOwner()){
								Board.gm.tilegrid[getY()-1][getX()-1].highlight(false);
							}
						}
					}
				}else if(getOwner() == player.BLACK){
					if(getY() < Board.HEIGHT-1){
						Board.gm.tilegrid[getY()+1][getX()-1].setKingAccessable(getOwner());
						Board.gm.RedTiles.add(Board.gm.tilegrid[getY()+1][getX()-1]);
						if(Board.gm.tilegrid[getY()+1][getX()-1].getOneIntersecting(King.class)!=null){
							if(((King)Board.gm.tilegrid[getY()+1][getX()-1].getOneIntersecting(King.class)).getOwner() != this.getOwner()){
								Board.gm.tilegrid[getY()+1][getX()-1].highlight(false);
							}
						}
					}
				}
			}
			if(getX()<Board.WIDTH-1){
				if(getOwner() == player.WHITE){
					if(getY() > 0){
						Board.gm.tilegrid[getY()-1][getX()+1].setKingAccessable(getOwner());
						Board.gm.RedTiles.add(Board.gm.tilegrid[getY()-1][getX()+1]);
						if(Board.gm.tilegrid[getY()-1][getX()+1].getOneIntersecting(King.class)!=null){
							if(((King)Board.gm.tilegrid[getY()-1][getX()+1].getOneIntersecting(King.class)).getOwner() != this.getOwner()){
								Board.gm.tilegrid[getY()-1][getX()+1].highlight(false);
							}
						}
					}
				}else if(getOwner() == player.BLACK){
					if(getY() < Board.HEIGHT-1){
						Board.gm.tilegrid[getY()+1][getX()+1].setKingAccessable(getOwner());
						Board.gm.RedTiles.add(Board.gm.tilegrid[getY()+1][getX()+1]);
						if(Board.gm.tilegrid[getY()+1][getX()+1].getOneIntersecting(King.class)!=null){
							if(((King)Board.gm.tilegrid[getY()+1][getX()+1].getOneIntersecting(King.class)).getOwner() != this.getOwner()){
								Board.gm.tilegrid[getY()+1][getX()+1].highlight(false);
							}
						}
					}
				}
			}
			
			
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
}
