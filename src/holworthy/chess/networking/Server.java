package holworthy.chess.networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import holworthy.chess.model.Board;
import holworthy.chess.model.piece.Piece.Colour;
import holworthy.chess.networking.listener.ClientHandlerListener;
import holworthy.chess.networking.listener.OnStartErrorListener;
import holworthy.chess.networking.listener.OnStartListener;
import holworthy.chess.networking.listener.ServerListener;

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
	private ServerListener serverListener;
	private ClientHandler whiteClientHandler;
	private ClientHandler blackClientHandler;
	private boolean sentWhiteName = false;
	private boolean sentBlackName = false;
	
	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setServerListener(ServerListener serverListener) {
		this.serverListener = serverListener;
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
		private ClientHandlerListener clientHandlerListener;

		public ClientHandler(Socket socket, Colour colour) {
			this.socket = socket;
			this.colour = colour;
		}

		public void setClientHandlerListener(ClientHandlerListener clientHandlerListener) {
			this.clientHandlerListener = clientHandlerListener;
		}

		public void start() {
			Thread thread = new Thread(this);
			thread.start();
		}

		private void sendColour() throws IOException {
			dataOutputStream.writeInt(colour.ordinal());
		}

		public void sendName(String name) throws IOException {
			byte[] nameBytes = name.getBytes();
			int nameLength = nameBytes.length;
			dataOutputStream.writeInt(nameLength);
			dataOutputStream.write(nameBytes);
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

			try {
				int nameLength = dataInputStream.readInt();
				byte[] nameBytes = dataInputStream.readNBytes(nameLength);
				String name = new String(nameBytes);
				if(colour == Colour.WHITE) {
					blackClientHandler.sendName(name);
					sentWhiteName = true;
				} else {
					whiteClientHandler.sendName(name);
					sentBlackName = true;
				}

				System.out.println(clientHandlerListener);

				if(clientHandlerListener != null)
					clientHandlerListener.onNameRecieved(name);
			} catch(IOException e) {

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
			if(serverListener != null)
				serverListener.onStartError();
			return;
		}

		if(serverListener != null)
			serverListener.onStart();

		Socket whiteSocket;
		Socket blackSocket;
		try {
			whiteSocket = serverSocket.accept();
			blackSocket = serverSocket.accept();
		} catch(IOException e) {
			// TODO: should probably handle this
			return;
		}
		
		whiteClientHandler = new ClientHandler(whiteSocket, Colour.WHITE);
		blackClientHandler = new ClientHandler(blackSocket, Colour.BLACK);

		whiteClientHandler.setClientHandlerListener(new ClientHandlerListener() {
			@Override
			public void onNameRecieved(String name) {
				System.out.println("white set name to " + name);
			}
		});
		blackClientHandler.setClientHandlerListener(new ClientHandlerListener() {
			@Override
			public void onNameRecieved(String name) {
				System.out.println("black set name to " + name);
			}
		});

		whiteClientHandler.start();
		blackClientHandler.start();

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
