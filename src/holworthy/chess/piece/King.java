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

		int fromX = from.getX();
		int fromY = from.getY();

		for(int dy = -1; dy <= 1; dy++) {
			for(int dx = -1; dx <= 1; dx++) {
				if(dx != 0 || dy != 0) {
					Square to = getBoard().getSquare(fromX + dx, fromY + dy);
					if(to != null)
						moves.add(new Move(from, to));
				}
			}
		}

		return moves;
	}
}
