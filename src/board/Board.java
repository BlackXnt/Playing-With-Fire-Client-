package board;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import characters.PlayersManager;
import network.Client;

public class Board {
	private JFrame frame;
	private static final String NAME = "Playing With Fire";
	private int width = 800;
	private int height = 640;
	private GamePanel gamePanel;
	
	
	public Board(){
		gamePanel = new GamePanel();
		Thread game = new Thread(gamePanel);
		game.start();
		frameInit();
	}
	
	private void frameInit(){
		frame = new JFrame(NAME);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(BorderLayout.CENTER, gamePanel);
		
		//TODO:remove this
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	
	public GamePanel getGamePanel(){
		return gamePanel;
	}
}
