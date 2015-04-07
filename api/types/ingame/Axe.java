package scripts.woodcutter.api.types.ingame;

public enum Axe {

	BRONZE(1351, null, 1), IRON(1349, BRONZE, 1), STEEL(1353, IRON, 10), MITHRIL(
			1355, STEEL, 21), ADAMANT(1357, MITHRIL, 30), RUNE(1359, ADAMANT,
			40), DRAGON(6739, RUNE, 60);

	private static final int[] ALL_AXE_IDS = new int[] { BRONZE.getId(),
			IRON.getId(), STEEL.getId(), MITHRIL.getId(), ADAMANT.getId(),
			RUNE.getId(), DRAGON.getId() };

	private int id;
	private Axe previous_axe;
	private int required_attack_level;

	Axe(int id, Axe previous_axe, int required_attack_level) {
		this.id = id;
		this.previous_axe = previous_axe;
		this.required_attack_level = required_attack_level;
	}

	public int getId() {
		return this.id;
	}

	public Axe getPreviousAxe() {
		return this.previous_axe;
	}

	public int getRequiredAttackLevel() {
		return this.required_attack_level;
	}

	public static Axe getAxeAtLevel(int level) {
		if (level >= 61)
			return DRAGON;
		if (level >= 41)
			return RUNE;
		if (level >= 31)
			return ADAMANT;
		if (level >= 21)
			return MITHRIL;
		if (level >= 6)
			return STEEL;
		return IRON;
	}

	public static int[] getAllAxeIds() {
		return ALL_AXE_IDS;
	}
}
