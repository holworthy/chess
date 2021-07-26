package holworthy.chess.piece;

public class Pawn extends Piece{
	public Pawn(Colour colour){
		super(colour);
	}

	@Override
	public char getCharacter() {
		return 'P';
	}
}
