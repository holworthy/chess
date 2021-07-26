package holworthy.chess.model;

import java.util.ArrayList;

import holworthy.chess.model.move.CastlingMove;
import holworthy.chess.model.move.Move;
import holworthy.chess.model.move.StandardMove;
import holworthy.chess.model.piece.King;
import holworthy.chess.model.piece.Piece;

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
		if(!board.generateMoves(whosTurn).contains(move)) {
			return;
		}
		
		if(move instanceof StandardMove) {
			StandardMove standardMove = (StandardMove) move;
			if(standardMove.getFrom().getPiece() == null || standardMove.getFrom().getPiece().getColour() != whosTurn) {
				// TODO: can only move own pieces
				return;
			}

			if(standardMove.getTo().getPiece() != null && standardMove.getTo().getPiece().getColour() == whosTurn) {
				// TODO: cannot move onto own piece
				return;
			}

			Square from = standardMove.getFrom();
			Square to = standardMove.getTo();

			capturedPieces.add(to.getPiece());
			to.setPiece(from.getPiece());
			from.setPiece(null);
		} else if(move instanceof CastlingMove) {
			// TODO: do castling
			return;
		}

		if(isInCheck(whosTurn)) {
			undoMove();
			// TODO: announce check issue
			return;
		}

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

		makeMove(new StandardMove(board.getSquare(fromX, fromY), board.getSquare(toX, toY)));
	}

	public void undoMove() {
		Move lastMove = moves.remove(moves.size() - 1);

		if(lastMove instanceof StandardMove) {
			StandardMove standardMove = (StandardMove) lastMove;
			Square from = standardMove.getFrom();
			Square to = standardMove.getTo();
			from.setPiece(to.getPiece());
			to.setPiece(capturedPieces.remove(capturedPieces.size() - 1));
		} else if(lastMove instanceof CastlingMove) {

		}
	}

	public Board getBoard() {
		return board;
	}

	private Square kingSquare(Piece.Colour colour) {
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				Square square = board.getSquare(x, y);
				Piece piece = square.getPiece();
				if(piece != null && piece.getColour() == colour && piece instanceof King)
					return square;
			}
		}
		return null;
	}

	public boolean isInCheck(Piece.Colour colour) {
		ArrayList<Move> moves = board.generateMoves(colour.other());
		Square kingSquare = kingSquare(colour);

		for(Move move : moves) {
			if(move instanceof StandardMove) {
				StandardMove standardMove = (StandardMove) move;
				if(standardMove.getTo() == kingSquare)
					return true;
			}
		}

		return false;
	}

	public boolean isInCheckmate(Piece.Colour colour) {
		if(!isInCheck(colour))
			return false;

		for(Move move : board.generateMoves(whosTurn)) {
			makeMove(move);
			if(!isInCheck(colour)) {
				undoMove();
				return false;
			}
			undoMove();
		}

		return true;
	}

	public static void main(String[] args) {
		Chess chess = new Chess();

		System.out.println(chess.getBoard());
		chess.makeMove("a2a4");
		System.out.println(chess.getBoard());
		// chess.makeMove("a7a5");
		// System.out.println(chess.getBoard());
	}
}
