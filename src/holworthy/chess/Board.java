package holworthy.chess;

import java.util.ArrayList;

import holworthy.chess.piece.Bishop;
import holworthy.chess.piece.King;
import holworthy.chess.piece.Knight;
import holworthy.chess.piece.Pawn;
import holworthy.chess.piece.Piece;
import holworthy.chess.piece.Queen;
import holworthy.chess.piece.Rook;

public class Board {
	private Square[][] squares;

	public Board() {
		squares = new Square[8][8];

		squares[0][0] = new Square(0, 0, new Rook(Piece.Colour.BLACK, this));
		squares[0][1] = new Square(1, 0, new Knight(Piece.Colour.BLACK, this));
		squares[0][2] = new Square(2, 0, new Bishop(Piece.Colour.BLACK, this));
		squares[0][3] = new Square(3, 0, new Queen(Piece.Colour.BLACK, this));
		squares[0][4] = new Square(4, 0, new King(Piece.Colour.BLACK, this));
		squares[0][5] = new Square(5, 0, new Bishop(Piece.Colour.BLACK, this));
		squares[0][6] = new Square(6, 0, new Knight(Piece.Colour.BLACK, this));
		squares[0][7] = new Square(7, 0, new Rook(Piece.Colour.BLACK, this));

		for(int x = 0; x < 8; x++) {
			squares[1][x] = new Square(x, 1, new Pawn(Piece.Colour.BLACK, this));
			squares[6][x] = new Square(x, 6, new Pawn(Piece.Colour.WHITE, this));
		}

		for(int y = 2; y < 6; y++)
			for(int x = 0; x < 8; x++)
				squares[y][x] = new Square(x, y, null);

		squares[7][0] = new Square(0, 7, new Rook(Piece.Colour.WHITE, this));
		squares[7][1] = new Square(1, 7, new Knight(Piece.Colour.WHITE, this));
		squares[7][2] = new Square(2, 7, new Bishop(Piece.Colour.WHITE, this));
		squares[7][3] = new Square(3, 7, new Queen(Piece.Colour.WHITE, this));
		squares[7][4] = new Square(4, 7, new King(Piece.Colour.WHITE, this));
		squares[7][5] = new Square(5, 7, new Bishop(Piece.Colour.WHITE, this));
		squares[7][6] = new Square(6, 7, new Knight(Piece.Colour.WHITE, this));
		squares[7][7] = new Square(7, 7, new Rook(Piece.Colour.WHITE, this));
	}

	@Override
	public String toString() {
		String string = "";

		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				string += squares[y][x].getPiece() == null ? '.' : squares[y][x].getPiece().getCharacter();
			}
			string += "\n";
		}

		return string;
	}


	// TODO: no white on white action
	// TODO: no black on black action
	// TODO: en passant
	// TODO: castling
	// TODO: no invalid moves when in check
	// TODO: no moving queens bishops or rooks through other pieces
	public ArrayList<Move> generateMoves(Piece.Colour colour) {
		ArrayList<Move> moves = new ArrayList<>();

		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				Square square = getSquare(x, y);
				if(square.getPiece() != null && square.getPiece().getColour() == colour)
					moves.addAll(square.getPiece().generateMoves(square));
			}
		}

		return moves;
	}

	public Square getSquare(int x, int y) {
		if(x < 0 || x > 7 || y < 0 || y > 7)
			return null;
		return squares[y][x];
	}

	public Square getSquareAbove(Square square) {
		return getSquare(square.getX(), square.getY() - 1);
	}

	public Square getSquareRight(Square square) {
		return getSquare(square.getX() + 1, square.getY());
	}

	public Square getSquareBelow(Square square) {
		return getSquare(square.getX(), square.getY() + 1);
	}

	public Square getSquareLeft(Square square) {
		return getSquare(square.getX() - 1, square.getY());
	}

	public Square getSquareAboveRight(Square square) {
		return getSquare(square.getX() + 1, square.getY() - 1);
	}

	public Square getSquareAboveLeft(Square square) {
		return getSquare(square.getX() - 1, square.getY() - 1);
	}

	public Square getSquareBelowRight(Square square) {
		return getSquare(square.getX() + 1, square.getY() + 1);
	}

	public Square getSquareBelowLeft(Square square) {
		return getSquare(square.getX() - 1, square.getY() + 1);
	}

	public static void main(String[] args) {
		Board board = new Board();
		System.out.println(board);
	}
}
