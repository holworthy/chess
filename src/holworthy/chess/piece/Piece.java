package holworthy.chess.piece;

abstract public class Piece {
	private Colour colour;

	public static enum Colour{
		WHITE,
		BLACK;
	}

	Piece(Colour colour){
		this.colour = colour;
	}
}
