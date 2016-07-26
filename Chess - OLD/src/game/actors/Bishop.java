package game.actors;

import game.world.Board;
import game.world.Board.player;

public class Bishop extends ChessPiece {
	public Bishop(player owner){
		this.setOwner(owner);
		this.genImage(W_Bi,B_Bi);
		this.firstAct();
	}

	@Override
	public boolean pieceLogic(int x, int y){
		boolean out = false;

		if(( Math.abs(x-this.getX()) == Math.abs(y-this.getY()) )){
			out = true;
			if(x < this.getX()){ 				//Destination is WEST
				//				System.out.println("Destination is on the left");
				int j = 0;
				if(y > this.getY()){			//Destination is SOUTH
					//					System.out.println("Destination is below");
					j=1;
				}else if(y < this.getY()){		//Destination is NORTH
					j=-1;
				}
				for(int i = -1;i >= x-this.getX(); i--){
					if(this.getOneObjectAtOffset(i, j, ChessPiece.class)!=null){

						if( ((ChessPiece) this.getOneObjectAtOffset(i, j, ChessPiece.class)).getOwner() == this.getOwner()){
							return false;
						}else{
							//Board.gm.moveClicked(this.getX()+i, this.getY()+j);
							this.getWorld().removeObject(this.getOneObjectAtOffset(0,0,ChessPiece.class));
							return true;
						}
					}

					if(j>0){
						j++;
					}else if(j<0){
						j--;
					}
				}
			}else if(x > this.getX()){			//Destination is EAST
				int j = 0;
				if(y > this.getY()){			//Destination is SOUTH
					j=1;
				}else if(y < this.getY()){		//Destination is NORTH
					j=-1;
				}
				for(int i = 1;i <= x-this.getX(); i++){
					if(this.getOneObjectAtOffset(i, j, ChessPiece.class)!=null){

						if( ((ChessPiece) this.getOneObjectAtOffset(i, j, ChessPiece.class)).getOwner() == this.getOwner()){
							return false;
						}else{
							//Board.gm.moveClicked(this.getX()+i, this.getY()+j);
							this.getWorld().removeObject(this.getOneObjectAtOffset(0,0,ChessPiece.class));
							return true;
						}
					}

					if(j>0){
						j++;
					}else if(j<0){
						j--;
					}
				}
			}
		}

		return out;
	}

	@Override
	public void pieceAccessableToggle(){
		boolean NW = true, NE = true, SW = true, SE = true;
		try{

			for(int i = 1; i < Board.HEIGHT; i++){

				if(this.getY()-i >= 0){
					if(this.getX()-i >= 0 && NW){
						if( Board.gm.tilegrid[getY()-i][getX()-i].getOneIntersecting(ChessPiece.class)!=null){
							if(((ChessPiece) Board.gm.tilegrid[getY()-i][getX()-i].getOneIntersecting(ChessPiece.class)).getOwner() != this.getOwner()){
								Board.gm.tilegrid[getY()-i][getX()-i].highlight(true);
							}
							NW = false;
						}else{
							Board.gm.tilegrid[getY()-i][getX()-i].highlight(true);
							Board.gm.tilegrid[getY()-i][getX()-i].setKingAccessable(getOwner());
						}
					}else{
						NW = false;
					}
					if(this.getX()+i <= Board.WIDTH-1 && NE){
						if( Board.gm.tilegrid[getY()-i][getX()+i].getOneIntersecting(ChessPiece.class)!=null){
							if(((ChessPiece) Board.gm.tilegrid[getY()-i][getX()+i].getOneIntersecting(ChessPiece.class)).getOwner() != this.getOwner()){
								Board.gm.tilegrid[getY()-i][getX()+i].highlight(true);
							}
							NE = false;
						}else{
							Board.gm.tilegrid[getY()-i][getX()+i].highlight(true);
							Board.gm.tilegrid[getY()-i][getX()+i].setKingAccessable(getOwner());
						}
					}else{
						NE = false;
					}
				}
				if(this.getY()+i <= Board.HEIGHT-1){
					if(this.getX()-i >= 0 && SW){
						if( Board.gm.tilegrid[getY()+i][getX()-i].getOneIntersecting(ChessPiece.class)!=null){
							if(((ChessPiece) Board.gm.tilegrid[getY()+i][getX()-i].getOneIntersecting(ChessPiece.class)).getOwner() != this.getOwner()){
								Board.gm.tilegrid[getY()+i][getX()-i].highlight(true);
							}
							SW = false;
						}else{
							Board.gm.tilegrid[getY()+i][getX()-i].highlight(true);
							Board.gm.tilegrid[getY()+i][getX()-i].setKingAccessable(getOwner());
						}
					}else{
						SW = false;
					}
					if(this.getX()+i <= Board.WIDTH-1 && SE){
						if( Board.gm.tilegrid[getY()+i][getX()+i].getOneIntersecting(ChessPiece.class)!=null){
							if(((ChessPiece) Board.gm.tilegrid[getY()+i][getX()+i].getOneIntersecting(ChessPiece.class)).getOwner() != this.getOwner()){
								Board.gm.tilegrid[getY()+i][getX()+i].highlight(true);
							}
							SE = false;
						}else{
							Board.gm.tilegrid[getY()+i][getX()+i].highlight(true);
							Board.gm.tilegrid[getY()+i][getX()+i].setKingAccessable(getOwner());
						}
					}else{
						SE = false;
					}
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("ArrayIndexOutOfBounds:BISHOP\n");
			e.printStackTrace();
		}
	}

	@Override
	public void kingInDangerToggle(){
		boolean NW = true, NE = true, SW = true, SE = true;
		try{
			for(int i = 1; i < Board.HEIGHT; i++){
				if(getY()-i >= 0){
					if(getX()-i >= 0 && NW){
						if( Board.gm.tilegrid[getY()-i][getX()-i].getOneIntersecting(ChessPiece.class)!=null){
							if(Board.gm.tilegrid[getY()-i][getX()-i].getOneIntersecting(King.class)!=null){
								if(((King) Board.gm.tilegrid[getY()-i][getX()-i].getOneIntersecting(King.class)).getOwner()!=getOwner()){
									Board.gm.tilegrid[getY()-i][getX()-i].highlight(false);
									Board.gm.tilegrid[getY()-i][getX()-i].setKingAccessable(getOwner());
									Board.gm.RedTiles.add(Board.gm.tilegrid[getY()-i][getX()-i]);
									this.attacking();
								}else{
									NW = false;
								}
							}else{
								Board.gm.tilegrid[getY()-i][getX()-i].setKingAccessable(getOwner());
								Board.gm.RedTiles.add(Board.gm.tilegrid[getY()-i][getX()-i]);
								NW = false;
							}
						}else{
							Board.gm.tilegrid[getY()-i][getX()-i].setKingAccessable(getOwner());
							Board.gm.RedTiles.add(Board.gm.tilegrid[getY()-i][getX()-i]);
						}
					}else{
						NW = false;
					}
					if(getX()+i <= Board.WIDTH-1 && NE){
						if( Board.gm.tilegrid[getY()-i][getX()+i].getOneIntersecting(ChessPiece.class)!=null){
							if(Board.gm.tilegrid[getY()-i][getX()+i].getOneIntersecting(King.class)!=null){
								if(((King) Board.gm.tilegrid[getY()-i][getX()+i].getOneIntersecting(King.class)).getOwner()!=getOwner()){
									Board.gm.tilegrid[getY()-i][getX()+i].highlight(false);
									Board.gm.tilegrid[getY()-i][getX()+i].setKingAccessable(getOwner());
									Board.gm.RedTiles.add(Board.gm.tilegrid[getY()-i][getX()+i]);
									this.attacking();
								}else{
									NE = false;
								}
							}else{
								Board.gm.tilegrid[getY()-i][getX()+i].setKingAccessable(getOwner());
								Board.gm.RedTiles.add(Board.gm.tilegrid[getY()-i][getX()+i]);
								NE = false;
							}
						}else{
							Board.gm.tilegrid[getY()-i][getX()+i].setKingAccessable(getOwner());
							Board.gm.RedTiles.add(Board.gm.tilegrid[getY()-i][getX()+i]);
						}
					}else{
						NE = false;
					}
				}
				if(getY()+i <= Board.HEIGHT-1){
					if(getX()-i >= 0 && SW){
						if( Board.gm.tilegrid[getY()+i][getX()-i].getOneIntersecting(ChessPiece.class)!=null){
							if(Board.gm.tilegrid[getY()+i][getX()-i].getOneIntersecting(King.class)!=null){
								if(((King) Board.gm.tilegrid[getY()+i][getX()-i].getOneIntersecting(King.class)).getOwner()!=getOwner()){
									Board.gm.tilegrid[getY()+i][getX()-i].highlight(false);
									Board.gm.tilegrid[getY()+i][getX()-i].setKingAccessable(getOwner());
									Board.gm.RedTiles.add(Board.gm.tilegrid[getY()+i][getX()-i]);
									this.attacking();
								}else{
									SW = false;
								}
							}else{
								Board.gm.tilegrid[getY()+i][getX()-i].setKingAccessable(getOwner());
								Board.gm.RedTiles.add(Board.gm.tilegrid[getY()+i][getX()-i]);
								SW = false;
							}
						}else{
							Board.gm.tilegrid[getY()+i][getX()-i].setKingAccessable(getOwner());
							Board.gm.RedTiles.add(Board.gm.tilegrid[getY()+i][getX()-i]);
						}
					}else{
						SW = false;
					}
					if(getX()+i <= Board.WIDTH-1 && SE){
						if( Board.gm.tilegrid[getY()+i][getX()+i].getOneIntersecting(ChessPiece.class)!=null){
							if(Board.gm.tilegrid[getY()+i][getX()+i].getOneIntersecting(King.class)!=null){
								if(((King) Board.gm.tilegrid[getY()+i][getX()+i].getOneIntersecting(King.class)).getOwner()!=getOwner()){
									Board.gm.tilegrid[getY()+i][getX()+i].highlight(false);
									Board.gm.tilegrid[getY()+i][getX()+i].setKingAccessable(getOwner());
									Board.gm.RedTiles.add(Board.gm.tilegrid[getY()+i][getX()+i]);
									this.attacking();
								}else{
									SE = false;
								}
							}else{
								Board.gm.tilegrid[getY()+i][getX()+i].setKingAccessable(getOwner());
								Board.gm.RedTiles.add(Board.gm.tilegrid[getY()+i][getX()+i]);
								SE = false;
							}
						}else{
							Board.gm.tilegrid[getY()+i][getX()+i].setKingAccessable(getOwner());
							Board.gm.RedTiles.add(Board.gm.tilegrid[getY()+i][getX()+i]);
						}
					}else{
						SE = false;
					}
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("ArrayIndexOutOfBounds:BISHOP\n\n");
			e.printStackTrace();
		}
	}
}

