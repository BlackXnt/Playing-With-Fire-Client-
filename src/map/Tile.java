package map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.ImageIcon;

import misc.Util;

public class Tile implements Serializable{
	private static final long serialVersionUID = 1L;
	private Location location;
	private TileType type;
	private ImageIcon texture;
	private static final Image[][] explosions = Util.splitSprites(new ImageIcon("images/explosion.png"), 4, 7);
	private static final int DEFAULT_TILE_SIZE = 32;

	public Tile(int x, int y, TileType type) {
		this.location = new Location(x, y);
		this.type = type;
		setTexture();
	}

	public Tile(Location location, TileType type) {
		this.location = new Location(location);
		this.type = type;
		setTexture();
	}

	public Tile(TileType type) {
		this(null, type);
	}

	public Tile() {
		this(null);
	}

	public Location getLocation() {
		return new Location(location);
	}

	public void setLocation(int x, int y) {
		this.location = new Location(x, y);
	}	
	
	public void setLocation(Location location) {
		this.location = new Location(location);
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
		setTexture();
	}

	public double getWidth() {
		if (texture != null) {
			return texture.getIconWidth();
		}
		return DEFAULT_TILE_SIZE;
	}

	public double getHeight() {
		if (texture != null) {
			return texture.getIconHeight();
		}
		return DEFAULT_TILE_SIZE;
	}
	
	public void setTexture() {
		switch (type) {

		case Air:
		case Spawn:
			texture = null;
			break;

		case Brick:
			texture = new ImageIcon("images/brick2.png");
			break;

		case Box:
			texture = new ImageIcon("images/box.jpg");
			break;

		case Explosion_Center:
			texture = new ImageIcon(explosions[3][0]);
			break;

		case Explosion_Vertical:
			texture = new ImageIcon(explosions[3][1]);
			break;

		case Explosion_Horizontal:
			texture = new ImageIcon(explosions[3][2]);
			break;

		case Explosion_Up:
			texture = new ImageIcon(explosions[3][3]);
			break;

		case Explosion_Down:
			texture = new ImageIcon(explosions[3][4]);
			break;

		case Explosion_Right:
			texture = new ImageIcon(explosions[3][5]);
			break;

		case Explosion_Left:
			texture = new ImageIcon(explosions[3][6]);
			break;
		}
	}

	public boolean isDaenerys() {
		return type == TileType.Brick;
	}

	public boolean isSolid() {
		return type == TileType.Brick || type == TileType.Box;
	}

	public void draw(Graphics g) {
		if (location != null && texture != null) {
			g.drawImage(texture.getImage(), location.getX(), location.getY(), null);
		}
	}

	public Rectangle getBounds() {
		if (location != null && texture != null) {
			return new Rectangle(location.getX(), location.getY(), texture.getIconWidth(), texture.getIconHeight());
		} else if (location != null) {
			return new Rectangle(location.getX(), location.getY(), DEFAULT_TILE_SIZE, DEFAULT_TILE_SIZE);
		}
		return null;
	}
}
