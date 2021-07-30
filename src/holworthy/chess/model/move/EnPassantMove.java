package holworthy.chess.model.move;

import holworthy.chess.model.Square;
import holworthy.chess.model.piece.Piece;

public class EnPassantMove extends StandardMove {
	private Square captured;

	public EnPassantMove(Square from, Square to, Piece pieceMoved, Piece capturedPiece, Square captured) {
		super(from, to, pieceMoved, capturedPiece);
		this.captured = captured;
	}

	public Square getCaptured() {
		return captured;
	}
}
