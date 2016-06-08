package characters;

import java.awt.Graphics;
import java.awt.Rectangle;

import map.Location;

public abstract class Entity {
	protected Location location; 
	protected double width, height;
	protected boolean valid = true;
	
	public Entity(int x, int y, double width, double height){
		this.location = new Location(x, y);
		this.width = width;
		this.height = height;
	}
	
	public Entity(Location location, double width, double height){
		this.location = new Location(location);
		this.width = width;
		this.height = height;
	}
	
	public Location getLocation(){
		return new Location(location);
	}
	
	public void setLocation(int x, int y) {
		this.location = new Location(x, y);
	}	
	
	public void setLocation(Location location){
		this.location = new Location(location);
	}
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public abstract void tick();

	public abstract void draw(Graphics g);
	
	public Rectangle getBounds() {
	    return new Rectangle(location.getX(), location.getY(), (int)width, (int)height);
	}
	
	public boolean isValid(){
		return valid;
	}
}
