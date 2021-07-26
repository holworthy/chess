package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Board;
import holworthy.chess.Move;
import holworthy.chess.Square;

public class Bishop extends Piece{
	public Bishop(Colour colour, Board board){
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return 'B';
	}

	@Override
	public ArrayList<Move> generateMoves(Square from) {
		ArrayList<Move> moves = new ArrayList<>();
		for (int xChange = -1; xChange <= 1; xChange += 2){
			for (int yChange = -1; yChange <= 1; yChange += 2){
				Square to = getBoard().getSquare(from.getX() + xChange, from.getY() + yChange);
				int multiple = 2;
				while (to != null){
					moves.add(new Move(from, to));
					to = getBoard().getSquare(from.getX() + (xChange*multiple), from.getY() + (yChange*multiple));
					multiple++;
				}
			}
		}
		return moves;
	}
}
