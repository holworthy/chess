package holworthy.chess.model.move;

import holworthy.chess.model.piece.Piece;

public class EnPassantMove extends AttackingMove {
	public EnPassantMove(Piece pieceMoved, Piece capturedPiece) {
		super(pieceMoved, capturedPiece);
	}
}
