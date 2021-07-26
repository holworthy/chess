package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Board;
import holworthy.chess.Move;
import holworthy.chess.Square;

public class Queen extends Piece{
	public Queen(Colour colour, Board board){
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return 'Q';
	}

	@Override
	public ArrayList<Move> generateMoves(Square from) {
		return null;
	}
}
