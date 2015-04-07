package scripts.woodcutter.api.types.top;

import scripts.woodcutter.api.types.bottom.workers.AxeUpgrader;
import scripts.woodcutter.api.types.bottom.workers.Banker;
import scripts.woodcutter.api.types.bottom.workers.Chopper;
import scripts.woodcutter.api.types.bottom.workers.Dropper;
import scripts.woodcutter.api.types.bottom.workers.NestLooter;
import scripts.woodcutter.api.types.bottom.workers.Progressor;

public enum WorkerFactory {

	CHOPPER {
		@Override
		public Worker getWorker() {
			return new Chopper();
		}
	},
	BANKER {
		@Override
		public Worker getWorker() {
			return new Banker();

		}
	},
	DROPPER {
		@Override
		public Worker getWorker() {
			return new Dropper();
		}
	},
	PROGRESSOR {
		@Override
		public Worker getWorker() {
			return new Progressor();
		}
	},
	AXE_UPGRADER {

		@Override
		public Worker getWorker() {
			return new AxeUpgrader();
		}

	},
	NEST_LOOTER {

		@Override
		public Worker getWorker() {
			return new NestLooter();
		}

	};

	public abstract Worker getWorker();
}
