package scripts.woodcutter.api.types.bottom.progression.conditions;

import org.tribot.api.General;
import org.tribot.api.Timing;

import scripts.woodcutter.api.types.bottom.progression.ProgressionMove;
import scripts.woodcutter.api.types.ingame.Tree;
import scripts.woodcutter.api.types.variables.Vars;

public class TimeElapsedMove extends ProgressionMove {

	private long time;

	public TimeElapsedMove(Tree next_tree, double hours) {
		super(next_tree);
		long temp = (long) (hours * 3600);
		this.time = (long) General.randomDouble(temp - (temp * .05), temp
				+ (temp * .05));
	}

	@Override
	public boolean shouldProgress() {
		return Vars.get().last_location_change + time >= System
				.currentTimeMillis() && super.genericProgressionTest();
	}

	@Override
	public String toString() {
		return Timing.msToString(time);
	}
}
