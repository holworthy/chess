package holworthy.chess.piece;

public class Bishop extends Piece{
	public Bishop(Colour colour){
		super(colour);
	}

	@Override
	public char getCharacter() {
		return 'B';
	}
}
