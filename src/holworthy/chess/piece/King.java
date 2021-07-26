package holworthy.chess.piece;

public class King extends Piece {
	public King(Colour colour) {
		super(colour);
	}

	@Override
	public char getCharacter() {
		return 'K';
	}
}
