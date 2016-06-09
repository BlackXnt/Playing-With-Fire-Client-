package network;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.net.Socket;

import board.GamePanel;
import map.GameMap;

public class NetworkListener implements Runnable{
	private Socket socket;

	public NetworkListener(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			Object serverObject = null;
			while (true) {
				try {
					serverObject = in.readObject();
					if (serverObject instanceof String) {
						CommandWrapper cw = new CommandWrapper(((String) serverObject));
						cw.execute();
					} else if (serverObject instanceof GameMap) {
						//System.out.println(10000);
						if(Client.getBoard() != null){
							Client.getBoard().getGamePanel().setCurrentMap(((GameMap) serverObject));
							//System.out.println(((GameMap) serverObject).getInteractingTilesTypes(new Rectangle(0, 0, 200, 200)));
						}
					}
				} catch (OptionalDataException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("Disconnected from server");
		System.exit(0);
	}
}
