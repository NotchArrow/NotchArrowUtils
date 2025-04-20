package com.notcharrow.notcharrowutils.config;

public class NotchArrowUtilsConfig {
	// Chat Colors and Formatting
	public String textformatColor = "DARK_AQUA";
	public boolean textformatBold = false;
	public boolean textformatItalic = false;
	public boolean textformatUnderline = false;
	public boolean textformatPrefix = true;
	public String textformatColorPrefix = "DARK_BLUE";
	public boolean textformatBoldPrefix = true;
	public boolean textformatItalicPrefix = false;
	public boolean textformatUnderlinePrefix = false;


	// Calculator Command
	public String calculatorVar1Name = "inv";
	public double calculatorVar1Value = 2304;
	public String calculatorVar2Name = "var2";
	public double calculatorVar2Value = 0;
	public String calculatorVar3Name = "var3";
	public double calculatorVar3Value = 0;
	public String calculatorVar4Name = "var4";
	public double calculatorVar4Value = 0;
	public String calculatorVar5Name = "var5";
	public double calculatorVar5Value = 0;

	// HotbarCycling
	public boolean hotbarCyclingLockSlot1 = false;
	public boolean hotbarCyclingLockSlot2 = false;
	public boolean hotbarCyclingLockSlot3 = false;
	public boolean hotbarCyclingLockSlot4 = false;
	public boolean hotbarCyclingLockSlot5 = false;
	public boolean hotbarCyclingLockSlot6 = false;
	public boolean hotbarCyclingLockSlot7 = false;
	public boolean hotbarCyclingLockSlot8 = false;
	public boolean hotbarCyclingLockSlot9 = false;

	// Tickevents
	public boolean tickregistryAutoAttack = false;
	public boolean tickregistryAutoAttackPassive = false;
	public boolean tickregistryAutoAttackPlayer = false;

	public boolean tickregistryAutoElytra = false;

	public boolean tickregistryAutoFish = false;

	public boolean tickregistryAutoRefill = false;

	public boolean tickregistryAutoReplant = false;

	public boolean tickregistryAutoRocket = false;
	public int tickregistryAutoRocketMinDelay = 10;
	public int tickregistryAutoRocketMinY = 200;
	public boolean tickRegistryAutoRocketSafeLanding = false;
	public boolean tickregistryAutoRocketDisconnectOnSafeLanding = true;

	public boolean tickregistryAutoSprint = false;

	public boolean tickregistryAutoTool = false;
	public boolean tickregistryAutoToolFromInventory = false;
	public int tickregistryAutoToolSlot = 9;
	public boolean tickregistryAutoToolSwitchBack = true;

	public boolean tickregistryBreadcrumbs = false;
	public int tickregistryBreadcrumbsMinimumSpacing = 1;
	public int tickregistryBreadcrumbsViewDistance = 10;

	public boolean tickregistryCameraLock = false;

	public boolean tickregistryCoordinateOverlay = false;
	public boolean tickregistryColorfulCoordinateOverlay = false;

	public boolean tickregistryFastPlace = false;
	public boolean tickregistryFloatingFastPlace = false;

	public boolean tickregistryNightVision = false;

	public boolean tickregistryPickupNotifier = false;
	public String tickregistryPickupNotifierLocation = "BOTTOM_RIGHT";
	public int tickregistryPickupNotifierTime = 5;

	public boolean tickregistryProjectileTrail = false;
	public int tickregistryProjectileTrailDistance = 64;

	public boolean tickregistryStormPause = false;


	// Mixins
	public boolean mixinConstantMusic = false;
	public boolean mixinNoFog = false;
	public boolean mixinStatistics = false;
	public int mixinZoomSensitivityFactor = 50;
}
