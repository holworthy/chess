package holworthy.chess.model.piece;

import java.util.ArrayList;

import holworthy.chess.model.Board;
import holworthy.chess.model.Square;
import holworthy.chess.model.move.Move;
import holworthy.chess.model.move.StandardMove;

public class Queen extends Piece{
	public Queen(Colour colour, Board board){
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return getColour() == Colour.WHITE ? 'Q' : 'q';
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

		to = getBoard().getSquareAboveRight(from);
		while(to != null) {
			moves.add(new StandardMove(from, to));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareAboveRight(to);
		}

		to = getBoard().getSquareRight(from);
		while(to != null) {
			moves.add(new StandardMove(from, to));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareRight(to);
		}

		to = getBoard().getSquareBelowRight(from);
		while(to != null) {
			moves.add(new StandardMove(from, to));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareBelowRight(to);
		}

		to = getBoard().getSquareBelow(from);
		while(to != null) {
			moves.add(new StandardMove(from, to));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareBelow(to);
		}

		to = getBoard().getSquareAboveLeft(from);
		while(to != null) {
			moves.add(new StandardMove(from, to));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareAboveLeft(to);
		}

		to = getBoard().getSquareLeft(from);
		while(to != null) {
			moves.add(new StandardMove(from, to));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareLeft(to);
		}

		to = getBoard().getSquareBelowLeft(from);
		while(to != null) {
			moves.add(new StandardMove(from, to));
			if(to.getPiece() != null)
				break;
			to = getBoard().getSquareBelowLeft(to);
		}

		return moves;
	}

	@Override
	public int getType() {
		return 4;
	}
}
