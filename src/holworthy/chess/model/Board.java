package holworthy.chess.model;

import java.util.ArrayList;

import holworthy.chess.model.move.CastlingMove;
import holworthy.chess.model.move.EnPassantMove;
import holworthy.chess.model.move.CastlingMove.Side;
import holworthy.chess.model.move.Move;
import holworthy.chess.model.move.StandardMove;
import holworthy.chess.model.piece.Bishop;
import holworthy.chess.model.piece.King;
import holworthy.chess.model.piece.Knight;
import holworthy.chess.model.piece.Pawn;
import holworthy.chess.model.piece.Piece;
import holworthy.chess.model.piece.Piece.Colour;
import holworthy.chess.model.piece.Queen;
import holworthy.chess.model.piece.Rook;

public class Board {
	private Square[][] squares;
	private Piece.Colour whosTurn;
	private ArrayList<Move> moves;
	private ArrayList<Piece> capturedPieces;

	public Board() {
		squares = new Square[8][8];
		whosTurn = Colour.WHITE;
		moves = new ArrayList<>();
		capturedPieces = new ArrayList<>();

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

	// TODO: en passant
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

	private Square kingSquare(Piece.Colour colour) {
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				Square square = getSquare(x, y);
				Piece piece = square.getPiece();
				if(piece != null && piece.getColour() == colour && piece instanceof King)
					return square;
			}
		}
		return null;
	}

	public boolean isInCheck(Piece.Colour colour) {
		ArrayList<Move> moves = generateMoves(colour.other());
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

	public ArrayList<Move> getMoves(){
		return moves;
	}

	public boolean makeMove(Move move) {
		if(!generateMoves(whosTurn).contains(move) && !(move instanceof CastlingMove) && !(move instanceof EnPassantMove))
			return false;
		
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

			from.getPiece().setMoved(true);

			to.setPiece(from.getPiece());
			from.setPiece(null);
		} else if(move instanceof CastlingMove) {
			CastlingMove castlingMove = (CastlingMove) move;

			if(!isCastlingValid(whosTurn, castlingMove.getSide()))
				return false;

			getSquare(castlingMove.getSide() == Side.QUEEN ? 0 : 7, whosTurn == Colour.BLACK ? 0 : 7).getPiece().setMoved(true);
			getSquare(4, whosTurn == Colour.BLACK ? 0 : 7).getPiece().setMoved(true);
			if(whosTurn == Colour.WHITE) {
				if(castlingMove.getSide() == Side.QUEEN) {
					getSquare(2, 7).setPiece(getSquare(4, 7).getPiece());
					getSquare(4, 7).setPiece(null);
					getSquare(3, 7).setPiece(getSquare(0, 7).getPiece());
					getSquare(0, 7).setPiece(null);
				} else {
					getSquare(6, 7).setPiece(getSquare(4, 7).getPiece());
					getSquare(4, 7).setPiece(null);
					getSquare(5, 7).setPiece(getSquare(7, 7).getPiece());
					getSquare(7, 7).setPiece(null);
				}
			} else {
				if(castlingMove.getSide() == Side.QUEEN) {
					getSquare(2, 0).setPiece(getSquare(4, 0).getPiece());
					getSquare(4, 0).setPiece(null);
					getSquare(3, 0).setPiece(getSquare(0, 0).getPiece());
					getSquare(0, 0).setPiece(null);

				} else {
					getSquare(6, 0).setPiece(getSquare(4, 0).getPiece());
					getSquare(4, 0).setPiece(null);
					getSquare(5, 0).setPiece(getSquare(7, 0).getPiece());
					getSquare(7, 0).setPiece(null);
				}
			}
		} 
		else if (move instanceof EnPassantMove){
			EnPassantMove enPassantMove = (EnPassantMove) move;
			if (enPassantMove.getFrom().getPiece() == null || enPassantMove.getFrom().getPiece().getColour() != whosTurn)
				return false;
			if (enPassantMove.getTo().getPiece() != null)
				return false;
			
			
			

			Square from = enPassantMove.getFrom();
			isEnPassantValid(from);
			
			Square to = enPassantMove.getTo();
			Square captured = enPassantMove.getCaptured();

			capturedPieces.add(captured.getPiece());
			captured.setPiece(null);

			to.setPiece(from.getPiece());
			from.setPiece(null);
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

		Square from = getSquare(fromX, fromY);
		Square to = getSquare(toX, toY);

		return makeMove(new StandardMove(from, to));
	}

	public boolean isInCheckmate(Piece.Colour colour) {
		if(!isInCheck(colour))
			return false;

		for(Move move : generateMoves(colour.other())) {
			makeMove(move);
			if(!isInCheck(colour)) {
				undoMove();
				return false;
			}
			undoMove();
		}

		return true;
	}

	public boolean isCastlingValid(Colour colour, Side side) {
		Square kingSquare = getSquare(4, colour == Colour.BLACK ? 0 : 7);
		Square rookSquare = getSquare(side == Side.QUEEN ? 0 : 7, colour == Colour.BLACK ? 0 : 7);
		if(kingSquare.getPiece() == null || !(kingSquare.getPiece() instanceof King) || kingSquare.getPiece().getColour() != colour || kingSquare.getPiece().getMoved())
			return false;
		if(rookSquare.getPiece() == null || !(rookSquare.getPiece() instanceof Rook) || rookSquare.getPiece().getColour() != colour || rookSquare.getPiece().getMoved())
			return false;

		ArrayList<Square> inbetweenSquares = new ArrayList<>();
		if(colour == Colour.WHITE) {
			if(side == Side.QUEEN) {
				inbetweenSquares.add(getSquare(1, 7));
				inbetweenSquares.add(getSquare(2, 7));
				inbetweenSquares.add(getSquare(3, 7));
			} else {
				inbetweenSquares.add(getSquare(5, 7));
				inbetweenSquares.add(getSquare(6, 7));
			}
		} else {
			if(side == Side.QUEEN) {
				inbetweenSquares.add(getSquare(1, 0));
				inbetweenSquares.add(getSquare(2, 0));
				inbetweenSquares.add(getSquare(3, 0));
			} else {
				inbetweenSquares.add(getSquare(5, 0));
				inbetweenSquares.add(getSquare(6, 0));
			}
		}

		for(Square inbetweenSquare : inbetweenSquares)
			if(inbetweenSquare.getPiece() != null)
				return false;
		
		if(isInCheck(colour))
			return false;

		for(Square inbetweenSquare : inbetweenSquares) {
			for(Move enemyMove : generateMoves(colour.other())) {
				if(enemyMove instanceof StandardMove) {
					StandardMove standardMove = (StandardMove) enemyMove;
					if(standardMove.getTo().equals(inbetweenSquare))
						return false;
				}
			}
		}

		return true;
	}

	public boolean isEnPassantValid(Square from){
		Square leftSquare = this.getSquare(from.getX() - 1, from.getY());
		Square rightSquare = this.getSquare(from.getX() + 1, from.getY());
		if (from.getPiece().getColour() == Colour.WHITE){
			// enpassant left white
			if (leftSquare != null){
				if (leftSquare.getPiece() instanceof Pawn && leftSquare.getPiece().getColour() == Colour.BLACK){
					if (this.getMoves().get(this.getMoves().size() - 1) instanceof StandardMove){
						StandardMove move = (StandardMove) this.getMoves().get(this.getMoves().size() - 1);
						if (move.getMovedPiece() == leftSquare.getPiece())
							if (move.getTo().getY() - move.getFrom().getY() == 2)
								return true;
					}
				}
			}
			
			// enpassant right white
			if (rightSquare != null){
				if (rightSquare.getPiece() instanceof Pawn && rightSquare.getPiece().getColour() == Colour.BLACK){
					if (this.getMoves().get(this.getMoves().size() - 1) instanceof StandardMove){
						StandardMove move = (StandardMove) this.getMoves().get(this.getMoves().size() - 1);
						if (move.getMovedPiece() == rightSquare.getPiece())
							if (move.getTo().getY() - move.getFrom().getY() == 2)
								return true;
					}
				}
			}
		}
		else {
			// enpassant left Black
			if (leftSquare != null){
				if (leftSquare.getPiece() instanceof Pawn && leftSquare.getPiece().getColour() == Colour.WHITE){
					if (this.getMoves().get(this.getMoves().size() - 1) instanceof StandardMove){
						StandardMove move = (StandardMove) this.getMoves().get(this.getMoves().size() - 1);
						if (move.getMovedPiece() == leftSquare.getPiece())
							if (move.getTo().getY() - move.getFrom().getY() == -2)
								return true;
					}
				}
			}
			
			// enpassant right Balck
			if (rightSquare != null){
				if (rightSquare.getPiece() instanceof Pawn && rightSquare.getPiece().getColour() == Colour.WHITE){
					if (this.getMoves().get(this.getMoves().size() - 1) instanceof StandardMove){
						StandardMove move = (StandardMove) this.getMoves().get(this.getMoves().size() - 1);
						if (move.getMovedPiece() == rightSquare.getPiece())
							if (move.getTo().getY() - move.getFrom().getY() == -2)
								return true;
					}
				}
			}
		}
		return false;
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
			if(whosTurn.other() == Colour.WHITE) {
				if(castlingMove.getSide() == Side.QUEEN) {
					getSquare(4, 7).setPiece(getSquare(2, 7).getPiece());
					getSquare(2, 7).setPiece(null);
					getSquare(0, 7).setPiece(getSquare(3, 7).getPiece());
					getSquare(3, 7).setPiece(null);
				} else {
					getSquare(4, 7).setPiece(getSquare(6, 7).getPiece());
					getSquare(6, 7).setPiece(null);
					getSquare(7, 7).setPiece(getSquare(5, 7).getPiece());
					getSquare(5, 7).setPiece(null);
				}
			} else {
				if(castlingMove.getSide() == Side.QUEEN) {
					getSquare(4, 0).setPiece(getSquare(2, 0).getPiece());
					getSquare(2, 0).setPiece(null);
					getSquare(0, 0).setPiece(getSquare(3, 0).getPiece());
					getSquare(3, 0).setPiece(null);
				} else {
					getSquare(4, 0).setPiece(getSquare(6, 0).getPiece());
					getSquare(6, 0).setPiece(null);
					getSquare(7, 0).setPiece(getSquare(5, 0).getPiece());
					getSquare(5, 0).setPiece(null);
				}
			}
			Square kingSquare = getSquare(5, whosTurn == Colour.BLACK ? 0 : 7);
			Square rookSquare = getSquare(castlingMove.getSide() == Side.QUEEN ? 0 : 7, whosTurn == Colour.BLACK ? 0 : 7);
			kingSquare.getPiece().setMoved(false);
			rookSquare.getPiece().setMoved(false);
		}

		whosTurn = whosTurn.other();
	}

	public static void main(String[] args) {
		Board board = new Board();
		System.out.println(board);
	}
}
