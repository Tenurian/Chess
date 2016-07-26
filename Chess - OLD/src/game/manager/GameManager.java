package game.manager;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import game.ActorEnum;
import game.actors.Bishop;
import game.actors.ChessPiece;
import game.actors.King;
import game.actors.Knight;
import game.actors.Pawn;
import game.actors.Queen;
import game.actors.Rook;
import game.actors.Tile;
import game.world.Board;
import game.world.Board.player;
import greenfoot.*;

public class GameManager {

	public static ActorEnum[][] actorsgrid;
	private ChessPiece holder;
	private player current,opponent;
	private World world;
	public Tile[][] tilegrid;
	public ArrayList<Tile> RedTiles;
	private King WhiteKing, BlackKing;
	private ChessPiece attacker;

	public GameManager(World world){
		setAttacker(null);
		this.world = world;
		BlackKing = new King(player.BLACK);
		WhiteKing = new King(player.WHITE);
		current = player.WHITE;
		opponent = player.BLACK;
		tilegrid = new Tile[Board.WIDTH][Board.HEIGHT];
		RedTiles = new ArrayList<Tile>();
	}

	public void gameInit(){
		boolean black = true;
		GreenfootImage background = new GreenfootImage(Board.WIDTH*Board.CELL_SIZE, Board.HEIGHT*Board.CELL_SIZE);
		background.setColor(Color.MAGENTA);
		background.fill();
		world.setBackground(background);
		for(int i = 0; i < Board.HEIGHT; i++){
			black = !black;
			for(int j = 0; j < Board.WIDTH; j++){
				tilegrid[i][j] = new Tile(black, i, j);
				world.addObject(tilegrid[i][j], j, i);
				black = !black;
			}
		}

		addActors();
	}

	public void addActors(){
		for(int i = 0; i < Board.WIDTH; i++){
			world.addObject(new Pawn(player.BLACK), i, 1);
			world.addObject(new Pawn(player.WHITE), i, 6);
		}
		world.addObject(new Rook(player.BLACK), 0, 0);
		world.addObject(new Rook(player.BLACK), 7, 0);
		world.addObject(new Rook(player.WHITE), 0, 7);//
		world.addObject(new Rook(player.WHITE), 7, 7);//
		world.addObject(new Knight(player.BLACK), 1, 0);
		world.addObject(new Knight(player.BLACK), 6, 0);
		world.addObject(new Knight(player.WHITE), 1, 7);//
		world.addObject(new Knight(player.WHITE), 6, 7);//
		world.addObject(new Bishop(player.BLACK), 2, 0);
		world.addObject(new Bishop(player.BLACK), 5, 0);
		world.addObject(new Bishop(player.WHITE), 2, 7);//
		world.addObject(new Bishop(player.WHITE), 5, 7);//
		world.addObject(BlackKing, 4, 0);
		world.addObject(new Queen(player.BLACK), 3, 0);
		world.addObject(WhiteKing, 4, 7);//
		world.addObject(new Queen(player.WHITE), 3, 7);//
	}

	public void setClicked(ChessPiece p){
		holder = p;
		for(int i = 0; i < Board.WIDTH; i++){
			for(int j = 0; j < Board.HEIGHT; j++){
				tilegrid[i][j].imgUpdate();
			}
		}
		holder.pieceAccessableToggle();
	}

	public ChessPiece getClicked(){
		return holder;
	}

	public player getCurrentPlayer(){
		return current;
	}

	public player getOpponent(){
		return opponent;
	}

	public void nextTurn(){
		if(current == player.WHITE){
			current = player.BLACK;
			opponent = player.WHITE;
		}else if(current == player.BLACK){
			current = player.WHITE;
			opponent = player.BLACK;
		}
	}

	private void gameOver(String winner){
		world.repaint();
		Greenfoot.stop();
		JOptionPane.showMessageDialog(null, winner+" Wins!");
		reset();
	}

	@SuppressWarnings("unchecked")
	public boolean intersectable(){
		boolean out = false;
		for(ChessPiece c:(ArrayList<ChessPiece>)world.getObjects(ChessPiece.class)){
			if(c.getOwner() == current){
				if(c.getClass()!=King.class){
					for(Tile t:RedTiles){
						if(c.pieceLogic(t.getX(), t.getY())){
							System.out.println(c.toString()+"_@X:"+c.getX()+"Y:"+c.getY()+"_F:"+t.getX()+"G:"+t.getY());
							out = true;
						}
					}
				}
			}
		}
		return out;
	}

	@SuppressWarnings("unchecked")
	public boolean intersectable(ChessPiece attacker){
		boolean out = false;
		for(ChessPiece c:(ArrayList<ChessPiece>)world.getObjects(ChessPiece.class)){
			if(c.getClass()!= King.class){
				if(c.getOwner()!= attacker.getOwner()){
					for(int x = 0; x < Board.WIDTH; x++){
						for(int y = 0; y < Board.HEIGHT; y++){
							if(c.pieceLogic(x, y) && attacker.pieceLogic(x, y)){
								System.out.println(x+","+y);
							}
						}
					}
				}
			}
		}
		return out;
	}

	@SuppressWarnings("unchecked")
	public void moveClicked(int x, int y) {
		RedTiles.clear();
		holder.setLocation(x, y);
		holder.posUpdate(x, y);
		holder.pawnUpdate();

		for(int i = 0; i < Board.WIDTH; i++){
			for(int j = 0; j < Board.HEIGHT; j++){
				tilegrid[i][j].imgUpdate();
				tilegrid[i][j].setKingAccessable(null);
			}
		}
		RedTiles.clear();
		ArrayList<ChessPiece> bort = (ArrayList<ChessPiece>) world.getObjects(ChessPiece.class);
		for(ChessPiece c: bort){
			if(c.getOwner() == current){
				c.kingInDangerToggle();
			}else{
				if(c.getClass()!=King.class){
					RedTiles.add(tilegrid[c.getY()][c.getX()]);
				}
			}
		}
		holder = null;
		nextTurn();
		
		if(current == player.WHITE && RedTiles.contains(tilegrid[WhiteKing.getY()][WhiteKing.getX()])){
			intersectable();
		}
		if(current == player.BLACK && RedTiles.contains(tilegrid[BlackKing.getY()][BlackKing.getX()])){
			intersectable();
		}

		if(current == player.WHITE){
			if(WhiteKing.kingMateCheck()){
				gameOver("Player 2");
			}
		}else if(current == player.BLACK){
			if(BlackKing.kingMateCheck()){
				gameOver("Player 1");
			}
		}
	}

	public void repaintWorld() {
		this.world.repaint();
	}

	public void reset() {
		if(javax.swing.JOptionPane.showConfirmDialog( null, "Would you like to play again?", "Game Over!", javax.swing.JOptionPane.YES_NO_OPTION) == 0){
			Greenfoot.setWorld(new game.world.Board());
		}else{
			System.exit(1);
		}
	}

	public void setAttacker(ChessPiece attacker) {
		this.attacker = attacker;
	}

}
