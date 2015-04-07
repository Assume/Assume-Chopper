package scripts.woodcutter.api.types.top;

public enum Reason {

	UNKNOWN(null), NEED_TO_DROP_INVENTORY(WorkerFactory.DROPPER), NEED_TO_BANK(
			WorkerFactory.BANKER), NEED_TO_PROGRESS(WorkerFactory.PROGRESSOR), NEED_TO_CHOP(
			WorkerFactory.CHOPPER), NEED_TO_UPGRADE_AXE(
			WorkerFactory.AXE_UPGRADER), NEED_TO_LOOT(WorkerFactory.NEST_LOOTER);

	private WorkerFactory Factory;

	Reason(WorkerFactory Factory) {
		this.Factory = Factory;
	}

	public Worker getNextWorker() {
		if (Factory == null)
			return null;
		return Factory.getWorker();
	}

}
