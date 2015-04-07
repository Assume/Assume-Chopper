package scripts.woodcutter.api.types.bottom.progression;

import java.util.ArrayList;
import java.util.List;

import scripts.woodcutter.api.types.bottom.progression.conditions.CurrentLevelMove;
import scripts.woodcutter.api.types.bottom.progression.conditions.LogsChoppedMove;
import scripts.woodcutter.api.types.bottom.progression.conditions.TimeElapsedMove;
import scripts.woodcutter.api.types.ingame.Tree;
import scripts.woodcutter.api.types.variables.Vars;

public class ProgressionFactory {

	private List<ProgressionMove> moves;

	public ProgressionFactory() {
		this.moves = new ArrayList<ProgressionMove>();
	}

	public void add(ProgressionMove move) {
		moves.add(move);
	}

	public void remove(int index) {
		moves.remove(index);
	}

	public ProgressionMove create(Tree tree, ProgressionMoveType move_type,
			String text) {
		ProgressionMove fin = null;
		switch (move_type) {
		case WC_LEVEL:
			fin = new CurrentLevelMove(tree, getInt(text));
			break;
		case LOGS_CHOPPED:
			fin = new LogsChoppedMove(tree, getInt(text));
			break;
		case TIME_ELAPSED:
			fin = new TimeElapsedMove(tree, getDouble(text));
		}
		moves.add(fin);
		return fin;
	}

	private String cleanseText(String text) {
		return text.replaceAll("[^0-9.]", "");
	}

	private int getInt(String text) {
		return Integer.parseInt(cleanseText(text));
	}

	private double getDouble(String text) {
		return Double.parseDouble(cleanseText(text));
	}

	public void close(String name) {
		Vars.get().PROGRESSION_TYPES.add(new Progression(name, moves));

	}
}
