package game.actors;

import game.world.Board;
import game.world.Board.player;

public class Rook extends ChessPiece {
	public Rook(player owner){
		this.setOwner(owner);
		this.genImage(W_Ro, B_Ro);
		this.firstAct();
	}
	@Override
	public void pieceAccessableToggle(){
		boolean up = true, down = true, left = true, right = true;
		for(int i = 1; i <= Board.WIDTH; i++){
			try{
				if((this.getX()-i) >= 0 && left){
					if(Board.gm.tilegrid[this.getY()][this.getX()-i].getOneIntersecting(ChessPiece.class)!=null){
						if(((ChessPiece) Board.gm.tilegrid[this.getY()][this.getX()-i].getOneIntersecting(ChessPiece.class)).getOwner()!=this.getOwner()){
							Board.gm.tilegrid[this.getY()][this.getX()-i].highlight(true);
						}
						left = false;
					}else{
						if(left){
							Board.gm.tilegrid[this.getY()][this.getX()-i].highlight(true);
							Board.gm.tilegrid[this.getY()][this.getX()-i].setKingAccessable(getOwner());
						}
					}
				}else{
					left = false;
				}
				if((this.getX()+i) <= Board.WIDTH-1 && right){
					if(Board.gm.tilegrid[this.getY()][this.getX()+i].getOneIntersecting(ChessPiece.class)!=null){
						if(((ChessPiece) Board.gm.tilegrid[this.getY()][this.getX()+i].getOneIntersecting(ChessPiece.class)).getOwner()!=this.getOwner()){
							Board.gm.tilegrid[this.getY()][this.getX()+i].highlight(true);
						}
						right = false;
					}else{
						if(right){
							Board.gm.tilegrid[this.getY()][this.getX()+i].highlight(true);
							Board.gm.tilegrid[this.getY()][this.getX()+i].setKingAccessable(getOwner());
						}
					}
				}else{
					right = false;
				}
				if((this.getY()-i) >= 0 && up){
					if(Board.gm.tilegrid[this.getY()-i][this.getX()].getOneIntersecting(ChessPiece.class)!=null){
						if(((ChessPiece) Board.gm.tilegrid[this.getY()-i][this.getX()].getOneIntersecting(ChessPiece.class)).getOwner()!=this.getOwner()){
							Board.gm.tilegrid[this.getY()-i][this.getX()].highlight(true);
						}
						up = false;
					}else{
						if(up){
							Board.gm.tilegrid[this.getY()-i][this.getX()].highlight(true);
							Board.gm.tilegrid[this.getY()-i][this.getX()].setKingAccessable(getOwner());
						}
					}
				}else{
					up = false;
				}
				if((this.getY()+i) <= Board.HEIGHT-1 && down){
					if(Board.gm.tilegrid[this.getY()+i][this.getX()].getOneIntersecting(ChessPiece.class)!=null){
						if(((ChessPiece) Board.gm.tilegrid[this.getY()+i][this.getX()].getOneIntersecting(ChessPiece.class)).getOwner()!=this.getOwner()){
							Board.gm.tilegrid[this.getY()+i][this.getX()].highlight(true);
						}
						down = false;
					}else{
						if(down){
							Board.gm.tilegrid[this.getY()+i][this.getX()].highlight(true);
							Board.gm.tilegrid[this.getY()+i][this.getX()].setKingAccessable(getOwner());
						}
					}
				}else{
					down = false;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("ArrayIndexOutOfBounds");
			}
		}
	}

	@Override
	public boolean pieceLogic(int x, int y){
		boolean out = false;
		if(this.getX() == x || this.getY() == y){
			out = true;

			try{

				if(this.getX() == x){ //moving vertically
					if(this.getY() > y){				//moving NORTH		Towards 0

						for(int i = -1; i >= y-this.getY(); i--){
							if(this.getOneObjectAtOffset(0, i, ChessPiece.class)!=null){
								if( ((ChessPiece) this.getOneObjectAtOffset(0, i, ChessPiece.class)).getOwner() == this.getOwner()){
									return false;
								}else{
									//this.setLocation(this.getX(), this.getY()+i);
									//this.getWorld().removeObject(this.getOneObjectAtOffset(0,0,ChessPiece.class));
									//Board.gm.moveClicked(this.getX(), this.getY());
									return true;
								}
							}
						}

					}else if(this.getY() < y){			//moving SOUTH		Away from 0

						for(int i = 0; i <= y-this.getY(); i++){
							if(this.getOneObjectAtOffset(0, i, ChessPiece.class)!=null){
								if( ((ChessPiece) this.getOneObjectAtOffset(0, i, ChessPiece.class)).getOwner() == this.getOwner()){
									return false;
								}else{
									//this.setLocation(this.getX(), this.getY()+i);
									//this.getWorld().removeObject(this.getOneObjectAtOffset(0,0,ChessPiece.class));
									//Board.gm.moveClicked(this.getX(), this.getY());
									return true;
								}
							}
						}

					}
				}else if(this.getY() == y){//moving horizontally
					if(this.getX() < x){				//moving EAST		Away from 0

						for(int i = 0; i <= x-this.getX(); i++){
							if(this.getOneObjectAtOffset(i, 0, ChessPiece.class)!=null){
								if( ((ChessPiece) this.getOneObjectAtOffset(i, 0, ChessPiece.class)).getOwner() == this.getOwner()){
									return false;
								}else{
									//this.setLocation(this.getX()+i, this.getY());
									//this.getWorld().removeObject(this.getOneObjectAtOffset(0,0,ChessPiece.class));
									//Board.gm.moveClicked(this.getX(), this.getY());
									return true;
								}
							}
						}

					}else if(this.getX() > x){			//moving WEST		Towards 0

						for(int i = 0; i >= x-this.getX(); i--){
							if(this.getOneObjectAtOffset(i, 0, ChessPiece.class)!=null){
								if( ((ChessPiece) this.getOneObjectAtOffset(i, 0, ChessPiece.class)).getOwner() == this.getOwner()){
									return false;
								}else{
									//this.setLocation(this.getX()+i, this.getY());
									//this.getWorld().removeObject(this.getOneObjectAtOffset(0,0,ChessPiece.class));
									//Board.gm.moveClicked(this.getX(), this.getY());
									return true;
								}
							}
						}

					}
				}
			} catch(NullPointerException e){
				
			}
		}

		return out;
	}

	@Override
	public void kingInDangerToggle(){
		boolean up2 = true, down2 = true, left2 = true, right2 = true;
		try{
			for(int i = 1; i <= Board.WIDTH; i++){
				if((this.getX()-i) >= 0 && left2){
					if(Board.gm.tilegrid[this.getY()][this.getX()-i].getOneIntersecting(ChessPiece.class)!=null){
						if(Board.gm.tilegrid[this.getY()][this.getX()-i].getOneIntersecting(King.class)!=null){
							if(((King) Board.gm.tilegrid[this.getY()][this.getX()-i].getOneIntersecting(King.class)).getOwner()!=getOwner()){
								Board.gm.tilegrid[this.getY()][this.getX()-i].setKingAccessable(getOwner());
								Board.gm.tilegrid[this.getY()][this.getX()-i].highlight(false);
								Board.gm.RedTiles.add(Board.gm.tilegrid[this.getY()][this.getX()-i]);
								this.attacking();
							}else{
								left2 = false;
							}
						}else{
							Board.gm.tilegrid[this.getY()][this.getX()-i].setKingAccessable(getOwner());
							Board.gm.RedTiles.add(Board.gm.tilegrid[this.getY()][this.getX()-i]);
							left2 = false;
						}
					}else{
						Board.gm.tilegrid[this.getY()][this.getX()-i].setKingAccessable(getOwner());
						Board.gm.RedTiles.add(Board.gm.tilegrid[this.getY()][this.getX()-i]);
					}
				}else{
					left2 = false;
				}

				if((this.getX()+i) <= Board.WIDTH-1 && right2){
					if(Board.gm.tilegrid[this.getY()][this.getX()+i].getOneIntersecting(ChessPiece.class)!=null){
						if(Board.gm.tilegrid[this.getY()][this.getX()+i].getOneIntersecting(King.class)!=null){
							if(((King) Board.gm.tilegrid[this.getY()][this.getX()+i].getOneIntersecting(King.class)).getOwner()!=getOwner()){
								Board.gm.tilegrid[this.getY()][this.getX()+i].setKingAccessable(getOwner());
								Board.gm.tilegrid[this.getY()][this.getX()+i].highlight(false);
								Board.gm.RedTiles.add(Board.gm.tilegrid[this.getY()][this.getX()+i]);
								this.attacking();
							}else{
								right2 = false;
							}
						}else{
							Board.gm.tilegrid[this.getY()][this.getX()+i].setKingAccessable(getOwner());
							Board.gm.RedTiles.add(Board.gm.tilegrid[this.getY()][this.getX()+i]);
							right2 = false;
						}
					}else{
						Board.gm.tilegrid[this.getY()][this.getX()+i].setKingAccessable(getOwner());
						Board.gm.RedTiles.add(Board.gm.tilegrid[this.getY()][this.getX()+i]);
					}
				}else{
					right2 = false;
				}
				if((this.getY()-i) >= 0 && up2){
					if(Board.gm.tilegrid[this.getY()-i][this.getX()].getOneIntersecting(ChessPiece.class)!=null){
						if(Board.gm.tilegrid[this.getY()-i][this.getX()].getOneIntersecting(King.class)!=null){
							if(((King) Board.gm.tilegrid[this.getY()-i][this.getX()].getOneIntersecting(King.class)).getOwner()!=getOwner()){
								Board.gm.tilegrid[this.getY()-i][this.getX()].setKingAccessable(getOwner());
								Board.gm.tilegrid[this.getY()-i][this.getX()].highlight(false);
								Board.gm.RedTiles.add(Board.gm.tilegrid[this.getY()-i][this.getX()]);
								this.attacking();
							}else{
								up2 = false;
							}
						}else{
							Board.gm.tilegrid[this.getY()-i][this.getX()].setKingAccessable(getOwner());
							Board.gm.RedTiles.add(Board.gm.tilegrid[this.getY()-i][this.getX()]);
							up2 = false;
						}
					}else{
						Board.gm.tilegrid[this.getY()-i][this.getX()].setKingAccessable(getOwner());
						Board.gm.RedTiles.add(Board.gm.tilegrid[this.getY()-i][this.getX()]);
					}
				}else{
					up2 = false;
				}
				if((this.getY()+i) <= Board.HEIGHT-1 && down2){
					if(Board.gm.tilegrid[this.getY()+i][this.getX()].getOneIntersecting(ChessPiece.class)!= null){
						if(Board.gm.tilegrid[this.getY()+i][this.getX()].getOneIntersecting(King.class)!=null){
							if(((King) Board.gm.tilegrid[this.getY()+i][this.getX()].getOneIntersecting(King.class)).getOwner()!=getOwner()){
								Board.gm.tilegrid[this.getY()+i][this.getX()].setKingAccessable(getOwner());
								Board.gm.tilegrid[this.getY()+i][this.getX()].highlight(false);
								Board.gm.RedTiles.add(Board.gm.tilegrid[this.getY()+i][this.getX()]);
								this.attacking();
							}else{
								down2 = false;
							}
						}else{
							Board.gm.tilegrid[this.getY()+i][this.getX()].setKingAccessable(getOwner());
							Board.gm.RedTiles.add(Board.gm.tilegrid[this.getY()+i][this.getX()]);
							down2 = false;
						}
					}else{
						Board.gm.tilegrid[this.getY()+i][this.getX()].setKingAccessable(getOwner());
						Board.gm.RedTiles.add(Board.gm.tilegrid[this.getY()+i][this.getX()]);
					}
				}else{
					down2 = false;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
}
