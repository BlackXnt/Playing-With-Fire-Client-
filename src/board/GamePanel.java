package board;

import java.awt.Graphics;

import javax.swing.JPanel;

import characters.Bomb;
import characters.Direction;
import characters.Player;
import characters.PlayerKeyListener;
import characters.PlayersManager;
import map.GameMap;
import network.Client;

public class GamePanel extends JPanel implements Runnable {

	private static PlayersManager players;
	private static GameMap map;

	public GamePanel() {
		players = Client.getPlayersManager();
		this.setFocusable(true);
		this.addKeyListener(new PlayerKeyListener(Client.getNetworkMessenger()));
	}

	@Override
	public void run() {
		while (true) {
			players.update();
			repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static GameMap getCurrentMap(){
		return null;
	}
	
	public static void setCurrentMap(GameMap map){
		GamePanel.map = map;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(map != null){
			map.paintComponent(g);
		}
		players.draw(g);
	}

}
