package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Board;
import holworthy.chess.Move;
import holworthy.chess.Square;

public class Rook extends Piece{
	public Rook(Colour colour, Board board){
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return 'R';
	}

	@Override
	public ArrayList<Move> generateMoves(Square from) {
		return null;
	}
}
