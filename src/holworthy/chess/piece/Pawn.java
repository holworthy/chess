package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Board;
import holworthy.chess.Move;
import holworthy.chess.Square;

public class Pawn extends Piece{
	public Pawn(Colour colour, Board board){
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return 'P';
	}

	@Override
	public ArrayList<Move> generateMoves(Square from) {
		return null;
	}
}
