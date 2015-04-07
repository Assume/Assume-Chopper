package scripts.woodcutter.api.types.bottom.progression;

import java.util.ArrayList;
import java.util.List;

import org.tribot.api2007.Skills.SKILLS;

import scripts.woodcutter.api.types.bottom.progression.conditions.CurrentLevelMove;
import scripts.woodcutter.api.types.ingame.Tree;

public class Progression {

	public static final Progression MONEY = new Progression("Money",
			new CurrentLevelMove(Tree.NORMAL, 1), new CurrentLevelMove(
					Tree.OAK, 15), new CurrentLevelMove(Tree.WILLOW, 30),
			new CurrentLevelMove(Tree.MAPLE, 45), new CurrentLevelMove(
					Tree.YEW, 65), new CurrentLevelMove(Tree.MAGIC, 85));
	public static final Progression QUICK_LEVEL = new Progression(
			"Quick Level", new CurrentLevelMove(Tree.NORMAL, 1),
			new CurrentLevelMove(Tree.OAK, 15), new CurrentLevelMove(
					Tree.WILLOW, 30));

	private List<ProgressionMove> moves = new ArrayList<ProgressionMove>();
	private String name;

	public Progression(String name, List<ProgressionMove> move) {
		this.moves = move;
		this.name = name;
	}

	public Progression(String name, ProgressionMove... move) {
		this.moves = fillList(move);
		this.name = name;
	}

	private List<ProgressionMove> fillList(ProgressionMove[] moves) {
		List<ProgressionMove> list = new ArrayList<ProgressionMove>();
		for (ProgressionMove x : moves)
			list.add(x);
		return list;
	}

	public void removeInvalidMoves() {
		for (int i = 0; i < moves.size() - 1; i++) {
			ProgressionMove x = moves.get(i);
			ProgressionMove x2 = moves.get(i + 1);
			if (x instanceof CurrentLevelMove && x2 instanceof CurrentLevelMove) {
				if (((CurrentLevelMove) x).getLevel() <= SKILLS.WOODCUTTING
						.getActualLevel()
						&& (SKILLS.WOODCUTTING.getActualLevel() >= ((CurrentLevelMove) x2)
								.getLevel()))
					moves.remove(i);
			}
		}
	}

	public boolean shouldProgress() {
		if (this.moves.size() == 0)
			return false;
		return this.moves.get(0).shouldProgress();
	}

	public Tree getNextMove() {
		Tree next = moves.get(0).getNextTree();
		moves.remove(0);
		return next;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
