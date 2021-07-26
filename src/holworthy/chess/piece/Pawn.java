package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Board;
import holworthy.chess.Square;
import holworthy.chess.move.Move;
import holworthy.chess.move.StandardMove;

public class Pawn extends Piece{
	private boolean moved = false;

	public Pawn(Colour colour, Board board){
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return getColour() == Colour.WHITE ? 'P' : 'p';
	}

	@Override
	public ArrayList<Move> generateMoves(Square from) {
		ArrayList<Move> moves = new ArrayList<>();
		if (this.getColour() == Colour.WHITE){
			Square to = getBoard().getSquare(from.getX(), from.getY() - 1);
			if (to != null)
				moves.add(new StandardMove(from, to));
			if (!moved && to.getPiece() == null){
				to = getBoard().getSquare(from.getX(), from.getY() - 2);
				if (to != null)
					moves.add(new StandardMove(from, to));
			}
		}
		else{
			Square to = getBoard().getSquare(from.getX(), from.getY() + 1);
			if (to != null)
				moves.add(new StandardMove(from, to));
			if (!moved && to.getPiece() == null){
				to = getBoard().getSquare(from.getX(), from.getY() + 2);
				if (to != null)
					moves.add(new StandardMove(from, to));
			}
		}
		return moves;
	}
}
