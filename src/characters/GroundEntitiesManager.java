package characters;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GroundEntitiesManager {
	private List<Entity> groundEntities;
	
	public GroundEntitiesManager(){
		groundEntities = new ArrayList<>(); 
	}
	
	public boolean addGroundItem(Entity entity){
		return groundEntities.add(entity);
	}
	
	public void draw(Graphics g){
		Iterator<Entity> iter = groundEntities.iterator();
		while (iter.hasNext()) {
			Entity entity = iter.next();
			if (entity.isValid()) {
				entity.tick();
				entity.draw(g);
			} else {
				iter.remove();
			}
		}
	}
}
