package holworthy.chess.model.piece;

import java.util.ArrayList;

import holworthy.chess.model.Board;
import holworthy.chess.model.Square;
import holworthy.chess.model.move.Move;
import holworthy.chess.model.move.StandardMove;

public class Pawn extends Piece{
	private boolean moved = false;

	public Pawn(Colour colour, Board board){
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return getColour() == Colour.WHITE ? 'P' : 'p';
	}

	public boolean getMoved(){
		return moved;
	}

	public void setMoved(boolean moved){
		this.moved = moved;
	}

	@Override
	public ArrayList<Move> generateMoves(Square from) {
		ArrayList<Move> moves = new ArrayList<>();
		if (this.getColour() == Colour.WHITE){
			// white forwards
			Square to = getBoard().getSquare(from.getX(), from.getY() - 1);
			if (to != null && to.getPiece() == null)
				moves.add(new StandardMove(from, to, to.getPiece()));
			// white forwards two spaces as frist move
			if (!moved && to.getPiece() == null){
				to = getBoard().getSquare(from.getX(), from.getY() - 2);
				if (to != null)
					moves.add(new StandardMove(from, to, to.getPiece()));
			}
			// white diagonal attack left
			to = getBoard().getSquare(from.getX() - 1, from.getY() - 1);
			if (to != null && to.getPiece() != null)
				moves.add(new StandardMove(from, to, to.getPiece()));
			// white diagonal attack right
			to = getBoard().getSquare(from.getX() + 1, from.getY() - 1);
			if (to != null && to.getPiece() != null)
				moves.add(new StandardMove(from, to, to.getPiece()));
		}
		else{
			// black forwards
			Square to = getBoard().getSquare(from.getX(), from.getY() + 1);
			if (to != null && to.getPiece() == null)
				moves.add(new StandardMove(from, to, to.getPiece()));
			// Black forwards two spaces as first move
			if (!moved && to.getPiece() == null){
				to = getBoard().getSquare(from.getX(), from.getY() + 2);
				if (to != null)
					moves.add(new StandardMove(from, to, to.getPiece()));
			}
			// Black diagonal attack left
			to = getBoard().getSquare(from.getX() - 1, from.getY() + 1);
			if (to != null && to.getPiece() != null)
				moves.add(new StandardMove(from, to, to.getPiece()));
			// Black diagonal attack right
			to = getBoard().getSquare(from.getX() + 1, from.getY() + 1);
			if (to != null && to.getPiece() != null)
				moves.add(new StandardMove(from, to, to.getPiece()));
		}
		return moves;
	}
}
