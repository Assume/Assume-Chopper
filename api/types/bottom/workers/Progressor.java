package scripts.woodcutter.api.types.bottom.workers;

import org.tribot.api2007.WebWalking;

import scripts.woodcutter.api.types.bottom.DescriptiveBoolean;
import scripts.woodcutter.api.types.ingame.Tree;
import scripts.woodcutter.api.types.top.Reason;
import scripts.woodcutter.api.types.top.Worker;
import scripts.woodcutter.api.types.variables.Vars;

public class Progressor implements Worker {

	@Override
	public void execute() {
		moveToNewArea(Vars.get().progression_type.getNextMove());
	}

	private void moveToNewArea(Tree tree) {
		System.out.println("Moving to "+tree);
		WebWalking.walkTo((Vars.get().current_location = tree).getTreeArea()
				.getRandomTile());
		Vars.get().last_location_change = System.currentTimeMillis();
	}

	@Override
	public DescriptiveBoolean shouldExecute() {
		if (!Vars.get().progression_type.shouldProgress())
			return new DescriptiveBoolean(false, Reason.NEED_TO_CHOP);
		return new DescriptiveBoolean(true, null);
	}

}
