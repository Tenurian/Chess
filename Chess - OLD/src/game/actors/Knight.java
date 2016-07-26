package game.actors;

import game.world.Board;
import game.world.Board.player;

public class Knight extends ChessPiece {
	public Knight(player owner){
		this.setOwner(owner);
		this.genImage(W_Kn, B_Kn);
		this.firstAct();
	}

	@Override
	public boolean pieceLogic(int x, int y){
		boolean U2_L1 = ((x==this.getX()-1)&&(y==this.getY()-2));	//up two left one
		boolean U2_R1 = ((x==this.getX()+1)&&(y==this.getY()-2));	//up two right one
		boolean R2_U1 = ((x==this.getX()+2)&&(y==this.getY()-1));	//right two up one
		boolean R2_D1 = ((x==this.getX()+2)&&(y==this.getY()+1));	//right two down one
		boolean D2_L1 = ((x==this.getX()-1)&&(y==this.getY()+2));	//down two left one
		boolean D2_R1 = ((x==this.getX()+1)&&(y==this.getY()+2));	//down two right one
		boolean L2_U1 = ((x==this.getX()-2)&&(y==this.getY()-1));	//left two up one
		boolean L2_D1 = ((x==this.getX()-2)&&(y==this.getY()+1));	//left two down one
		return U2_L1 || U2_R1 || R2_U1 || R2_D1 || D2_L1 || D2_R1 || L2_U1 || L2_D1;
	}

	@Override
	public void pieceAccessableToggle(){
		try {
			int[] offsets = {-2,-1,1,2};
			for(int j = 0; j < offsets.length; j++){
				for(int i = 0; i < offsets.length; i++){
					if(Math.abs(offsets[i]) != Math.abs(offsets[j])){
						if((this.getX()+offsets[i]) >= 0 && (this.getX()+offsets[i]) <= Board.WIDTH-1 && (this.getY()+offsets[j]) >= 0 && (this.getY()+offsets[j]) <= Board.HEIGHT-1){
							if(Board.gm.tilegrid[((this.getX()+offsets[i]))][((this.getY()+offsets[j]))]!=null){
								((Tile) Board.gm.tilegrid[((this.getY()+offsets[j]))][((this.getX()+offsets[i]))]).setKingAccessable(getOwner());
								((Tile) Board.gm.tilegrid[((this.getY()+offsets[j]))][((this.getX()+offsets[i]))]).highlight(this);	
							}
						}
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("ArrayIndexOutOfBounds");
		}
	}

	@Override
	public void kingInDangerToggle(){
		try {
			int[] offsets = {-2,-1,1,2};
			for(int j = 0; j < offsets.length; j++){
				for(int i = 0; i < offsets.length; i++){
					if(Math.abs(offsets[i]) != Math.abs(offsets[j])){
						if((this.getX()+offsets[i]) >= 0 && (this.getX()+offsets[i]) <= Board.WIDTH-1 && (this.getY()+offsets[j]) >= 0 && (this.getY()+offsets[j]) <= Board.HEIGHT-1){
							if(Board.gm.tilegrid[((this.getY()+offsets[j]))][((this.getX()+offsets[i]))]!=null){
								Board.gm.RedTiles.add(Board.gm.tilegrid[((this.getY()+offsets[j]))][((this.getX()+offsets[i]))]);
								Board.gm.tilegrid[((this.getY()+offsets[j]))][((this.getX()+offsets[i]))].setKingAccessable(getOwner());
								if(Board.gm.tilegrid[((this.getY()+offsets[j]))][((this.getX()+offsets[i]))].getOneIntersecting(King.class)!=null){
									if(((King)Board.gm.tilegrid[((this.getY()+offsets[j]))][((this.getX()+offsets[i]))].getOneIntersecting(King.class)).getOwner() != this.getOwner()){
										Board.gm.tilegrid[((this.getY()+offsets[j]))][((this.getX()+offsets[i]))].highlight(false);
										this.attacking();
									}
								}
							}
						}
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("ArrayIndexOutOfBounds");
		}
	}
}
