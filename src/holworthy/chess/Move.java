package holworthy.chess;

public class Move {
	private Square from;
	private Square to;

	public Move(Square from, Square to){
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
		if(!(obj instanceof Move))
			return false;
		Move move = (Move) obj;
		return from.equals(move.getFrom()) && to.equals(move.getTo());
	}

	@Override
	public String toString() {
		return from.getX() + ", " + from.getY() + " (" + (from.getPiece() == null ? '.' : from.getPiece().getCharacter()) + ") -> " + to.getX() + ", " + to.getY() + " (" + (to.getPiece() == null ? '.' : to.getPiece().getCharacter()) + ")";
	}
}
