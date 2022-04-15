package com.example.config;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("Skill Tracker Configuration")
public interface SkillTrackerConfig extends Config
{
	String CRYSTAL_MATH_LABS_DESCRIPTION = "Enabling tracking via crystal math labs each time you stop playing";
	String WISE_OLD_MAN_DESCRIPTION = "Enabling tracking via wise old man each time you stop playing";

	@ConfigItem(keyName = "crystalMathLabsTracking", name = "Crystal Math Labs", description = CRYSTAL_MATH_LABS_DESCRIPTION)
	default boolean crystalMathLabsCheckbox()
	{
		return true;
	}

	@ConfigItem(keyName = "wiseOldManTracking", name = "Wise Old Man", description = WISE_OLD_MAN_DESCRIPTION)
	default boolean wiseOldManCheckbox()
	{
		return true;
	}
}
