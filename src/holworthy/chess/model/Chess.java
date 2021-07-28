package holworthy.chess.model;

import java.util.ArrayList;

import holworthy.chess.model.move.CastlingMove;
import holworthy.chess.model.move.Move;
import holworthy.chess.model.move.StandardMove;
import holworthy.chess.model.move.CastlingMove.Side;
import holworthy.chess.model.piece.King;
import holworthy.chess.model.piece.Pawn;
import holworthy.chess.model.piece.Piece;
import holworthy.chess.model.piece.Piece.Colour;

public class Chess {
	private Board board;
	private Piece.Colour whosTurn;
	private ArrayList<Move> moves;
	private ArrayList<Piece> capturedPieces;

	private boolean[] haveKingsMoved = {false, false};
	private boolean[] haveQueensideRooksMoved = {false, false};
	private boolean[] haveKingsideRooksMoved = {false, false};

	public Chess() {
		board = new Board();
		whosTurn = Colour.WHITE;
		moves = new ArrayList<>();
		capturedPieces = new ArrayList<>();
	}

	public boolean makeMove(Move move) {
		if(!board.generateMoves(whosTurn).contains(move) && !(move instanceof CastlingMove))
			return false;
		
		int colourNumber = whosTurn.ordinal();
		if(move instanceof StandardMove) {
			StandardMove standardMove = (StandardMove) move;

			if(standardMove.getFrom().getPiece() == null || standardMove.getFrom().getPiece().getColour() != whosTurn)
				return false;

			if(standardMove.getTo().getPiece() != null && standardMove.getTo().getPiece().getColour() == whosTurn)
				return false;

			Square from = standardMove.getFrom();
			Square to = standardMove.getTo();

			if(standardMove.getCapturedPiece() != null)
				capturedPieces.add(standardMove.getCapturedPiece());
			
			if(from.getPiece() instanceof King && !haveKingsMoved[colourNumber])
				haveKingsMoved[colourNumber] = true;
			if(from.getX() == 0 && (from.getY() == 0 || from.getY() == 7) && !haveQueensideRooksMoved[colourNumber])
				haveQueensideRooksMoved[colourNumber] = true;
			if(from.getX() == 7 && (from.getY() == 0 || from.getY() == 7) && !haveKingsideRooksMoved[colourNumber])
				haveKingsideRooksMoved[colourNumber] = true;

			if(standardMove.getFrom().getPiece() instanceof Pawn){
				Pawn pawn = (Pawn) standardMove.getFrom().getPiece();
				pawn.setMoved(true);
			}

			to.setPiece(from.getPiece());
			from.setPiece(null);
		} else if(move instanceof CastlingMove) {
			CastlingMove castlingMove = (CastlingMove) move;

			if(castlingMove.getSide() == Side.QUEEN && (haveKingsMoved[colourNumber] || haveQueensideRooksMoved[colourNumber]))
				return false;
			else if(castlingMove.getSide() == Side.KING && (haveKingsMoved[colourNumber] || haveKingsideRooksMoved[colourNumber]))
				return false;

			ArrayList<Square> inbetweenSquares = new ArrayList<>();
			if(whosTurn == Colour.WHITE) {
				if(castlingMove.getSide() == Side.QUEEN) {
					inbetweenSquares.add(board.getSquare(1, 7));
					inbetweenSquares.add(board.getSquare(2, 7));
					inbetweenSquares.add(board.getSquare(3, 7));
				} else {
					inbetweenSquares.add(board.getSquare(5, 7));
					inbetweenSquares.add(board.getSquare(6, 7));
				}
			} else {
				if(castlingMove.getSide() == Side.QUEEN) {
					inbetweenSquares.add(board.getSquare(1, 0));
					inbetweenSquares.add(board.getSquare(2, 0));
					inbetweenSquares.add(board.getSquare(3, 0));
				} else {
					inbetweenSquares.add(board.getSquare(5, 0));
					inbetweenSquares.add(board.getSquare(6, 0));
				}
			}

			for(Square inbetweenSquare : inbetweenSquares)
				if(inbetweenSquare.getPiece() != null)
					return false;
			
			if(isInCheck(whosTurn))
				return false;

			for(Square inbetweenSquare : inbetweenSquares) {
				for(Move enemyMove : board.generateMoves(whosTurn.other())) {
					if(move instanceof StandardMove) {
						StandardMove standardMove = (StandardMove) enemyMove;
						if(standardMove.getTo().equals(inbetweenSquare))
							return false;
					}
				}
			}

			if(whosTurn == Colour.WHITE) {
				if(castlingMove.getSide() == Side.QUEEN) {
					board.getSquare(2, 7).setPiece(board.getSquare(4, 7).getPiece());
					board.getSquare(4, 7).setPiece(null);
					board.getSquare(3, 7).setPiece(board.getSquare(0, 7).getPiece());
					board.getSquare(0, 7).setPiece(null);
					haveQueensideRooksMoved[colourNumber] = true;
				} else {
					board.getSquare(6, 7).setPiece(board.getSquare(4, 7).getPiece());
					board.getSquare(4, 7).setPiece(null);
					board.getSquare(5, 7).setPiece(board.getSquare(7, 7).getPiece());
					board.getSquare(7, 7).setPiece(null);
					haveKingsideRooksMoved[colourNumber] = true;
				}
			} else {
				if(castlingMove.getSide() == Side.QUEEN) {
					board.getSquare(2, 0).setPiece(board.getSquare(4, 0).getPiece());
					board.getSquare(4, 0).setPiece(null);
					board.getSquare(3, 0).setPiece(board.getSquare(0, 0).getPiece());
					board.getSquare(0, 0).setPiece(null);
					haveQueensideRooksMoved[colourNumber] = true;

				} else {
					board.getSquare(6, 0).setPiece(board.getSquare(4, 0).getPiece());
					board.getSquare(4, 0).setPiece(null);
					board.getSquare(5, 0).setPiece(board.getSquare(7, 0).getPiece());
					board.getSquare(7, 0).setPiece(null);
					haveKingsideRooksMoved[colourNumber] = true;
				}
			}
			haveKingsMoved[colourNumber] = true;
		}

		moves.add(move);

		if(isInCheck(whosTurn)) {
			undoMove();
			return false;
		}

		whosTurn = whosTurn.other();

		return true;
	}

	private boolean isValidRank(char rank) {
		return rank >= '1' && rank <= '8';
	}

	private boolean isValidFile(char file) {
		return file >= 'a' && file <= 'h';
	}

	public boolean makeMove(String moveString) {
		if(moveString.length() != 4) {
			return false;
		}

		char fromFile = moveString.charAt(0);
		char fromRank = moveString.charAt(1);
		char toFile = moveString.charAt(2);
		char toRank = moveString.charAt(3);

		if(!isValidFile(fromFile) || !isValidFile(toFile) || !isValidRank(fromRank) || !isValidRank(toRank)) {
			return false;
		}

		int fromX = fromFile - 'a';
		int fromY = 7 - (fromRank - '1');
		int toX = toFile - 'a';
		int toY = 7 - (toRank - '1');

		Square from = board.getSquare(fromX, fromY);
		Square to = board.getSquare(toX, toY);

		return makeMove(new StandardMove(from, to, to.getPiece()));
	}

	public void undoMove() {
		Move lastMove = moves.remove(moves.size() - 1);

		if(lastMove instanceof StandardMove) {
			StandardMove standardMove = (StandardMove) lastMove;
			Square from = standardMove.getFrom();
			Square to = standardMove.getTo();

			// TODO: set pawn moved flag to false if undone

			from.setPiece(to.getPiece());
			to.setPiece(standardMove.getCapturedPiece() == null ? null : capturedPieces.remove(capturedPieces.size() - 1));
		} else if(lastMove instanceof CastlingMove) {
			CastlingMove castlingMove = (CastlingMove) lastMove;
			int colourNumber = whosTurn.other().ordinal();
			if(whosTurn.other() == Colour.WHITE) {
				if(castlingMove.getSide() == Side.QUEEN) {
					board.getSquare(4, 7).setPiece(board.getSquare(2, 7).getPiece());
					board.getSquare(2, 7).setPiece(null);
					board.getSquare(0, 7).setPiece(board.getSquare(3, 7).getPiece());
					board.getSquare(3, 7).setPiece(null);
					haveQueensideRooksMoved[colourNumber] = false;
				} else {
					board.getSquare(4, 7).setPiece(board.getSquare(6, 7).getPiece());
					board.getSquare(6, 7).setPiece(null);
					board.getSquare(7, 7).setPiece(board.getSquare(5, 7).getPiece());
					board.getSquare(5, 7).setPiece(null);
					haveKingsideRooksMoved[colourNumber] = false;
				}
			} else {
				if(castlingMove.getSide() == Side.QUEEN) {
					board.getSquare(4, 0).setPiece(board.getSquare(2, 0).getPiece());
					board.getSquare(2, 0).setPiece(null);
					board.getSquare(0, 0).setPiece(board.getSquare(3, 0).getPiece());
					board.getSquare(3, 0).setPiece(null);
					haveQueensideRooksMoved[colourNumber] = false;

				} else {
					board.getSquare(4, 0).setPiece(board.getSquare(6, 0).getPiece());
					board.getSquare(6, 0).setPiece(null);
					board.getSquare(7, 0).setPiece(board.getSquare(5, 0).getPiece());
					board.getSquare(5, 0).setPiece(null);
					haveKingsideRooksMoved[colourNumber] = false;
				}
			}
			haveKingsMoved[colourNumber] = false;
		}

		whosTurn = whosTurn.other();
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
		chess.makeMove("a2a3");
		chess.makeMove("g8h6");
		chess.makeMove("b2b3");
		chess.makeMove("e7e6");
		chess.makeMove("c2c3");
		chess.makeMove("f8d6");
		chess.makeMove("d2d3");
		chess.makeMove(new CastlingMove(Side.KING));
		System.out.println(chess.getBoard());

		chess.undoMove();
		chess.undoMove();
		chess.undoMove();
		chess.undoMove();
		chess.undoMove();
		chess.undoMove();
		chess.undoMove();
		chess.undoMove();
		System.out.println(chess.getBoard());
	}
}
