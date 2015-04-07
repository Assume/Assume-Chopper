package scripts.woodcutter.api.types.ingame;

public enum TreeType {

	NORMAL("Tree", 1511), OAK("Oak", 1521), WILLOW("Willow", 1519), MAPLE(
			"Maple tree", 1517), YEW("Yew", 1515), MAGIC("Magic", 1513);

	private int log_id;
	private String tree_name;
	private int amount_chopped;

	TreeType(String tree_name, int log_id) {
		this.tree_name = tree_name;
		this.log_id = log_id;
		this.amount_chopped = 0;
	}

	public String getName() {
		return this.tree_name;
	}

	public int getLogId() {
		return this.log_id;
	}

	public void incrementAmountChopped() {
		this.amount_chopped++;
		TreeType.total_chopped++;
	}

	public int getAmountChopped() {
		return this.amount_chopped;
	}

	public static int getTotalNumberChopped() {
		return total_chopped;
	}

	private static int total_chopped = 0;

	
}
