package scripts.woodcutter.api.types.ingame;

import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;

public enum Tree {
	NORMAL(TreeType.NORMAL, Location.NORMAL_TREE_LUMBRIDGE,
			Location.NORMAL_TREE_OAK_CAMELOT_FRONT_OF_BANK,
			Location.NORMAL_TREE_OAK_VARROCK_EAST,
			Location.NORMAL_TREE_OAK_VARROCK_WEST), OAK(TreeType.OAK,
			Location.OAK_LUMBRIDGE,
			Location.NORMAL_TREE_OAK_CAMELOT_FRONT_OF_BANK,
			Location.NORMAL_TREE_OAK_VARROCK_EAST,
			Location.NORMAL_TREE_OAK_VARROCK_WEST), WILLOW(TreeType.WILLOW,
			Location.WILLOW_DRAYNOR, Location.WILLOW_CAMELOT_BANK,
			Location.WILLOW_RIMMINGTON), MAPLE(TreeType.MAPLE,
			Location.MAPLE_CAMELOT_BANK, Location.MAPLE_SINCLAR_MANSION), YEW(
			TreeType.YEW, Location.YEW_RIMMINGTON,
			Location.YEW_CAMELOT_FLAX_FIELD, Location.YEW_EDGEVILLE,
			Location.YEW_LUMBRIDGE, Location.YEW_VARROCK_PALACE), MAGIC(
			TreeType.MAGIC, Location.MAGIC_TOWER_NEAR_CAMELOT,
			Location.MAGIC_MAGIC_TRAINING_ARENA, Location.MAGIC_LEGENDS_GUILD);

	private TreeType tree;
	private Location[] areas;
	private RSArea tree_area;

	Tree(TreeType tree, Location... areas) {
		this.tree = tree;
		this.areas = areas;
	}

	public TreeType getTree() {
		return this.tree;
	}

	public RSArea getTreeArea() {
		if (this.tree_area != null)
			return this.tree_area;
		int closest_distance = 100000;
		RSArea area = null;
		for (Location x : areas) {
			RSArea tarea = x.getTreeArea();
			int distance = Player.getPosition().distanceTo(
					tarea.getRandomTile());
			if (distance < closest_distance
					&& x.getMinimumCombatLevel() < Player.getRSPlayer()
							.getCombatLevel()) {
				area = tarea;
				closest_distance = distance;
			}
		}
		this.tree_area = area;
		return area;
	}

	public Location[] getAllTreeAreas() {
		return this.areas;
	}

}
