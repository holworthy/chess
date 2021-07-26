package holworthy.chess.piece;

import java.util.ArrayList;

import holworthy.chess.Board;
import holworthy.chess.Square;
import holworthy.chess.move.Move;
import holworthy.chess.move.StandardMove;

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
			moves.add(new StandardMove(from, to));
		to = getBoard().getSquare(fromX - 1, fromY - 2);
		if(to != null)
			moves.add(new StandardMove(from, to));
		to = getBoard().getSquare(fromX + 2, fromY - 1);
		if(to != null)
			moves.add(new StandardMove(from, to));
		to = getBoard().getSquare(fromX - 2, fromY - 1);
		if(to != null)
			moves.add(new StandardMove(from, to));
		to = getBoard().getSquare(fromX + 1, fromY + 2);
		if(to != null)
			moves.add(new StandardMove(from, to));
		to = getBoard().getSquare(fromX - 1, fromY + 2);
		if(to != null)
			moves.add(new StandardMove(from, to));
		to = getBoard().getSquare(fromX + 2, fromY + 1);
		if(to != null)
			moves.add(new StandardMove(from, to));
		to = getBoard().getSquare(fromX - 2, fromY + 1);
		if(to != null)
			moves.add(new StandardMove(from, to));

		return moves;
	}
}
