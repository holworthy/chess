package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Board;
import holworthy.chess.Move;
import holworthy.chess.Square;

public class Rook extends Piece{
	public Rook(Colour colour, Board board){
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return 'R';
	}

	@Override
	public ArrayList<Move> generateMoves(Square from) {
		ArrayList<Move> moves = new ArrayList<>();

		int fromX = from.getX();
		int fromY = from.getY();

		for(int x = 0; x < 8; x++) {
			if(x != fromX) {
				Square to = getBoard().getSquare(x, fromY);
				if(to != null)
					moves.add(new Move(from, to));
			}
		}

		for(int y = 0; y < 8; y++) {
			if(y != fromY) {
				Square to = getBoard().getSquare(fromX, y);
				if(to != null)
					moves.add(new Move(from, to));
			}
		}

		return moves;
	}
}
