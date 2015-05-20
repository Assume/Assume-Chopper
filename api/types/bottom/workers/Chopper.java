package scripts.woodcutter.api.types.bottom.workers;

import java.util.ArrayList;
import java.util.List;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.woodcutter.api.types.bottom.DescriptiveBoolean;
import scripts.woodcutter.api.types.ingame.TreeType;
import scripts.woodcutter.api.types.top.Reason;
import scripts.woodcutter.api.types.top.Worker;
import scripts.woodcutter.api.types.top.WorkerFactory;
import scripts.woodcutter.api.types.variables.Vars;

public class Chopper implements Worker {

	@Override
	public void execute() {
		RSTile random_area_tile = Vars.get().current_location.getTreeArea()
				.getRandomTile();
		if (Player.getPosition().distanceTo(random_area_tile) > 25)
			WebWalking.walkTo(random_area_tile);
		RSObject tree = getBestTree();
		if (tree == null)
			return;
		moveToTree(tree);
		General.sleep(Vars.get().abc_util.DELAY_TRACKER.SWITCH_OBJECT.next());
		chopTree(tree);
	}

	private RSObject getBestTree() {
		TreeType tree = Vars.get().current_location.getTree();
		RSObject[] possible_trees = Objects.find(25, tree.getName());
		Objects.sortByDistanceA(Player.getPosition(), possible_trees);
		removeBadTrees(possible_trees);
		int index = getIndex(possible_trees.length);
		return index == -1 ? null : possible_trees[index];
	}

	private int getIndex(int array_length) {
		if (array_length == 0)
			return -1;
		if (!Vars.get().abc_util.BOOL_TRACKER.USE_CLOSEST.next())
			return 0;
		return array_length > 1 ? 1 : 0;
	}

	private RSObject[] removeBadTrees(RSObject[] trees) {
		List<RSObject> list = new ArrayList<RSObject>();
		RSArea area = Vars.get().current_location.getTreeArea();
		for (RSObject x : trees)
			if (area.contains(x.getPosition()))
				list.add(x);
		return list.toArray(new RSObject[list.size()]);
	}

	private void chopTree(RSObject tree) {
		DynamicClicking.clickRSObject(tree, "Chop down "
				+ Vars.get().current_location.getTree().getName());
		while (Player.isMoving() && tree.getModel() != null)
			General.sleep(50);
		Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				return Player.getAnimation() != -1;
			}
		}, General.random(1500, 2000));
		while (Player.getAnimation() != -1)
			General.sleep(25, 100);
	}

	private void moveToTree(RSObject tree) {
		if (Player.getPosition().distanceTo(tree.getPosition()) >= 6)
			Walking.walkTo(tree);
		if (!tree.isOnScreen())
			Camera.turnToTile(tree);
		return;
	}

	@Override
	public DescriptiveBoolean shouldExecute() {
		if (Inventory.isFull())
			return Vars.get().banking ? new DescriptiveBoolean(false,
					Reason.NEED_TO_BANK) : new DescriptiveBoolean(false,
					Reason.NEED_TO_DROP_INVENTORY);
		if (WorkerFactory.NEST_LOOTER.getWorker().shouldExecute().getBoolean())
			return new DescriptiveBoolean(false, Reason.NEED_TO_LOOT);
		if (Vars.get().progressive_mode)
			if (Vars.get().progression_type.shouldProgress())
				return new DescriptiveBoolean(false, Reason.NEED_TO_PROGRESS);
		if (Vars.get().axe_upgrades)
			if (WorkerFactory.AXE_UPGRADER.getWorker().shouldExecute()
					.getBoolean())
				return new DescriptiveBoolean(false, Reason.NEED_TO_UPGRADE_AXE);
		return new DescriptiveBoolean(true, null);
	}

}
