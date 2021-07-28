package holworthy.chess.model;

import holworthy.chess.model.move.CastlingMove;
import holworthy.chess.model.move.EnPassantMove;
import holworthy.chess.model.move.CastlingMove.Side;

public class Chess {
	private Board board;

	public Chess() {
		board = new Board();
	}

	public Board getBoard() {
		return board;
	}

	public static void main(String[] args) {
		Chess chess = new Chess();
		Board board = chess.getBoard();

		System.out.println(board);
		board.makeMove("d2d4");
		board.makeMove("h7h6");
		board.makeMove("d4d5");
		board.makeMove("c7c5");
		// board.makeMove("d5c6");
		System.out.println(board.makeMove(new EnPassantMove(board.getSquare(3, 3).getPiece(), board.getSquare(2, 3).getPiece(), board.getSquare(3, 3), board.getSquare(2, 2), board.getSquare(2, 3))));
		System.out.println(board);

		// board.undoMove();
		// board.undoMove();
		// board.undoMove();
		// board.undoMove();
		// board.undoMove();
		// board.undoMove();
		// board.undoMove();
		// board.undoMove();
		// System.out.println(board);
	}
}
