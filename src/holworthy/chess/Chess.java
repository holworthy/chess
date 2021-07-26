package holworthy.chess;

import java.util.ArrayList;

import holworthy.chess.piece.Piece;

public class Chess {
	private Board board;
	private Piece.Colour whosTurn;
	private ArrayList<Move> moves;
	private ArrayList<Piece> capturedPieces;

	public Chess() {
		board = new Board();
		whosTurn = Piece.Colour.WHITE;
		moves = new ArrayList<>();
		capturedPieces = new ArrayList<>();
	}

	public void makeMove(Move move) {
		if(move.getFrom().getPiece() == null || move.getFrom().getPiece().getColour() != whosTurn) {
			// TODO: can only move own pieces
			return;
		}

		if(move.getTo().getPiece() != null && move.getTo().getPiece().getColour() == whosTurn) {
			// TODO: cannot move onto own piece
			return;
		}

		Square from = move.getFrom();
		Square to = move.getTo();

		capturedPieces.add(to.getPiece());
		to.setPiece(from.getPiece());
		from.setPiece(null);

		whosTurn = whosTurn.other();
	}

	private boolean isValidRank(char rank) {
		return rank >= '1' && rank <= '8';
	}

	private boolean isValidFile(char file) {
		return file >= 'a' && file <= 'h';
	}

	public void makeMove(String moveString) {
		if(moveString.length() != 4) {
			// TODO: invalid moveString
			return;
		}

		char fromFile = moveString.charAt(0);
		char fromRank = moveString.charAt(1);
		char toFile = moveString.charAt(2);
		char toRank = moveString.charAt(3);

		if(!isValidFile(fromFile) || !isValidFile(toFile) || !isValidRank(fromRank) || !isValidRank(toRank)) {
			// TODO: invalid moveString
			return;
		}

		int fromX = fromFile - 'a';
		int fromY = 7 - (fromRank - '1');
		int toX = toFile - 'a';
		int toY = 7 - (toRank - '1');

		System.out.printf("%d %d %d %d\n", fromX, fromY, toX, toY);

		makeMove(new Move(board.getSquare(fromX, fromY), board.getSquare(toX, toY)));
	}

	public void undoMove() {
		Move lastMove = moves.remove(moves.size() - 1);
		Square from = lastMove.getFrom();
		Square to = lastMove.getTo();
		from.setPiece(to.getPiece());
		to.setPiece(capturedPieces.remove(capturedPieces.size() - 1));
	}

	public Board getBoard() {
		return board;
	}

	public static void main(String[] args) {
		Chess chess = new Chess();

		System.out.println(chess.getBoard());
		chess.makeMove("a2a4");
		System.out.println(chess.getBoard());
		chess.makeMove("a7a5");
		System.out.println(chess.getBoard());
	}
}
