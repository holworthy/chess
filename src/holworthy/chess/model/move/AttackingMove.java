package holworthy.chess.model.move;

import holworthy.chess.model.piece.Piece;

public abstract class AttackingMove extends Move {
	private Piece capturedPiece;

	public AttackingMove(Piece capturedPiece) {
		this.capturedPiece = capturedPiece;
	}

	public AttackingMove() {
		this(null);
	}

	public Piece getCapturedPiece() {
		return capturedPiece;
	}
}
