package holworthy.chess.model.move;

import holworthy.chess.model.Square;

public class StandardMove extends AttackingMove {
	public StandardMove(Square from, Square to) {
		super(from, to, from.getPiece(), to.getPiece());
	}

	// @Override
	// public String toString() {
	// 	return from.getX() + ", " + from.getY() + " (" + (from.getPiece() == null ? '.' : from.getPiece().getCharacter()) + ") -> " + to.getX() + ", " + to.getY() + " (" + (to.getPiece() == null ? '.' : to.getPiece().getCharacter()) + ")";
	// }
}
