package map;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import characters.Direction;
import characters.Entity;
import misc.Util;

public class GameMap extends JPanel implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private int id;
	private int width;
	private int height;
	private Tile[][] tiles;

	public GameMap(String name, int id, int width, int height, Tile[][] tiles) {
		this.name = name;
		this.id = id;
		this.width = width;
		this.height = height;
		this.tiles = tiles;
	}

	public GameMap(String name, int id, int width, int height) {
		this.name = name;
		this.id = id;
		this.width = width;
		this.height = height;
		tiles = new Tile[width / 32][height / 32]; // 25 20
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(TileType.Brick);
			}
		}
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Clear the board
		g.clearRect(0, 0, getWidth(), getHeight());
		int x = 0, y = 0, j;
		Tile lastTile = tiles[0][0];
		for (int i = 0; i < tiles.length; i++) {
			for (j = 0; j < tiles[i].length; j++) {
				// Upper left corner of this terrain rect
				Tile tile = tiles[i][j];
				if (tile != null) {
					tile.setTexture();
					tile.setLocation(x, y);
					tile.draw(g);
					lastTile = tile;
				}
				y += lastTile.getHeight();
			}
			x += lastTile.getWidth();
			y = 0;
		}
	}

	private Tile getTile(int x, int y, Direction direction, int shifter) {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getBounds().contains(x, y)) {
					if(direction == null){
						return tiles[i][j];
					}
					switch (direction) {
					case Up:
						if(j - shifter < 0 || j - shifter >= tiles[i].length) return null;
						return tiles[i][j - shifter];
					case Down:
						if(j + shifter < 0 || j + shifter >= tiles[i].length) return null;
						return tiles[i][j + shifter];
					case Right:
						if(i + shifter < 0 || i + shifter >= tiles.length) return null;
						return tiles[i + shifter][j];
					case Left:
						if(i - shifter < 0 || i - shifter >= tiles.length) return null;
						return tiles[i - shifter][j];
					default:
						return tiles[i][j];
					}
				}
			}
		}
		return null;
	}
	
	private Tile getTile(int x, int y, Direction direction) {
		return getTile(x, y, direction, 1);
	}
	
	private Tile getTile(int x, int y) {
		return getTile(x, y, null, 0);
	}
	
	public TileType getTileType(int x, int y, Direction direction, int shifter) {
		Tile tile = getTile(x, y, direction, shifter);
		if (tile != null) {
			return tile.getType();
		}
		return null;
	}
	
	public Set<TileType> getInteractingTilesTypes(Rectangle box){
		Set<TileType> interactingTiles = new HashSet<>();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getBounds().intersects(box)) {
					interactingTiles.add(tiles[i][j].getType());
				}
			}
		}
		return interactingTiles;
	}
	
	public TileType getTileType(int x, int y, Direction direction) {
		return getTileType(x, y, direction, 1);
	}
	
	public TileType getTileType(int x, int y) {
		return getTileType(x, y, null, 0);
	}

	public void setTileType(int x, int y, TileType type, Direction direction, int shifter) {
		Tile tile = getTile(x, y, direction, shifter);
		if (tile != null) {
			tile.setType(type);
		}
	}
	
	public void setTileType(int x, int y, TileType type, Direction direction) {
		setTileType(x, y, type, direction, 1);
	}
	
	public void setTileType(int x, int y, TileType type) {
		setTileType(x, y, type, null, 0);
	}
	
	public boolean isTileDaenerys(int x, int y, Direction direction, int shifter){
		Tile tile = getTile(x, y, direction, shifter);
		return tile != null && tile.isDaenerys();
	}
	
	public boolean isTileDaenerys(int x, int y, Direction direction){
		return isTileDaenerys(x, y, direction, 1);
	}
	
	public boolean isTileDaenerys(int x, int y){
		return isTileDaenerys(x, y, null, 0);
	}
	
	public boolean isTileSolid(int x, int y, Direction direction, int shifter){
		Tile tile = getTile(x, y, direction, shifter);
		return tile != null && tile.isSolid();
	}
	
	public boolean isTileSolid(int x, int y, Direction direction){
		return isTileSolid(x, y, direction, 1);
	}
	
	public boolean isTileSolid(int x, int y){
		return isTileSolid(x, y, null, 0);
	}
	
}
