package holworthy.chess.piece;

public class Rook extends Piece{
	public Rook(Colour colour){
		super(colour);
	}

	@Override
	public char getCharacter() {
		return 'R';
	}
}
