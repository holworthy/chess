package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Board;
import holworthy.chess.Move;
import holworthy.chess.Square;

public class Knight extends Piece{
	public Knight(Colour colour, Board board){
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return getColour() == Colour.WHITE ? 'N' : 'n';
	}

	@Override
	public ArrayList<Move> generateMoves(Square from) {
		ArrayList<Move> moves = new ArrayList<>();

		int fromX = from.getX();
		int fromY = from.getY();

		Square to = getBoard().getSquare(fromX + 1, fromY - 2);
		if(to != null)
			moves.add(new Move(from, to));
		to = getBoard().getSquare(fromX - 1, fromY - 2);
		if(to != null)
			moves.add(new Move(from, to));
		to = getBoard().getSquare(fromX + 2, fromY - 1);
		if(to != null)
			moves.add(new Move(from, to));
		to = getBoard().getSquare(fromX - 2, fromY - 1);
		if(to != null)
			moves.add(new Move(from, to));
		to = getBoard().getSquare(fromX + 1, fromY + 2);
		if(to != null)
			moves.add(new Move(from, to));
		to = getBoard().getSquare(fromX - 1, fromY + 2);
		if(to != null)
			moves.add(new Move(from, to));
		to = getBoard().getSquare(fromX + 2, fromY + 1);
		if(to != null)
			moves.add(new Move(from, to));
		to = getBoard().getSquare(fromX - 2, fromY + 1);
		if(to != null)
			moves.add(new Move(from, to));

		return moves;
	}
}
