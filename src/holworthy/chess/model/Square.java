package holworthy.chess.model;

import holworthy.chess.model.piece.Piece;

public class Square {
	private int x;
	private int y;
	private Piece piece;

	public Square(int x, int y, Piece piece) {
		this.x = x;
		this.y = y;
		this.piece = piece;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}
