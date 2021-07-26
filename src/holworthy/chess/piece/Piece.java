package holworthy.chess.piece;

public abstract class Piece {
	private Colour colour;

	public static enum Colour{
		WHITE,
		BLACK;
	}

	public Piece(Colour colour){
		this.colour = colour;
	}

	public abstract char getCharacter();
}
