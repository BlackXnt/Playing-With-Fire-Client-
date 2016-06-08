package network;

public class NetworkMessenger {
	private Client client;

	public NetworkMessenger(Client client) {
		this.client = client;
	}

	public void keyPressed(int key, int playerId){	
		client.send("key|" + playerId + "|pressed|" + key);
	}

	public void keyReleased(int key, int playerId) {
		client.send("key|" + playerId + "|released|" + key);
	}
}
