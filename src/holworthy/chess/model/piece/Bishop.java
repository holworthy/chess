package holworthy.chess.model.piece;

import java.util.ArrayList;

import holworthy.chess.model.Board;
import holworthy.chess.model.Square;
import holworthy.chess.model.move.Move;
import holworthy.chess.model.move.StandardMove;

public class Bishop extends Piece{
	public Bishop(Colour colour, Board board){
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return getColour() == Colour.WHITE ? 'B' : 'b';
	}

	@Override
	public ArrayList<Move> generateMoves(Square from) {
		ArrayList<Move> moves = new ArrayList<>();
		for (int xChange = -1; xChange <= 1; xChange += 2){
			for (int yChange = -1; yChange <= 1; yChange += 2){
				Square to = getBoard().getSquare(from.getX() + xChange, from.getY() + yChange);
				int multiple = 2;
				while (to != null){
					moves.add(new StandardMove(from, to));
					if (to.getPiece() == null)
						to = getBoard().getSquare(from.getX() + (xChange*multiple), from.getY() + (yChange*multiple));
					else
						to = null;
					multiple++;
				}
			}
		}
		return moves;
	}

	@Override
	public int getType() {
		return 2;
	}
}
