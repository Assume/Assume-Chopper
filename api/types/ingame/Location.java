package scripts.woodcutter.api.types.ingame;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public enum Location {

	NORMAL_TREE_LUMBRIDGE(new RSArea(new RSTile[] { new RSTile(3204, 3247, 0),
			new RSTile(3191, 3248, 0), new RSTile(3182, 3234, 0),
			new RSTile(3197, 3223, 0) }), 1),

	NORMAL_TREE_OAK_CAMELOT_FRONT_OF_BANK(new RSArea(new RSTile[] {
			new RSTile(2717, 3486, 0), new RSTile(2731, 3485, 0),
			new RSTile(2731, 3464, 0), new RSTile(2719, 3464, 0) }), 1),

	NORMAL_TREE_OAK_VARROCK_WEST(new RSArea(new RSTile[] {
			new RSTile(3169, 3422, 0), new RSTile(3157, 3418, 0),
			new RSTile(3162, 3402, 0), new RSTile(3169, 3401, 0) }), 1),

	NORMAL_TREE_OAK_VARROCK_EAST(new RSArea(new RSTile[] {
			new RSTile(3272, 3455, 0), new RSTile(3272, 3439, 0),
			new RSTile(3276, 3439, 0), new RSTile(3277, 3429, 0),
			new RSTile(3278, 3421, 0), new RSTile(3280, 3412, 0),
			new RSTile(3287, 3415, 0), new RSTile(3280, 3456, 0) }), 1),

	OAK_LUMBRIDGE(new RSArea(new RSTile[] { new RSTile(3144, 3230, 0),
			new RSTile(3146, 3218, 0), new RSTile(3160, 3214, 0),
			new RSTile(3172, 3220, 0), new RSTile(3157, 3240, 0) }), 1),

	WILLOW_DRAYNOR(new RSArea(new RSTile[] { new RSTile(3081, 3240, 0),
			new RSTile(3091, 3237, 0), new RSTile(3088, 3229, 0) }), 10),

	WILLOW_CAMELOT_BANK(new RSArea(new RSTile[] { new RSTile(2711, 3518, 0),
			new RSTile(2705, 3518, 0), new RSTile(2706, 3511, 0),
			new RSTile(2716, 3509, 0) }), 1),

	WILLOW_RIMMINGTON(new RSArea(new RSTile[] { new RSTile(2959, 3201, 0),
			new RSTile(2973, 3201, 0), new RSTile(2975, 3195, 0),
			new RSTile(2969, 3189, 0), new RSTile(2960, 3192, 0),
			new RSTile(2959, 3197, 0) }), 1),

	WILLOW_BARBARIAN_ASSAULT(new RSArea(new RSTile[] {
			new RSTile(2515, 3584, 0), new RSTile(2521, 3582, 0),
			new RSTile(2521, 3576, 0), new RSTile(2515, 3576, 0) }), 1),

	MAPLE_CAMELOT_BANK(new RSArea(new RSTile[] { new RSTile(2720, 3506, 0),
			new RSTile(2721, 3499, 0), new RSTile(2734, 3499, 0),
			new RSTile(2733, 3507, 0) }), 1),

	MAPLE_SINCLAR_MANSION(new RSArea(new RSTile[] { new RSTile(2701, 3582, 0),
			new RSTile(2721, 3582, 0), new RSTile(2727, 3557, 0),
			new RSTile(2716, 3551, 0), new RSTile(2707, 3564, 0) }), 1),

	YEW_EDGEVILLE(new RSArea(new RSTile[] { new RSTile(3083, 3485, 0),
			new RSTile(3089, 3485, 0), new RSTile(3091, 3465, 0),
			new RSTile(3083, 3466, 0) }), 1),

	YEW_VARROCK_PALACE(new RSArea(new RSTile[] { new RSTile(3202, 3506, 0),
			new RSTile(3206, 3498, 0), new RSTile(3225, 3500, 0),
			new RSTile(3224, 3506, 0) }), 1),

	YEW_CAMELOT_FLAX_FIELD(new RSArea(new RSTile[] { new RSTile(2753, 3435, 0),
			new RSTile(2764, 3435, 0), new RSTile(2762, 3426, 0),
			new RSTile(2752, 3424, 0) }), 1),

	YEW_RIMMINGTON(new RSArea(new RSTile[] { new RSTile(2939, 3236, 0),
			new RSTile(2929, 3237, 0), new RSTile(2921, 3236, 0),
			new RSTile(2926, 3225, 0), new RSTile(2941, 3223, 0),
			new RSTile(2946, 3236, 0) }), 1),

	YEW_LUMBRIDGE(new RSArea(new RSTile[] { new RSTile(3195, 3236, 0),
			new RSTile(3185, 3239, 0), new RSTile(3179, 3231, 0),
			new RSTile(3179, 3221, 0), new RSTile(3189, 3218, 0),
			new RSTile(3198, 3223, 0) }), 1),

	YEW_CAMELOT_NEAR_BANK(new RSArea(new RSTile[] { new RSTile(2705, 3468, 0),
			new RSTile(2715, 3468, 0), new RSTile(2725, 3468, 0),
			new RSTile(2725, 3458, 0), new RSTile(2714, 3454, 0),
			new RSTile(2704, 3456, 0) }), 1),

	MAGIC_TOWER_NEAR_CAMELOT(new RSArea(new RSTile[] {
			new RSTile(2699, 3401, 0), new RSTile(2707, 3401, 0),
			new RSTile(2707, 3394, 0), new RSTile(2699, 3394, 0) }), 1),

	MAGIC_MAGIC_TRAINING_ARENA(new RSArea(new RSTile[] {
			new RSTile(3353, 3324, 0), new RSTile(3354, 3309, 0),
			new RSTile(3373, 3308, 0), new RSTile(3372, 3324, 0) }), 1),

	MAGIC_LEGENDS_GUILD(new RSArea(new RSTile[] { new RSTile(2707, 3389, 0),
			new RSTile(2717, 3389, 0), new RSTile(2718, 3373, 0),
			new RSTile(2708, 3373, 0) }), 1);

	private RSArea tree_area;
	private int minimum_combat_level;

	Location(RSArea tree_area, int minimum_combat_level) {
		this.tree_area = tree_area;
		this.minimum_combat_level = minimum_combat_level;
	}

	public RSArea getTreeArea() {
		return this.tree_area;
	}

	public int getMinimumCombatLevel() {
		return this.minimum_combat_level;
	}

}
