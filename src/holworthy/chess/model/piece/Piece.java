package holworthy.chess.model.piece;

import java.util.ArrayList;

import holworthy.chess.model.Board;
import holworthy.chess.model.Square;
import holworthy.chess.model.move.Move;

public abstract class Piece {
	public static enum Colour{
		WHITE,
		BLACK;

		public Colour other() {
			return this == WHITE ? BLACK : WHITE;
		}
	}

	private Colour colour;
	private Board board;
	private boolean moved = false;

	public Piece(Colour colour, Board board){
		this.colour = colour;
		this.board = board;
	}

	public Colour getColour() {
		return colour;
	}

	public Board getBoard() {
		return board;
	}

	public boolean getMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public abstract char getCharacter();
	public abstract ArrayList<Move> generateMoves(Square from);
	public abstract int getType();
}
