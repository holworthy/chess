package holworthy.chess.piece;

public class Knight extends Piece{
	public Knight(Colour colour){
		super(colour);
	}

	@Override
	public char getCharacter() {
		return 'N';
	}
}
