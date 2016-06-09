package board;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import characters.GroundEntitiesManager;
import characters.PlayerKeyListener;
import characters.PlayersManager;
import map.GameMap;
import network.Client;

public class GamePanel extends JPanel implements Runnable {

	private PlayersManager players;
	private GroundEntitiesManager groundEntitiesManager;
	private GameMap map;
	private Object lock = new Object();

	public GamePanel() {
		players = Client.getPlayersManager();
		groundEntitiesManager = Client.getGroundEntitiesManager();
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
	
	public void setCurrentMap(GameMap map){
		synchronized (lock) {
			this.map = map;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		synchronized (lock) {
			if (map != null) {
				map.paintComponent(g);
				//System.out.println(map.getInteractingTilesTypes(new Rectangle(0, 0, 200, 200)));
			}
		}
		players.draw(g);
		groundEntitiesManager.draw(g);
	}

}
