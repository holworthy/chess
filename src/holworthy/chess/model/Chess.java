package holworthy.chess.model;

import java.util.ArrayList;
import java.util.Collections;

import holworthy.chess.model.move.Move;

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

		while(true) {
			System.out.println(board.getWhosTurn());
			System.out.println(board);

			ArrayList<Move> moves = board.generateValidMoves(board.getWhosTurn());
			if(moves.size() == 0)
				break;
			Collections.shuffle(moves);
			for(Move move : moves) {
				if(board.makeMove(move)) {
					System.out.println(move);
					break;
				}
			}
					
			System.out.println(board);
			System.out.println("----------------------------------------------");
		}

		System.out.println(board.getWhosTurn() + " is the winner");
	}
}
