package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Board;
import holworthy.chess.Move;
import holworthy.chess.Square;

public class King extends Piece {
	public King(Colour colour, Board board) {
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return 'K';
	}

	@Override
	public ArrayList<Move> generateMoves(Square from) {
		ArrayList<Move> moves = new ArrayList<>();
		return moves;
	}
}
