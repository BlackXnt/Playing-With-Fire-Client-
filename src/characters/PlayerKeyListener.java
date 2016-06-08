package characters;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import network.Client;
import network.NetworkMessenger;

public class PlayerKeyListener implements KeyListener {
	private Player player;
	private NetworkMessenger networkMessenger;

	public PlayerKeyListener(NetworkMessenger networkMessenger) {
		this.networkMessenger = networkMessenger;
		System.out.println(1);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		Player player = Client.getPlayersManager().getMyPlayer();
		if(player != null){
			networkMessenger.keyPressed(key, player.getId());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		Player player = Client.getPlayersManager().getMyPlayer();
		if(player != null){
			networkMessenger.keyReleased(key, player.getId());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
