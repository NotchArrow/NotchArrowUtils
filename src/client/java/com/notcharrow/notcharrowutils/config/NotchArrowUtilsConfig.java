package com.notcharrow.notcharrowutils.config;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class NotchArrowUtilsConfig {
	// Chat Colors and Formatting
	public enum ChatColors {
		BLACK,
		DARK_BLUE,
		DARK_GREEN,
		DARK_AQUA,
		DARK_RED,
		DARK_PURPLE,
		GOLD,
		GRAY,
		DARK_GRAY,
		BLUE,
		GREEN,
		AQUA,
		RED,
		LIGHT_PURPLE,
		YELLOW,
		WHITE;

		@Override
		public String toString() {
			String lower = name().toLowerCase(Locale.ROOT).replace('_', ' ');
			return Arrays.stream(lower.split(" "))
					.map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
					.collect(Collectors.joining(" "));
		}
	}
	public ChatColors textformatColor = ChatColors.DARK_AQUA;
	public boolean textformatBold = false;
	public boolean textformatItalic = false;
	public boolean textformatUnderline = false;
	public boolean textformatPrefix = true;
	public ChatColors textformatColorPrefix = ChatColors.DARK_BLUE;
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

	public boolean tickregistryDurabilityWarnings = false;
	public int tickregistryDurabilityWarningsPercentage = 10;
	public int tickregistryDurabilityWarningsTime = 180;

	public boolean tickregistryFastPlace = false;
	public boolean tickregistryFloatingFastPlace = false;

	public boolean tickregistryNightVision = false;

	public boolean tickregistryOverlay = false;
	public float tickregistryOverlayScale = 1.0f;
	public enum OverlayLocation {
		TOP_LEFT,
		TOP_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_RIGHT;

		@Override
		public String toString() {
			String lower = name().toLowerCase(Locale.ROOT).replace('_', ' ');
			return Arrays.stream(lower.split(" "))
					.map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
					.collect(Collectors.joining(" "));
		}
	}
	public OverlayLocation tickregistryOverlayLocation = OverlayLocation.TOP_LEFT;
	public boolean tickregistryOverlayTextShadow = true;
	public boolean tickregistryOverlayCoordinates = true;
	public boolean tickregistryOverlayFPS = true;
	public boolean tickregistryOverlayPing = true;
	public boolean tickregistryOverlaySpeed = true;
	public boolean tickregistryOverlayFacing = true;
	public boolean tickregistryOverlayDay = true;
	public boolean tickregistryOverlayTime = true;
	public boolean tickregistryOverlayBiome = true;
	public boolean tickregistryOverlayTargetedBlock = false;

	public boolean tickregistryPickupNotifier = false;
	public enum PickupNotifierLocation {
		TOP_LEFT,
		TOP_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_RIGHT;

		@Override
		public String toString() {
			String lower = name().toLowerCase(Locale.ROOT).replace('_', ' ');
			return Arrays.stream(lower.split(" "))
					.map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
					.collect(Collectors.joining(" "));
		}
	}
	public PickupNotifierLocation tickregistryPickupNotifierLocation = PickupNotifierLocation.TOP_RIGHT;
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
