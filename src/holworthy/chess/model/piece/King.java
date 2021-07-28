package holworthy.chess.model.piece;

import java.util.ArrayList;

import holworthy.chess.model.Board;
import holworthy.chess.model.Square;
import holworthy.chess.model.move.CastlingMove;
import holworthy.chess.model.move.CastlingMove.Side;
import holworthy.chess.model.move.Move;
import holworthy.chess.model.move.StandardMove;

public class King extends Piece {
	public King(Colour colour, Board board) {
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return getColour() == Colour.WHITE ? 'K' : 'k';
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
						moves.add(new StandardMove(from, to));
				}
			}
		}

		for(Side side : Side.values())
			if(getBoard().isCastlingValid(getColour(), side))
				moves.add(new CastlingMove(side));
			
		return moves;
	}
}
