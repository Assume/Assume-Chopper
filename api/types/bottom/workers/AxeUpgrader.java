package scripts.woodcutter.api.types.bottom.workers;

import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Equipment.SLOTS;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSItem;

import scripts.woodcutter.api.types.bottom.DescriptiveBoolean;
import scripts.woodcutter.api.types.ingame.Axe;
import scripts.woodcutter.api.types.top.Reason;
import scripts.woodcutter.api.types.variables.Vars;

public class AxeUpgrader extends Banker {

	private static Axe HIGHEST_AXE_NOT_IN_BANK = null;

	@Override
	public void execute() {
		Axe axe = Axe.getAxeAtLevel(Skills.getActualLevel(SKILLS.WOODCUTTING));
		removeAxe(axe);
		super.execute();
	}

	@Override
	protected void handleBankWindow() {
		Axe axe = Axe.getAxeAtLevel(Skills.getActualLevel(SKILLS.WOODCUTTING));
		Banking.depositAll();
		int minimum_combat = axe.getRequiredAttackLevel();
		if (Banking.find(axe.getId()).length == 0) {
			HIGHEST_AXE_NOT_IN_BANK = axe;
			int id = -1;
			Axe previous_axe = axe.getPreviousAxe();
			if (previous_axe == null)
				id = 1351;
			Banking.withdraw(1, id);
			minimum_combat = axe.getPreviousAxe().getRequiredAttackLevel();
		} else
			Banking.withdraw(1, axe.getId());
		Banking.close();
		General.sleep(300, 800);
		equipAxe(axe, minimum_combat);
	}

	private void equipAxe(Axe axe, int minimum_combat) {
		if (Skills.getActualLevel(SKILLS.ATTACK) >= minimum_combat) {
			RSItem[] axes = Inventory.find(axe.getId(), axe.getPreviousAxe()
					.getId());
			if (axes.length != 0)
				axes[0].click("W");
		}
	}

	private void removeAxe(Axe axe) {
		if (Equipment.find(axe.getPreviousAxe().getId()).length != 0) {
			if (Inventory.isFull())
				dropSingleLog();
			Equipment.remove(SLOTS.WEAPON);
		}

	}

	private void dropSingleLog() {
		RSItem[] items = Inventory.find(Vars.get().current_location.getTree()
				.getLogId());
		if (items.length > 0)
			items[0].click("Drop");
	}

	@Override
	public DescriptiveBoolean shouldExecute() {
		Axe axe = Axe.getAxeAtLevel(Skills.getActualLevel(SKILLS.WOODCUTTING));
		int id = axe.getId();
		if ((!Equipment.isEquipped(id) && Inventory.find(id).length == 0)
				&& HIGHEST_AXE_NOT_IN_BANK == null)
			return new DescriptiveBoolean(true, null);
		return new DescriptiveBoolean(false, Reason.NEED_TO_CHOP);
	}

}
