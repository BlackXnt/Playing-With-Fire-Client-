package network;

import characters.Direction;
import characters.Player;
import characters.PlayersManager;

public class CommandWrapper {

	private String command;
	private PlayersManager playersManager;

	public CommandWrapper(String command) {
		this.command = command;
		this.playersManager = Client.getPlayersManager();
	}

	public void execute() {
		String[] content = command.split("\\|");
		if (content[0].equals("player")) {
			switch (content[1]) {
			case "init":
				playersManager.setMyPlayerId(Integer.parseInt(content[2]));
				break;
			case "draw":
				Player player = playersManager.getPlayerById(Integer.parseInt(content[2]));
				Direction direction = null;
				if(!content[3].equals("null")){
					direction = Direction.valueOf(content[3]);
				}
				if(player == null){
					playersManager.addPlayer(new Player(Integer.parseInt(content[4]), Integer.parseInt(content[5]), direction, Integer.parseInt(content[2])));
				} else {
					player.move(Integer.parseInt(content[4]), Integer.parseInt(content[5]), direction);
				}
				break;
			default:
				break;
			}
		} 
	}
}
