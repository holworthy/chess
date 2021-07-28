package holworthy.chess.model.move;

import holworthy.chess.model.Square;
import holworthy.chess.model.piece.Piece;

public abstract class AttackingMove extends Move {
	private Square from;
	private Square to;
	private Piece movedPiece;
	private Piece capturedPiece;

	public AttackingMove(Square from, Square to, Piece movedPiece, Piece capturedPiece) {
		this.from = from;
		this.to = to;
		this.movedPiece = movedPiece;
		this.capturedPiece = capturedPiece;
	}

	public Square getFrom() {
		return from;
	}

	public Square getTo() {
		return to;
	}

	public Piece getMovedPiece() {
		return movedPiece;
	}

	public Piece getCapturedPiece() {
		return capturedPiece;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof AttackingMove))
			return false;
		AttackingMove move = (AttackingMove) obj;
		return getFrom().equals(move.getFrom()) && getTo().equals(move.getTo());
	}
}
