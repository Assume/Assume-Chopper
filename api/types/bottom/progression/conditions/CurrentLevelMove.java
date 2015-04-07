package scripts.woodcutter.api.types.bottom.progression.conditions;

import org.tribot.api2007.Skills.SKILLS;

import scripts.woodcutter.api.types.bottom.progression.ProgressionMove;
import scripts.woodcutter.api.types.ingame.Tree;

public class CurrentLevelMove extends ProgressionMove {

	private int level;

	public CurrentLevelMove(Tree next_tree, int level) {
		super(next_tree);
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	@Override
	public boolean shouldProgress() {
		return SKILLS.WOODCUTTING.getActualLevel() >= this.level && super.genericProgressionTest();
	}

	@Override
	public String toString() {
		return level + " woodcutting";
	}

}
