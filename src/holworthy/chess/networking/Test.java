package holworthy.chess.networking;

public class Test {
	public static void main(String[] args) {
		Server server = new Server();
		server.setPort(2000);
		server.setOnStartListener(() -> {
			System.out.println("server started");

			Client whiteClient = new Client("localhost", 2000);
			Client blackClient = new Client("localhost", 2000);

			whiteClient.connect();
			blackClient.connect();
		});
		server.setOnStartErrorListener(() -> {
			System.out.println("server start error");
		});
		server.start();
	}
}
