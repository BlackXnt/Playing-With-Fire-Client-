package characters;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GroundEntitiesManager {
	private static List<Entity> groundEntities = new ArrayList<>(); 
	
	public static boolean addGroundItem(Entity entity){
		return groundEntities.add(entity);
	}
	
	public static void updateGroundItems() {
		Iterator<Entity> iter = groundEntities.iterator();
		while (iter.hasNext()) {
			Entity entity = iter.next();
			if (entity.isValid()) {
				entity.tick();
			} else {
				iter.remove();
			}
		}
	}
	
	public static void draw(Graphics g){
		for(Entity entity : groundEntities){
			entity.draw(g);
		}
	}
}
