package holworthy.chess.model;

import java.util.ArrayList;

import holworthy.chess.model.move.Move;

public class Chess {
	private Board board;

	public Chess() {
		board = new Board();
	}

	public Board getBoard() {
		return board;
	}

	public static void main(String[] args) throws InterruptedException {
		Chess chess = new Chess();
		Board board = chess.getBoard();

		while(true) {
			System.out.println(board);
			ArrayList<Move> moves = board.generateMoves(board.getWhosTurn());
			System.out.println("generated: " + moves.size());
			for(Move move : moves) {
				if(board.makeMove(move)) {
					System.out.println(move);
					break;
				}
			}
					
			System.out.println(board);
			System.out.println("----------------------------------------------");
			Thread.sleep(500);
		}
	}
}
