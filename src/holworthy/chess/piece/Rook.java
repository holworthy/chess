package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Board;
import holworthy.chess.Square;
import holworthy.chess.move.Move;
import holworthy.chess.move.StandardMove;

public class Rook extends Piece{
	public Rook(Colour colour, Board board){
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return getColour() == Colour.WHITE ? 'R' : 'r';
	}

	@Override
	public ArrayList<Move> generateMoves(Square from) {
		ArrayList<Move> moves = new ArrayList<>();

		Square to = getBoard().getSquareAbove(from);
		while(to != null) {
			moves.add(new StandardMove(from, to));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareAbove(to);
		}

		to = getBoard().getSquareRight(from);
		while(to != null) {
			moves.add(new StandardMove(from, to));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareRight(to);
		}

		to = getBoard().getSquareBelow(from);
		while(to != null) {
			moves.add(new StandardMove(from, to));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareBelow(to);
		}

		to = getBoard().getSquareLeft(from);
		while(to != null) {
			moves.add(new StandardMove(from, to));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareLeft(to);
		}

		return moves;
	}
}
