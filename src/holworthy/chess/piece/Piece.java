package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Board;
import holworthy.chess.Move;
import holworthy.chess.Square;

public abstract class Piece {
	public static enum Colour{
		WHITE,
		BLACK;
	}

	private Colour colour;
	private Board board;

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

	public abstract char getCharacter();
	public abstract ArrayList<Move> generateMoves(Square from);
}
