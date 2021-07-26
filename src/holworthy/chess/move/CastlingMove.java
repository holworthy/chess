package holworthy.chess.move;

public class CastlingMove extends Move {
	public static enum Side {
		KING,
		QUEEN;
	}
	private Side side;

	public CastlingMove(Side side) {
		this.side = side;
	}

	public Side getSide() {
		return side;
	}
}
