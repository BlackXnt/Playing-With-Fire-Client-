package characters;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class PlayersManager {
	private int myPlayerId;
	private List<Player> players;
	private int numberOfPlayers;
	
	public PlayersManager(){
		players = new ArrayList<Player>();
	}
	
	public PlayersManager(int myPlayerId){
		this.myPlayerId = myPlayerId;
		players = new ArrayList<Player>();
	}
	
	public PlayersManager(Player myPlayer){
		this.myPlayerId = myPlayer.getId();
		players = new ArrayList<Player>();
		addPlayer(myPlayer);
	}
	
	public Player getMyPlayer(){
		for(Player player: players){
			if(player.getId() == myPlayerId){
				return player;
			}
		}
		return null;
	}
	
	public void setMyPlayerId(int myPlayerId){
		this.myPlayerId = myPlayerId;
	}
	
	public boolean addPlayer(Player player){
		if(players.add(player)){
			numberOfPlayers++;
			return true;
		}
		return false;
	}
	
	public Player getPlayerById(int id) {
		for (Player player : players) {
			if (player.getId() == id) {
				return player;
			}
		}
		return null;
	}

	public void draw(Graphics g) {
		for (Player player : players) {
			player.draw(g);
		}
	}
	
	public void update(){
		for(Player player : players){
			player.tick();
		}
	}
	
}
