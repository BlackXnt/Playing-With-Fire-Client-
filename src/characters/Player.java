package characters;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

import board.GamePanel;
import map.GameMap;
import map.Location;
import map.TileType;
import misc.Util;
import network.Client;

public class Player extends MovingEntity{

	static final int rows = 4;
	static final int cols = 3;
	static final Image[][] picts = Util.splitSprites(new ImageIcon("images/bad1.png"), rows, cols);
	private static final int playerHeight = 27;
	private static final int playerWidth = 27;

	private int row;
	private int col;
	private int id;

	public Player(int x, int y, Direction direction, int id) {
		super(x, y, playerWidth, playerHeight, direction);
		this.id = id;
	}
	
	public Player(Location location, Direction direction, int id) {
		super(location, playerWidth, playerHeight, direction);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void move(int x, int y, Direction direction){
		location.setX(x);
		location.setY(y);
		this.direction = direction;
	}
	
	@Override
	public void tick() {
		if (direction != null) {
			switch (direction) {
			case Up:
				row = 3;
				break;
			case Down:
				row = 0;
				break;
			case Right:
				row = 2;
				break;
			case Left:
				row = 1;
				break;
			}
			col++;
			col = col % cols;
		}
/*		if(Client.getBoard() != null){
			System.out.println(Client.getBoard().getGamePanel());
			if(Client.getBoard().getGamePanel() != null){
				System.out.println(Client.getBoard().getGamePanel().getCurrentMap());
			}
		}*/
/*		if(Client.getBoard() != null && Client.getBoard().getGamePanel() != null && Client.getBoard().getGamePanel().getCurrentMap() != null){
			System.out.println(Client.getBoard().getGamePanel().getCurrentMap().getInteractingTilesTypes(getBounds()));
		}*/
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(picts[row][col], location.getX(), location.getY(), null);
	}
}
