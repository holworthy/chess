package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Board;
import holworthy.chess.Square;
import holworthy.chess.move.Move;
import holworthy.chess.move.StandardMove;

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
			to = getBoard().getSquareAboveRight(from);
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
			to = getBoard().getSquareBelowRight(from);
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
			to = getBoard().getSquareAboveLeft(from);
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
			to = getBoard().getSquareBelowLeft(from);
		}

		return moves;
	}
}
