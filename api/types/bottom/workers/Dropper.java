package scripts.woodcutter.api.types.bottom.workers;

import org.tribot.api2007.Inventory;

import scripts.woodcutter.api.types.variables.Vars;

public class Dropper extends Banker {

	@Override
	public void execute() {
		Inventory.drop(Vars.get().current_location.getTree().getLogId());
	}

}
