package holworthy.chess.model.move;

import holworthy.chess.model.Square;
import holworthy.chess.model.piece.Piece;

public class PromotionMove extends StandardMove {
	private Piece promotionPiece;
	
	public PromotionMove(Square from, Square to, Piece promotionPiece) {
		super(from, to);
		this.promotionPiece = promotionPiece;
	}

	public Piece getPromotionPiece() {
		return promotionPiece;
	}
}
