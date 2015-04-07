package scripts.woodcutter.api.types.variables;

import java.util.ArrayList;
import java.util.List;

import org.tribot.api.util.ABCUtil;

import scripts.woodcutter.api.types.bottom.progression.Progression;
import scripts.woodcutter.api.types.ingame.Tree;

public class Vars {

	private static Vars vars;

	public static Vars get() {
		return vars == null ? vars = new Vars() : vars;
	}

	private Vars() {
		this.PROGRESSION_TYPES = new ArrayList<Progression>();
		this.PROGRESSION_TYPES.add(Progression.MONEY);
		this.PROGRESSION_TYPES.add(Progression.QUICK_LEVEL);
	}

	public ABCUtil abc_util = new ABCUtil();

	public Progression progression_type;

	public long last_location_change = System.currentTimeMillis();

	public boolean progressive_mode = false;
	public boolean banking = false;

	public Tree current_location;
	public boolean axe_upgrades = false;

	public List<Progression> PROGRESSION_TYPES;

}
