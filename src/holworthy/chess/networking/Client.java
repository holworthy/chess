package holworthy.chess.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import holworthy.chess.model.Chess;
import holworthy.chess.model.move.CastlingMove;
import holworthy.chess.model.move.EnPassantMove;
import holworthy.chess.model.move.Move;
import holworthy.chess.model.move.PromotionMove;
import holworthy.chess.model.move.StandardMove;
import holworthy.chess.model.piece.Piece;
import holworthy.chess.model.piece.Piece.Colour;

public class Client implements Runnable{
	private Thread thread;
	private String serverHost;
	private int serverPort;
	private String name;
	private Chess chess;
	private Colour colour;
	private Socket socket;
	private DataInputStream dIS;
	private DataOutputStream dOS;

	Client(String serverHost, int serverPort){
		this.thread = new Thread(this, "client thread");
		this.serverHost = serverHost;
		this.serverPort = serverPort;
		this.chess = new Chess();
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Chess getChess() {
		return chess;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public void connect(){
		thread.start();
	}

	@Override
	public void run() {
		try {
			socket = new Socket(serverHost, serverPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dIS = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dOS = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			int colourValue = dIS.readInt();
			colour = Colour.values()[colourValue];
			System.out.println("i am " + colour);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			byte[] nameBytes = name.getBytes();
			int nameLength = nameBytes.length;
			dOS.writeInt(nameLength);
			dOS.write(nameBytes);
		} catch(Exception e) {

		}
	}

	private void sendPiece(Piece piece) throws IOException {
		dOS.writeInt(piece.getColour().ordinal());
		dOS.writeInt(piece.getType());
	}

	private void sendStandardMove(StandardMove standardMove) throws IOException {
		dOS.writeInt(standardMove.getFrom().getX());
		dOS.writeInt(standardMove.getFrom().getY());
		dOS.writeInt(standardMove.getTo().getX());
		dOS.writeInt(standardMove.getTo().getY());
		sendPiece(standardMove.getMovedPiece());
		sendPiece(standardMove.getCapturedPiece());
	}

	private void sendPromotionMove(PromotionMove promotionMove) throws IOException {
		sendStandardMove(promotionMove);
		sendPiece(promotionMove.getPromotionPiece());
	}

	private void sendCastlingMove(CastlingMove castlingMove) throws IOException {
		dOS.writeInt(castlingMove.getSide().ordinal());
	}

	private void sendEnPassantMove(EnPassantMove enPassantMove) throws IOException {
		sendStandardMove(enPassantMove);
		dOS.writeInt(enPassantMove.getCaptured().getX());
		dOS.writeInt(enPassantMove.getCaptured().getY());
	}

	public void sendMove(Move move) throws IOException {
		dOS.writeInt(move.getType());
		if(move.getClass() == StandardMove.class)
			sendStandardMove((StandardMove) move);
		else if(move.getClass() == PromotionMove.class)
			sendPromotionMove((PromotionMove) move));
		else if(move.getClass() == CastlingMove.class)
			sendCastlingMove((CastlingMove) move);
		else if(move.getClass() == EnPassantMove.class)
			sendEnPassantMove((EnPassantMove) move);
	}

	// server host 
	// server port
	// chess
	// colour

	// send move
	// recv move
	
	// 0(String name)
	// 1(Move move)

	// onConnect
	// onConnectError
	// onRecieveColour
	// onRecieveOpponentName
	// onGameStart
	// onRecieveInvalidMove
	// onRecieveMove
	// onGameEnd
}
