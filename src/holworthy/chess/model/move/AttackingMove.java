package holworthy.chess.model.move;

import holworthy.chess.model.piece.Piece;

public abstract class AttackingMove extends Move {
	private Piece movedPiece;
	private Piece capturedPiece;

	public AttackingMove(Piece movedPiece, Piece capturedPiece) {
		this.movedPiece = movedPiece;
		this.capturedPiece = capturedPiece;
	}

	public Piece getMovedPiece() {
		return movedPiece;
	}

	public Piece getCapturedPiece() {
		return capturedPiece;
	}
}
