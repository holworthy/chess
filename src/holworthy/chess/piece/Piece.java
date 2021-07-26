package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Move;
import holworthy.chess.Square;

public abstract class Piece {
	private Colour colour;

	public static enum Colour{
		WHITE,
		BLACK;
	}

	public Piece(Colour colour){
		this.colour = colour;
	}

	public Colour getColour() {
		return colour;
	}

	public abstract char getCharacter();
	public abstract ArrayList<Move> generateMoves(Square from);
}
