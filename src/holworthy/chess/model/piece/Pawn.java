package holworthy.chess.model.piece;

import java.util.ArrayList;

import holworthy.chess.model.Board;
import holworthy.chess.model.Square;
import holworthy.chess.model.move.Move;
import holworthy.chess.model.move.PromotionMove;
import holworthy.chess.model.move.StandardMove;

public class Pawn extends Piece{
	public Pawn(Colour colour, Board board){
		super(colour, board);
	}

	@Override
	public char getCharacter() {
		return getColour() == Colour.WHITE ? 'P' : 'p';
	}

	@Override
	public ArrayList<Move> generateMoves(Square from) {
		ArrayList<Move> moves = new ArrayList<>();
		if (getColour() == Colour.WHITE){
			// white forwards
			Square to = getBoard().getSquare(from.getX(), from.getY() - 1);
			if(to != null && to.getPiece() == null) {
				if(to.getY() == 0) {
					moves.add(new PromotionMove(from, to, new Queen(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Rook(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Bishop(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Rook(getColour(), getBoard())));
				} else {
					moves.add(new StandardMove(from, to));
				}
			}
				
			// white forwards two spaces as first move
			if (!getMoved() && to.getPiece() == null){
				to = getBoard().getSquare(from.getX(), from.getY() - 2);
				if (to != null)
					moves.add(new StandardMove(from, to));
			}
			// white diagonal attack left
			to = getBoard().getSquare(from.getX() - 1, from.getY() - 1);
			if(to != null && to.getPiece() != null) {
				if(to.getY() == 0) {
					moves.add(new PromotionMove(from, to, new Queen(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Rook(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Bishop(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Rook(getColour(), getBoard())));
				} else {
					moves.add(new StandardMove(from, to));
				}
			}
			// white diagonal attack right
			to = getBoard().getSquare(from.getX() + 1, from.getY() - 1);
			if(to != null && to.getPiece() != null) {
				if(to.getY() == 0) {
					moves.add(new PromotionMove(from, to, new Queen(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Rook(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Bishop(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Rook(getColour(), getBoard())));
				} else {
					moves.add(new StandardMove(from, to));
				}
			}
		}
		else{
			// black forwards
			Square to = getBoard().getSquare(from.getX(), from.getY() + 1);
			if(to != null && to.getPiece() != null) {
				if(to.getY() == 0) {
					moves.add(new PromotionMove(from, to, new Queen(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Rook(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Bishop(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Rook(getColour(), getBoard())));
				} else {
					moves.add(new StandardMove(from, to));
				}
			}
			// Black forwards two spaces as first move
			if (!getMoved() && to.getPiece() == null){
				to = getBoard().getSquare(from.getX(), from.getY() + 2);
				if (to != null)
					moves.add(new StandardMove(from, to));
			}
			// Black diagonal attack left
			to = getBoard().getSquare(from.getX() - 1, from.getY() + 1);
			if(to != null && to.getPiece() != null) {
				if(to.getY() == 0) {
					moves.add(new PromotionMove(from, to, new Queen(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Rook(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Bishop(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Rook(getColour(), getBoard())));
				} else {
					moves.add(new StandardMove(from, to));
				}
			}
			// Black diagonal attack right
			to = getBoard().getSquare(from.getX() + 1, from.getY() + 1);
			if(to != null && to.getPiece() != null) {
				if(to.getY() == 0) {
					moves.add(new PromotionMove(from, to, new Queen(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Rook(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Bishop(getColour(), getBoard())));
					moves.add(new PromotionMove(from, to, new Rook(getColour(), getBoard())));
				} else {
					moves.add(new StandardMove(from, to));
				}
			}
		}
		getBoard().isEnPassantValid(from);
		return moves;
	}
}
