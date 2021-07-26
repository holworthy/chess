package holworthy.chess.move;

import holworthy.chess.Square;

public class StandardMove extends Move {
	private Square from;
	private Square to;

	public StandardMove(Square from, Square to){
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
