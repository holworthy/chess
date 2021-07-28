package holworthy.chess.model;

import holworthy.chess.model.move.CastlingMove;
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
		board.makeMove("a2a3");
		board.makeMove("g8h6");
		board.makeMove("b2b3");
		board.makeMove("e7e6");
		board.makeMove("c2c3");
		board.makeMove("f8d6");
		board.makeMove("d2d3");
		board.makeMove(new CastlingMove(Side.KING));
		System.out.println(board);

		board.undoMove();
		board.undoMove();
		board.undoMove();
		board.undoMove();
		board.undoMove();
		board.undoMove();
		board.undoMove();
		board.undoMove();
		System.out.println(board);
	}
}
