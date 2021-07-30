package holworthy.chess.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import holworthy.chess.model.Board;
import holworthy.chess.model.piece.Piece.Colour;
import holworthy.chess.networking.listener.OnStartErrorListener;
import holworthy.chess.networking.listener.OnStartListener;

public class Server implements Runnable {
	// send name?
	// recv move + validate + send to both
	
	// send oponent name 1(String name)
	// invalid move 2 
	// valid move 3(Move move)
	// win/loss 4(boolean win)

	// onGameStart
	// onRecieveMove
	// onGameEnd

	private int port;
	private OnStartListener onStartListener;
	private OnStartErrorListener onStartErrorListener;
	
	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setOnStartListener(OnStartListener onStartListener) {
		this.onStartListener = onStartListener;
	}

	public void setOnStartErrorListener(OnStartErrorListener onStartErrorListener) {
		this.onStartErrorListener = onStartErrorListener;
	}

	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	private class ClientHandler implements Runnable {
		private Socket socket;
		private Colour colour;
		private DataInputStream dataInputStream;
		private DataOutputStream dataOutputStream;

		public ClientHandler(Socket socket, Colour colour) {
			this.socket = socket;
			this.colour = colour;
			Thread thread = new Thread(this);
			thread.start();
		}

		private void sendColour() throws IOException {
			dataOutputStream.writeInt(colour.ordinal());
		}

		public void sendGameEnd(boolean hasWon) throws IOException {
			dataOutputStream.writeInt(4);
			dataOutputStream.writeBoolean(hasWon);
		}

		@Override
		public void run() {
			try {
				dataInputStream = new DataInputStream(socket.getInputStream());
				dataOutputStream = new DataOutputStream(socket.getOutputStream());
			} catch(IOException e) {
				// TODO
				return;
			}
			
			try {
				sendColour();
			} catch(IOException e) {
				// TODO
				return;
			}

			// TODO
		}
	}

	@Override
	public void run() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
		} catch(IOException e) {
			if(onStartErrorListener != null)
				onStartErrorListener.onStartError();
			return;
		}

		if(onStartListener != null)
			onStartListener.onStart();

		Socket whiteSocket;
		Socket blackSocket;
		try {
			whiteSocket = serverSocket.accept();
			blackSocket = serverSocket.accept();
		} catch(IOException e) {
			// TODO: should probably handle this
			return;
		}
		
		ClientHandler whiteClientHandler = new ClientHandler(whiteSocket, Colour.WHITE);
		ClientHandler blackClientHandler = new ClientHandler(blackSocket, Colour.BLACK);

		// TODO
		Board board = new Board();
		while(true) {

			if(board.isInCheckmate(Colour.WHITE)) {
				try {
					whiteClientHandler.sendGameEnd(false);
					blackClientHandler.sendGameEnd(true);
				} catch(IOException e) {
					// TODO
				}
				break;
			} else if(board.isInCheckmate(Colour.BLACK)) {
				try {
					whiteClientHandler.sendGameEnd(true);
					blackClientHandler.sendGameEnd(false);
				} catch(IOException e) {
					// TODO
				}
				break;	
			}
		}

		try {
			serverSocket.close();
		} catch(IOException e) {
			
		}

		// TODO: onStop
	}
}
