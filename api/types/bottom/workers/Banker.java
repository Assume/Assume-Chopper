package scripts.woodcutter.api.types.bottom.workers;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.WebWalking;

import scripts.woodcutter.api.types.bottom.DescriptiveBoolean;
import scripts.woodcutter.api.types.top.Reason;
import scripts.woodcutter.api.types.top.Worker;
import scripts.woodcutter.api.types.variables.Vars;
import scripts.woodcutter.api.types.ingame.Axe;

public class Banker implements Worker {

	@Override
	public void execute() {
		goToBank();
		openBank();
		handleBankWindow();
		WebWalking.walkTo(Vars.get().current_location.getTreeArea()
				.getRandomTile());
	}

	private void goToBank() {
		WebWalking.walkToBank();
	}

	protected void handleBankWindow() {
		Banking.depositAllExcept(Axe.getAllAxeIds());
	}

	private void openBank() {
		Banking.openBank();
		Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				return Banking.isBankScreenOpen();
			}
		}, 3000);
		if (!Banking.isBankScreenOpen()) {
			execute();
			return;
		}
		General.sleep(250, 800);
	}

	@Override
	public DescriptiveBoolean shouldExecute() {
		if (!Inventory.isFull())
			return new DescriptiveBoolean(false, Reason.NEED_TO_CHOP);
		return new DescriptiveBoolean(true, null);
	}

}
