package scripts.woodcutter.api.types.bottom.progression;

public enum ProgressionMoveType {

	WC_LEVEL("Level"), LOGS_CHOPPED("Number of Logs"), TIME_ELAPSED(
			"Time(Hours) ie 1.5");

	private String label_text;

	ProgressionMoveType(String label_text) {
		this.label_text = label_text;
	}

	public String getLabelText() {
		return this.label_text;
	}
}
