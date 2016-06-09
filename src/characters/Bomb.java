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

	private static final int CYCLES_TO_DETONATE = 100;
	private static final int CYCLES_TO_INVALIDATION = 120;
	private int cyclesPerChange = CYCLES_TO_DETONATE / (rows * cols);
	private int changesCompleted = 1;
	private int currentCycle;

	public Bomb(int x, int y) {
		super(x, y, bombWidth, bombHeight);
	}
	
	public Bomb(Location location) {
		super(location, bombWidth, bombHeight);
	}

	@Override
	public void tick() {
		System.out.println(4000);
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
		} else if(currentCycle >= CYCLES_TO_INVALIDATION){
			valid = false;
		}
		currentCycle++;
	}

	@Override
	public void draw(Graphics g) {
		if (valid == true) {
			g.drawImage(picts[row][col], location.getX(), location.getY(), picts[row][col].getWidth(null) * 2,
					picts[row][col].getHeight(null) * 2, null); // 16 27
		}
	}

}
