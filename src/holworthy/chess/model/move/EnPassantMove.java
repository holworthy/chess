package holworthy.chess.model.move;

import holworthy.chess.model.Square;
import holworthy.chess.model.piece.Piece;

public class EnPassantMove extends AttackingMove {
	Square from;
	Square to;
	Square captured;

	public EnPassantMove(Piece pieceMoved, Piece capturedPiece, Square from, Square to, Square captured) {
		super(pieceMoved, capturedPiece);
		this.from = from;
		this.to = to;
		this.captured = captured;
	}

	public Square getCaptured() {
		return captured;
	}

	public Square getFrom() {
		return from;
	}

	public Square getTo() {
		return to;
	}
}
