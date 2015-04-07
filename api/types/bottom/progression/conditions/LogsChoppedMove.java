package scripts.woodcutter.api.types.bottom.progression.conditions;

import scripts.woodcutter.api.types.bottom.progression.ProgressionMove;
import scripts.woodcutter.api.types.ingame.Tree;
import scripts.woodcutter.api.types.variables.Vars;

public class LogsChoppedMove extends ProgressionMove {

	private int logs_chopped;

	public LogsChoppedMove(Tree next_tree, int logs_chopped) {
		super(next_tree);
		this.logs_chopped = logs_chopped;
	}

	@Override
	public boolean shouldProgress() {
		return Vars.get().current_location.getTree().getAmountChopped() >= logs_chopped
				&& super.genericProgressionTest();
	}

	@Override
	public String toString() {
		return this.logs_chopped + " logs chopped";
	}
}
