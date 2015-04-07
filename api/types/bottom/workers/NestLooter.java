package scripts.woodcutter.api.types.bottom.workers;

import org.tribot.api.General;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.types.RSGroundItem;

import scripts.woodcutter.api.types.bottom.DescriptiveBoolean;
import scripts.woodcutter.api.types.top.Reason;
import scripts.woodcutter.api.types.top.Worker;

public class NestLooter implements Worker {

	@Override
	public void execute() {
		RSGroundItem[] items = GroundItems.find("Bird Nest");
		if (items.length > 0) {
			items[0].click("Take Bird Nest");
			General.sleep(2500, 3500);
		}
	}

	@Override
	public DescriptiveBoolean shouldExecute() {
		if (GroundItems.find("Bird Nest").length > 0)
			return new DescriptiveBoolean(true, null);
		return new DescriptiveBoolean(false, Reason.NEED_TO_CHOP);
	}

}
