package scripts.woodcutter.api.types.bottom.progression;

import scripts.woodcutter.api.types.ingame.Tree;
import scripts.woodcutter.api.types.variables.Vars;

public abstract class ProgressionMove {

	private Tree next_tree;
	

	public ProgressionMove(Tree next_tree) {
		this.next_tree = next_tree;
	}

	public abstract boolean shouldProgress();

	public Tree getNextTree() {
		return this.next_tree;
	}

	protected boolean genericProgressionTest() {
		return Vars.get().current_location != this.next_tree;
	}
}
