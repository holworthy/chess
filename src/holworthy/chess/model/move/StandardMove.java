package holworthy.chess.model.move;

import holworthy.chess.model.Square;
import holworthy.chess.model.piece.Piece;

public class StandardMove extends Move {
	private Square from;
	private Square to;
	private Piece movedPiece;
	private Piece capturedPiece;

	public StandardMove(Square from, Square to, Piece movedPiece, Piece capturedPiece) {
		this.from = from;
		this.to = to;
		this.movedPiece = movedPiece;
		this.capturedPiece = capturedPiece;
	}

	public StandardMove(Square from, Square to) {
		this(from, to, from.getPiece(), to.getPiece());
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
		if(!(obj instanceof StandardMove))
			return false;
		StandardMove move = (StandardMove) obj;
		return getFrom().equals(move.getFrom()) && getTo().equals(move.getTo());
	}
}
