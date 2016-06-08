package characters;

import map.Location;

public abstract class MovingEntity extends Entity{
	protected Direction direction;
	
	public MovingEntity(int x, int y, double width, double height, Direction direction) {
		super(x, y, width, height);
		this.direction = direction;
	}
	
	public MovingEntity(Location location, double width, double height, Direction direction) {
		super(location, width, height);
		this.direction = direction;
	}
	
	public abstract void move(int x, int y, Direction direction);
}
