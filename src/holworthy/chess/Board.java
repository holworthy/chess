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
		squares[0][1] = new Square(0, 1, new Knight(Piece.Colour.BLACK, this));
		squares[0][2] = new Square(0, 2, new Bishop(Piece.Colour.BLACK, this));
		squares[0][3] = new Square(0, 3, new Queen(Piece.Colour.BLACK, this));
		squares[0][4] = new Square(0, 4, new King(Piece.Colour.BLACK, this));
		squares[0][5] = new Square(0, 5, new Bishop(Piece.Colour.BLACK, this));
		squares[0][6] = new Square(0, 6, new Knight(Piece.Colour.BLACK, this));
		squares[0][7] = new Square(0, 7, new Rook(Piece.Colour.BLACK, this));

		for(int x = 0; x < 8; x++) {
			squares[1][x] = new Square(1, x, new Pawn(Piece.Colour.BLACK, this));
			squares[6][x] = new Square(6, x, new Pawn(Piece.Colour.WHITE, this));
		}

		for(int y = 2; y < 6; y++)
			for(int x = 0; x < 8; x++)
				squares[y][x] = new Square(x, y, null);

		squares[7][0] = new Square(7, 0, new Rook(Piece.Colour.WHITE, this));
		squares[7][1] = new Square(7, 1, new Knight(Piece.Colour.WHITE, this));
		squares[7][2] = new Square(7, 2, new Bishop(Piece.Colour.WHITE, this));
		squares[7][3] = new Square(7, 3, new Queen(Piece.Colour.WHITE, this));
		squares[7][4] = new Square(7, 4, new King(Piece.Colour.WHITE, this));
		squares[7][5] = new Square(7, 5, new Bishop(Piece.Colour.WHITE, this));
		squares[7][6] = new Square(7, 6, new Knight(Piece.Colour.WHITE, this));
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

	public static void main(String[] args) {
		Board board = new Board();
		System.out.println(board);
	}
}
