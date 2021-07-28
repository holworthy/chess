package holworthy.chess.model.move;

import holworthy.chess.model.Square;
import holworthy.chess.model.piece.Piece;

public class StandardMove extends AttackingMove {
	private Square from;
	private Square to;

	public StandardMove(Square from, Square to, Piece capturedPiece){
		super(capturedPiece);
		this.from = from;
		this.to = to;
	}

	public Square getFrom() {
		return from;
	}

	public Square getTo() {
		return to;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof StandardMove))
			return false;
		StandardMove move = (StandardMove) obj;
		return from.equals(move.getFrom()) && to.equals(move.getTo());
	}

	@Override
	public String toString() {
		return from.getX() + ", " + from.getY() + " (" + (from.getPiece() == null ? '.' : from.getPiece().getCharacter()) + ") -> " + to.getX() + ", " + to.getY() + " (" + (to.getPiece() == null ? '.' : to.getPiece().getCharacter()) + ")";
	}
}
