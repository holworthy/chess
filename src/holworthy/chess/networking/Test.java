package holworthy.chess.networking;

import holworthy.chess.networking.listener.ServerListener;

public class Test {
	public static void main(String[] args) {
		Server server = new Server();
		server.setPort(2000);
		server.setServerListener(new ServerListener() {
			@Override
			public void onStart() {
				System.out.println("server started");

				Client whiteClient = new Client("localhost", 2000);
				whiteClient.setName("whiteClient");
				Client blackClient = new Client("localhost", 2000);
				blackClient.setName("blackClient");

				whiteClient.connect();
				blackClient.connect();

				whiteClient.makeMove()
			}

			@Override
			public void onStartError() {
				System.out.println("server start error");
			}
		});
		server.start();
	}
}
