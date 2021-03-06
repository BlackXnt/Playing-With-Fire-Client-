package network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import board.Board;
import characters.GroundEntitiesManager;
import characters.PlayersManager;

public class Client {
	private Socket socket;
	private BufferedWriter out;
	private static PlayersManager playersManager;
	private static NetworkMessenger networkMessenger;
	private static GroundEntitiesManager groundEntitiesManager;
	private static Board board;

	public Client(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			playersManager = new PlayersManager();
			groundEntitiesManager = new GroundEntitiesManager();
			networkMessenger = new NetworkMessenger(this);
			Thread read = new Thread(new NetworkListener(socket));
			read.start();
			board = new Board();
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to server.");
			System.exit(1);
		}
	}

	public static NetworkMessenger getNetworkMessenger() {
		return networkMessenger;
	}

	public static PlayersManager getPlayersManager() {
		return playersManager;
	}
	
	public static GroundEntitiesManager getGroundEntitiesManager() {
		return groundEntitiesManager;
	}
	
	public static Board getBoard(){
		return board;
	}
	
	public void send(String line) {
		try {
			out.write(line + "\n");
			out.flush();
		} catch (IOException e) {
			System.err.println("Couldnt read or write");
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client("localhost", 23351);
	}
}
