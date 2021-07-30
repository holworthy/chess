package holworthy.chess.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import holworthy.chess.model.Chess;
import holworthy.chess.model.piece.Piece.Colour;

public class Client implements Runnable{
	private Thread thread;
	private String serverHost;
	private int serverPort;
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
