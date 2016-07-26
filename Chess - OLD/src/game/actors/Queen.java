package game.actors;

import game.world.Board;
import game.world.Board.player;

public class Queen extends ChessPiece {
	public Queen(player owner){
		this.setOwner(owner);
		this.genImage(W_Qu, B_Qu);
		this.firstAct();
	}

	private boolean diagonalCheck(int x, int y){
		boolean out = true;


		out = true;
		if(x < getX()){ 				//Destination is WEST
			int j = 0;
			if(y > getY()){			//Destination is SOUTH
				j=1;
			}else if(y < getY()){		//Destination is NORTH
				j=-1;
			}
			for(int i = -1;i >= x-getX(); i--){
				if(getOneObjectAtOffset(i, j, ChessPiece.class)!=null){

					if( ((ChessPiece) getOneObjectAtOffset(i, j, ChessPiece.class)).getOwner() == getOwner()){
						return false;
					}else{
//						Board.gm.moveClicked(getX()+i, getY()+j);
//						getWorld().removeObject(getOneObjectAtOffset(0,0,ChessPiece.class));
						return true;
					}
				}

				if(j>0){
					j++;
				}else if(j<0){
					j--;
				}
			}
		}else if(x > getX()){			//Destination is EAST
			int j = 0;
			if(y > getY()){			//Destination is SOUTH
				j=1;
			}else if(y < getY()){		//Destination is NORTH
				j=-1;
			}
			for(int i = 1;i <= x-getX(); i++){
				if(getOneObjectAtOffset(i, j, ChessPiece.class)!=null){

					if( ((ChessPiece) getOneObjectAtOffset(i, j, ChessPiece.class)).getOwner() == getOwner()){
						return false;
					}else{
//						Board.gm.moveClicked(getX()+i, getY()+j);
//						getWorld().removeObject(getOneObjectAtOffset(0,0,ChessPiece.class));
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

		return out;
	}

	private boolean straightCheck(int x, int y) {
		boolean out = false;
		if(getX() == x || getY() == y){
			out = true;

			if(getX() == x){ //moving vertically
				if(getY() > y){				//moving NORTH		Towards 0

					for(int i = -1; i >= y-getY(); i--){
						if(getOneObjectAtOffset(0, i, ChessPiece.class)!=null){
							if( ((ChessPiece) getOneObjectAtOffset(0, i, ChessPiece.class)).getOwner() == getOwner()){
								return false;
							}else{
//								this.setLocation(getX(), getY()+i);
//								getWorld().removeObject(getOneObjectAtOffset(0,0,ChessPiece.class));
//								Board.gm.moveClicked(getX(), getY());
								return true;
							}
						}
					}

				}else if(getY() < y){			//moving SOUTH		Away from 0

					for(int i = 0; i <= y-getY(); i++){
						if(getOneObjectAtOffset(0, i, ChessPiece.class)!=null){
							if( ((ChessPiece) getOneObjectAtOffset(0, i, ChessPiece.class)).getOwner() == getOwner()){
								return false;
							}else{
//								this.setLocation(getX(), getY()+i);
//								getWorld().removeObject(getOneObjectAtOffset(0,0,ChessPiece.class));
//								Board.gm.moveClicked(getX(), getY());
								return true;
							}
						}
					}

				}
			}else if(getY() == y){//moving horizontally
				if(getX() < x){				//moving EAST		Away from 0

					for(int i = 0; i <= x-getX(); i++){
						if(getOneObjectAtOffset(i, 0, ChessPiece.class)!=null){
							if( ((ChessPiece) getOneObjectAtOffset(i, 0, ChessPiece.class)).getOwner() == getOwner()){
								return false;
							}else{
//								this.setLocation(getX()+i, getY());
//								getWorld().removeObject(getOneObjectAtOffset(0,0,ChessPiece.class));
//								Board.gm.moveClicked(getX(), getY());
								return true;
							}
						}
					}

				}else if(getX() > x){			//moving WEST		Towards 0

					for(int i = 0; i >= x-getX(); i--){
						if(getOneObjectAtOffset(i, 0, ChessPiece.class)!=null){
							if( ((ChessPiece) getOneObjectAtOffset(i, 0, ChessPiece.class)).getOwner() == getOwner()){
								return false;
							}else{
//								this.setLocation(getX()+i, getY());
//								getWorld().removeObject(getOneObjectAtOffset(0,0,ChessPiece.class));
//								Board.gm.moveClicked(getX(), getY());
								return true;
							}
						}
					}

				}
			}
		}

		return out;
	}

	@Override
	public boolean pieceLogic(int x, int y){
		boolean out = false;

		if(( Math.abs(x-getX()) == Math.abs(y-getY()) )){
			return diagonalCheck(x,y);
		}else if( getX() == x  ||  getY() == y){
			return straightCheck(x,y);
		}
		return out;
	}

	@Override
	public void pieceAccessableToggle(){
		straightToggle();
		diagonalToggle();
	}

	private void diagonalToggle(){
		boolean NW = true, NE = true, SW = true, SE = true;
		try{

			for(int i = 1; i < Board.HEIGHT; i++){

				if(getY()-i >= 0){
					if(getX()-i >= 0 && NW){
						if( Board.gm.tilegrid[getY()-i][getX()-i].getOneIntersecting(ChessPiece.class)!=null){
							if(((ChessPiece) Board.gm.tilegrid[getY()-i][getX()-i].getOneIntersecting(ChessPiece.class)).getOwner() != getOwner()){
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
					if(getX()+i <= Board.WIDTH-1 && NE){
						if( Board.gm.tilegrid[getY()-i][getX()+i].getOneIntersecting(ChessPiece.class)!=null){
							if(((ChessPiece) Board.gm.tilegrid[getY()-i][getX()+i].getOneIntersecting(ChessPiece.class)).getOwner() != getOwner()){
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
				if(getY()+i <= Board.HEIGHT-1){
					if(getX()-i >= 0 && SW){
						if( Board.gm.tilegrid[getY()+i][getX()-i].getOneIntersecting(ChessPiece.class)!=null){
							if(((ChessPiece) Board.gm.tilegrid[getY()+i][getX()-i].getOneIntersecting(ChessPiece.class)).getOwner() != getOwner()){
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
					if(getX()+i <= Board.WIDTH-1 && SE){
						if( Board.gm.tilegrid[getY()+i][getX()+i].getOneIntersecting(ChessPiece.class)!=null){
							if(((ChessPiece) Board.gm.tilegrid[getY()+i][getX()+i].getOneIntersecting(ChessPiece.class)).getOwner() != getOwner()){
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

	private void straightToggle(){
		boolean up = true, down = true, left = true, right = true;
		for(int i = 1; i <= Board.WIDTH; i++){
			try{
				if((getX()-i) >= 0 && left){
					if(Board.gm.tilegrid[getY()][getX()-i].getOneIntersecting(ChessPiece.class)!=null){
						if(((ChessPiece) Board.gm.tilegrid[getY()][getX()-i].getOneIntersecting(ChessPiece.class)).getOwner()!=getOwner()){
							Board.gm.tilegrid[getY()][getX()-i].highlight(true);
						}
						left = false;
					}else{
						if(left){
							Board.gm.tilegrid[getY()][getX()-i].highlight(true);
							Board.gm.tilegrid[getY()][getX()-i].setKingAccessable(getOwner());
						}
					}
				}else{
					left = false;
				}
				if((getX()+i) <= Board.WIDTH-1 && right){
					if(Board.gm.tilegrid[getY()][getX()+i].getOneIntersecting(ChessPiece.class)!=null){
						if(((ChessPiece) Board.gm.tilegrid[getY()][getX()+i].getOneIntersecting(ChessPiece.class)).getOwner()!=getOwner()){
							Board.gm.tilegrid[getY()][getX()+i].highlight(true);
						}
						right = false;
					}else{
						if(right){
							Board.gm.tilegrid[getY()][getX()+i].highlight(true);
							Board.gm.tilegrid[getY()][getX()+i].setKingAccessable(getOwner());
						}
					}
				}else{
					right = false;
				}
				if((getY()-i) >= 0 && up){
					if(Board.gm.tilegrid[getY()-i][getX()].getOneIntersecting(ChessPiece.class)!=null){
						if(((ChessPiece) Board.gm.tilegrid[getY()-i][getX()].getOneIntersecting(ChessPiece.class)).getOwner()!=getOwner()){
							Board.gm.tilegrid[getY()-i][getX()].highlight(true);
						}
						up = false;
					}else{
						if(up){
							Board.gm.tilegrid[getY()-i][getX()].highlight(true);
							Board.gm.tilegrid[getY()-i][getX()].setKingAccessable(getOwner());
						}
					}
				}else{
					up = false;
				}
				if((getY()+i) <= Board.HEIGHT-1 && down){
					if(Board.gm.tilegrid[getY()+i][getX()].getOneIntersecting(ChessPiece.class)!=null){
						if(((ChessPiece) Board.gm.tilegrid[getY()+i][getX()].getOneIntersecting(ChessPiece.class)).getOwner()!=getOwner()){
							Board.gm.tilegrid[getY()+i][getX()].highlight(true);
						}
						down = false;
					}else{
						if(down){
							Board.gm.tilegrid[getY()+i][getX()].highlight(true);
							Board.gm.tilegrid[getY()+i][getX()].setKingAccessable(getOwner());
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
	public void kingInDangerToggle(){
		boolean up = true, down = true, left = true, right = true, NW = true, NE = true, SW = true, SE = true;
		for(int i = 1; i <= Board.WIDTH; i++){
			try{
				if((getX()-i) >= 0 && left){
					if(Board.gm.tilegrid[getY()][getX()-i].getOneIntersecting(ChessPiece.class)!=null){
						if(Board.gm.tilegrid[getY()][getX()-i].getOneIntersecting(King.class)!=null){
							if(((King) Board.gm.tilegrid[getY()][getX()-i].getOneIntersecting(King.class)).getOwner()!=getOwner()){
								Board.gm.tilegrid[getY()][getX()-i].highlight(false);
								Board.gm.tilegrid[getY()][getX()-i].setKingAccessable(getOwner());
								Board.gm.RedTiles.add(Board.gm.tilegrid[getY()][getX()-i]);
								this.attacking();
							}else{
								left = false;
							}
						}else{
							Board.gm.tilegrid[getY()][getX()-i].setKingAccessable(getOwner());
							Board.gm.RedTiles.add(Board.gm.tilegrid[getY()][getX()-i]);
							left = false;
						}
					}else{
						Board.gm.tilegrid[getY()][getX()-i].setKingAccessable(getOwner());
						Board.gm.RedTiles.add(Board.gm.tilegrid[getY()][getX()-i]);
					}
				}else{
					left = false;
				}
				if((getX()+i) <= Board.WIDTH-1 && right){
					if(Board.gm.tilegrid[getY()][getX()+i].getOneIntersecting(ChessPiece.class)!=null){
						if(Board.gm.tilegrid[getY()][getX()+i].getOneIntersecting(King.class)!=null){
							if(((King) Board.gm.tilegrid[getY()][getX()+i].getOneIntersecting(King.class)).getOwner()!=getOwner()){
								Board.gm.tilegrid[getY()][getX()+i].highlight(false);
								Board.gm.tilegrid[getY()][getX()+i].setKingAccessable(getOwner());
								Board.gm.RedTiles.add(Board.gm.tilegrid[getY()][getX()+i]);
								this.attacking();
							}else{
								right = false;
							}
						}else{
							Board.gm.tilegrid[getY()][getX()+i].setKingAccessable(getOwner());
							Board.gm.RedTiles.add(Board.gm.tilegrid[getY()][getX()+i]);
							right = false;
						}
					}else{
						Board.gm.tilegrid[getY()][getX()+i].setKingAccessable(getOwner());
						Board.gm.RedTiles.add(Board.gm.tilegrid[getY()][getX()+i]);
					}
				}else{
					right = false;
				}
				if((getY()-i) >= 0 && up){
					if(Board.gm.tilegrid[getY()-i][getX()].getOneIntersecting(ChessPiece.class)!=null){
						if(Board.gm.tilegrid[getY()-i][getX()].getOneIntersecting(King.class)!=null){
							if(((King) Board.gm.tilegrid[getY()-i][getX()].getOneIntersecting(King.class)).getOwner()!=getOwner()){
								Board.gm.tilegrid[getY()-i][getX()].highlight(false);
								Board.gm.tilegrid[getY()-i][getX()].setKingAccessable(getOwner());
								Board.gm.RedTiles.add(Board.gm.tilegrid[getY()-i][getX()]);
								this.attacking();
							}else{
								up = false;
							}
						}else{
							Board.gm.tilegrid[getY()-i][getX()].setKingAccessable(getOwner());
							Board.gm.RedTiles.add(Board.gm.tilegrid[getY()-i][getX()]);
							up = false;
						}
					}else{
						Board.gm.tilegrid[getY()-i][getX()].setKingAccessable(getOwner());
						Board.gm.RedTiles.add(Board.gm.tilegrid[getY()-i][getX()]);
					}
				}else{
					up = false;
				}
				if((getY()+i) <= Board.HEIGHT-1 && down){
					if(Board.gm.tilegrid[getY()+i][getX()].getOneIntersecting(ChessPiece.class)!= null){
						if(Board.gm.tilegrid[getY()+i][getX()].getOneIntersecting(King.class)!=null){
							if(((King) Board.gm.tilegrid[getY()+i][getX()].getOneIntersecting(King.class)).getOwner()!=getOwner()){
								Board.gm.tilegrid[getY()+i][getX()].highlight(false);
								Board.gm.tilegrid[getY()+i][getX()].setKingAccessable(getOwner());
								Board.gm.RedTiles.add(Board.gm.tilegrid[getY()+i][getX()]);
								this.attacking();
							}else{
								down = false;
							}
						}else{
							Board.gm.tilegrid[getY()+i][getX()].setKingAccessable(getOwner());
							Board.gm.RedTiles.add(Board.gm.tilegrid[getY()+i][getX()]);
							down = false;
						}
					}else{
						Board.gm.tilegrid[getY()+i][getX()].setKingAccessable(getOwner());
						Board.gm.RedTiles.add(Board.gm.tilegrid[getY()+i][getX()]);
					}
				}else{
					down = false;
				}
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
			} catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("ArrayIndexOutOfBounds");
			}
		}
	}
}
