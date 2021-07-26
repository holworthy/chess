package holworthy.chess.piece;

public class Queen extends Piece{
	public Queen(Colour colour){
		super(colour);
	}

	@Override
	public char getCharacter() {
		return 'Q';
	}
}
