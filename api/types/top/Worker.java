package scripts.woodcutter.api.types.top;

import scripts.woodcutter.api.types.bottom.DescriptiveBoolean;

public interface Worker {

	public abstract DescriptiveBoolean shouldExecute();

	public abstract void execute();

}
