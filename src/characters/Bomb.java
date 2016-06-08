package characters;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import board.GamePanel;
import map.GameMap;
import map.Location;
import map.TileType;
import misc.Util;

public class Bomb extends Entity {

	static final int rows = 4;
	static final int cols = 2;
	static final Image[][] picts = Util.splitSprites(new ImageIcon("images/dynamite.png"), rows, cols);
	private static final int bombHeight = picts[0][0].getHeight(null);
	private static final int bombWidth = picts[0][0].getWidth(null);

	private int row;
	private int col;

	//The player that placed the bomb
	private Player placer; 
	
	// The GamePanel and the map the bomb is in.
	private GamePanel gamePanel;
	private GameMap map;

	private static final int CYCLES_TO_DETONATE = 100;
	private static final int CYCLES_TO_INVALIDATION = 120;
	private int cyclesPerChange = CYCLES_TO_DETONATE / (rows * cols);
	private int changesCompleted = 1;
	private int currentCycle;
	private boolean exploded = false;
	private int power = 4;

	public Bomb(int x, int y, Player placer, GamePanel gamePanel) {
		super(x, y, bombWidth, bombHeight);
		this.gamePanel = gamePanel;
		this.placer = placer;
		map = gamePanel.getCurrentMap();
	}
	
	public Bomb(Location location, Player placer, GamePanel gamePanel) {
		super(location, bombWidth, bombHeight);
		this.gamePanel = gamePanel;
		this.placer = placer;
		map = gamePanel.getCurrentMap();
	}

	@Override
	public void tick() {
		if (currentCycle <= CYCLES_TO_DETONATE) {
			if (currentCycle - (cyclesPerChange * changesCompleted) >= 0 && changesCompleted != rows * cols) {
				changesCompleted++;
				if (col == (cols - 1)) {
					row++;
					col = 0;
				} else {
					col++;
				}
			}
		}
		currentCycle++;
	}

	@Override
	public void draw(Graphics g) {
		if (exploded == false && valid == true) {
			g.drawImage(picts[row][col], location.getX(), location.getY(), picts[row][col].getWidth(null) * 2,
					picts[row][col].getHeight(null) * 2, null); // 16 27
		}
	}

}
