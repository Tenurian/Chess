package game.actors;

import javax.swing.JOptionPane;

import game.world.Board;
import game.world.Board.player;
import greenfoot.Greenfoot;

public class King extends ChessPiece {
	public King(player owner){
		this.setOwner(owner);
		this.genImage(W_Ki,B_Ki);
		this.firstAct();
	}

	@Override
	public boolean pieceLogic(int x, int y){
		@SuppressWarnings("unused")
		boolean a1 = true,a2 = true,a3 = true,a4 = true,a5 = true,a6 = true,a7 = true,a8 = true;
		try{
			if(this.getY() > 0){
				if(this.getX() > 0 ){
					Board.gm.tilegrid[this.getY()-1][this.getX()-1].highlight(Board.gm.tilegrid[this.getY()-1][this.getX()-1].isKingAccessable(getOwner()));	//c1
					a1 = Board.gm.tilegrid[this.getY()-1][this.getX()-1].isKingAccessable(getOwner());
				}
				Board.gm.tilegrid[this.getY()-1][this.getX()].highlight(Board.gm.tilegrid[this.getY()-1][this.getX()].isKingAccessable(getOwner()));		//c2
				a2 = Board.gm.tilegrid[this.getY()-1][this.getX()].isKingAccessable(getOwner());
				if(this.getX() < Board.WIDTH-1){
					Board.gm.tilegrid[this.getY()-1][this.getX()+1].highlight(Board.gm.tilegrid[this.getY()-1][this.getX()+1].isKingAccessable(getOwner()));	//c3
					a3 = Board.gm.tilegrid[this.getY()-1][this.getX()+1].isKingAccessable(getOwner());
				}
			}
			if(this.getX() > 0){
				Board.gm.tilegrid[this.getY()][this.getX()-1].highlight(Board.gm.tilegrid[this.getY()][this.getX()-1].isKingAccessable(getOwner()));		//c4
				a4 = Board.gm.tilegrid[this.getY()][this.getX()-1].isKingAccessable(getOwner());
			}
			if(this.getX() < Board.WIDTH-1){
				Board.gm.tilegrid[this.getY()][this.getX()+1].highlight(Board.gm.tilegrid[this.getY()][this.getX()+1].isKingAccessable(getOwner()));		//c5
				a5 = Board.gm.tilegrid[this.getY()][this.getX()+1].isKingAccessable(getOwner());
			}

			if(this.getY() < Board.HEIGHT-1){
				if(this.getX() > 0){
					Board.gm.tilegrid[this.getY()+1][this.getX()-1].highlight(Board.gm.tilegrid[this.getY()+1][this.getX()-1].isKingAccessable(getOwner()));	//c6
					a6 = Board.gm.tilegrid[this.getY()+1][this.getX()-1].isKingAccessable(getOwner());
				}
				Board.gm.tilegrid[this.getY()+1][this.getX()].highlight(Board.gm.tilegrid[this.getY()+1][this.getX()].isKingAccessable(getOwner()));		//c7
				a7 = Board.gm.tilegrid[this.getY()+1][this.getX()].isKingAccessable(getOwner());
				if(this.getX() < Board.WIDTH-1){
					Board.gm.tilegrid[this.getY()+1][this.getX()+1].highlight(Board.gm.tilegrid[this.getY()+1][this.getX()+1].isKingAccessable(getOwner()));	//c8
					a8 = Board.gm.tilegrid[this.getY()+1][this.getX()+1].isKingAccessable(getOwner());
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("ArrayIndexOutOfBounds:KING");
			e.printStackTrace();
		}

		boolean c1 = ((x==this.getX()-1)&&(y==this.getY()-1));
		boolean c2 = ((x==this.getX())&&(y==this.getY()-1));
		boolean c3 = ((x==this.getX()+1)&&(y==this.getY()-1));
		boolean c4 = ((x==this.getX()-1)&&(y==this.getY()));
		boolean c5 = ((x==this.getX()+1)&&(y==this.getY()));
		boolean c6 = ((x==this.getX()-1)&&(y==this.getY()+1));
		boolean c7 = ((x==this.getX())&&(y==this.getY()+1));
		boolean c8 = ((x==this.getX()+1)&&(y==this.getY()+1));

		return (c1||c2||c3||c4||c5||c6||c7||c8);
	}

	public boolean kingMateCheck(){
		boolean b1 = false, b2 = false, b3 = false, b4 = false, b5 = false, b6 = false, b7 = false, b8 = false, b9 = false;
		try{
			if(this.getY() > 0){
				if(this.getX() > 0 ){
					b1 = Board.gm.RedTiles.contains(Board.gm.tilegrid[this.getY()-1][this.getX()-1]);
				}
				b2 = Board.gm.RedTiles.contains(Board.gm.tilegrid[this.getY()-1][this.getX()]);
				if(this.getX() < Board.WIDTH-1){
					b3 = Board.gm.RedTiles.contains(Board.gm.tilegrid[this.getY()-1][this.getX()+1]);
				}
			}else{
				b1 = b2 = b3 = true;
			}

			if(this.getX() > 0){
				b4 = Board.gm.RedTiles.contains(Board.gm.tilegrid[this.getY()][this.getX()-1]);
			}else{
				b4 = true;
			}
			
			b5 = Board.gm.RedTiles.contains(Board.gm.tilegrid[this.getY()][this.getX()]);
			
			if(this.getX() < Board.WIDTH-1){
				b6 = Board.gm.RedTiles.contains(Board.gm.tilegrid[this.getY()][this.getX()+1]);
			}else{
				b6 = true;
			}
			
			if(this.getY() < Board.HEIGHT-1){
				if(this.getX() > 0){
					b7 = Board.gm.RedTiles.contains(Board.gm.tilegrid[this.getY()+1][this.getX()-1]);
				}
				b8 = Board.gm.RedTiles.contains(Board.gm.tilegrid[this.getY()+1][this.getX()]);
				if(this.getX() < Board.WIDTH-1){
					b9 = Board.gm.RedTiles.contains(Board.gm.tilegrid[this.getY()+1][this.getX()+1]);
				}
			}else{
				b7 = b8 = b9 = true;
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("ArrayIndexOutOfBounds:KING");
			e.printStackTrace();
		}
		
		return (b1&&b2&&b3&&b4&&b5&&b6&&b7&&b8&&b9);

	}

	@Override
	public void pieceAccessableToggle(){
		@SuppressWarnings("unused")
		boolean b1 = false, b2 = false, b3 = false, b4 = false, b5 = false, b6 = false, b7 = false, b8 = false, b9 = false;
		try{
			if(this.getY() > 0){
				if(this.getX() > 0 ){
					Board.gm.tilegrid[this.getY()-1][this.getX()-1].highlight(Board.gm.tilegrid[this.getY()-1][this.getX()-1].isKingAccessable(getOwner()));	//c1
				}
				Board.gm.tilegrid[this.getY()-1][this.getX()].highlight(Board.gm.tilegrid[this.getY()-1][this.getX()].isKingAccessable(getOwner()));		//c2
				if(this.getX() < Board.WIDTH-1){
					Board.gm.tilegrid[this.getY()-1][this.getX()+1].highlight(Board.gm.tilegrid[this.getY()-1][this.getX()+1].isKingAccessable(getOwner()));	//c3
				}
			}
			if(this.getX() > 0){
				Board.gm.tilegrid[this.getY()][this.getX()-1].highlight(Board.gm.tilegrid[this.getY()][this.getX()-1].isKingAccessable(getOwner()));		//c4
			}
			if(this.getX() < Board.WIDTH-1){
				Board.gm.tilegrid[this.getY()][this.getX()+1].highlight(Board.gm.tilegrid[this.getY()][this.getX()+1].isKingAccessable(getOwner()));		//c5
			}

			if(this.getY() < Board.HEIGHT-1){
				if(this.getX() > 0){
					Board.gm.tilegrid[this.getY()+1][this.getX()-1].highlight(Board.gm.tilegrid[this.getY()+1][this.getX()-1].isKingAccessable(getOwner()));	//c6
				}
				Board.gm.tilegrid[this.getY()+1][this.getX()].highlight(Board.gm.tilegrid[this.getY()+1][this.getX()].isKingAccessable(getOwner()));		//c7
				if(this.getX() < Board.WIDTH-1){
					Board.gm.tilegrid[this.getY()+1][this.getX()+1].highlight(Board.gm.tilegrid[this.getY()+1][this.getX()+1].isKingAccessable(getOwner()));	//c8
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("ArrayIndexOutOfBounds:KING");
			e.printStackTrace();
		}
	}

	@Override
	public void kingCheck(){
		Board.gm.repaintWorld();
		if(this.getWorld() == null){
			Greenfoot.stop();
			String winner = "";
			if(this.getOwner() == player.BLACK)
				winner = "White";
			if(this.getOwner() == player.WHITE)
				winner = "Black";
			JOptionPane.showMessageDialog(null, winner+" Wins!");
			Board.gm.reset();
		}
	}
}
