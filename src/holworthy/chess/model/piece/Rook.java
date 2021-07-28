package holworthy.chess.model.piece;

import java.util.ArrayList;

import holworthy.chess.model.Board;
import holworthy.chess.model.Square;
import holworthy.chess.model.move.Move;
import holworthy.chess.model.move.StandardMove;

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
			moves.add(new StandardMove(from, to, to.getPiece()));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareAbove(to);
		}

		to = getBoard().getSquareRight(from);
		while(to != null) {
			moves.add(new StandardMove(from, to, to.getPiece()));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareRight(to);
		}

		to = getBoard().getSquareBelow(from);
		while(to != null) {
			moves.add(new StandardMove(from, to, to.getPiece()));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareBelow(to);
		}

		to = getBoard().getSquareLeft(from);
		while(to != null) {
			moves.add(new StandardMove(from, to, to.getPiece()));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareLeft(to);
		}

		return moves;
	}
}
