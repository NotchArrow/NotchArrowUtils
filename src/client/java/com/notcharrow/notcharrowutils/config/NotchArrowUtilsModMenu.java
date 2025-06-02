package com.notcharrow.notcharrowutils.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class NotchArrowUtilsModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return this::createConfigScreen;
	}

	private Screen createConfigScreen(Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(Text.of("NotchArrowUtils Configuration"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		// region General
		ConfigCategory general = builder.getOrCreateCategory(Text.of("General Settings"));

		addConfigEntryBoolean(general, "Auto Attack", "Toggles automatic attacking when you look at mobs", ConfigManager.config.tickregistryAutoAttack,
				newValue -> ConfigManager.config.tickregistryAutoAttack = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Auto Elytra", "Automatically swaps to your elytra when double jumping, swapping back to chestplate when landing", ConfigManager.config.tickregistryAutoElytra,
				newValue -> ConfigManager.config.tickregistryAutoElytra = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Auto Fish", "Toggles automatic fishing", ConfigManager.config.tickregistryAutoFish,
				newValue -> ConfigManager.config.tickregistryAutoFish = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Auto Refill", "Toggles automatic hotbar refill when running out of an item (can flag anticheat)", ConfigManager.config.tickregistryAutoRefill,
				newValue -> ConfigManager.config.tickregistryAutoRefill = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Auto Replant", "Toggles automatic replanting by right clicking while holding seeds", ConfigManager.config.tickregistryAutoReplant,
				newValue -> ConfigManager.config.tickregistryAutoReplant = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Auto Rocket", "Automatic firework rocket usage with elytra", ConfigManager.config.tickregistryAutoRocket,
				newValue -> ConfigManager.config.tickregistryAutoRocket = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Auto Sprint", "Toggles automatic sprinting when moving", ConfigManager.config.tickregistryAutoSprint,
				newValue -> ConfigManager.config.tickregistryAutoSprint = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Auto Tool", "Toggles automatic tool switching when breaking blocks", ConfigManager.config.tickregistryAutoTool,
				newValue -> ConfigManager.config.tickregistryAutoTool = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Breadcrumbs", "Toggles a particle trail that displays where you've walked", ConfigManager.config.tickregistryBreadcrumbs,
				newValue -> ConfigManager.config.tickregistryBreadcrumbs = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Camera Lock", "Toggles camera movement (locks the camera position)", ConfigManager.config.tickregistryCameraLock,
				newValue -> ConfigManager.config.tickregistryCameraLock = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Constant Music", "Removes pauses in Minecraft music", ConfigManager.config.mixinConstantMusic,
				newValue -> ConfigManager.config.mixinConstantMusic = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Coordinate Overlay", "Toggles an overlay that displays your coordinates", ConfigManager.config.tickregistryCoordinateOverlay,
				newValue -> ConfigManager.config.tickregistryCoordinateOverlay = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Durability Warnings", "Toggles durability warnings for tools and armor", ConfigManager.config.tickregistryDurabilityWarnings,
				newValue -> ConfigManager.config.tickregistryDurabilityWarnings = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Fast Place", "Places blocks quickly when holding right click", ConfigManager.config.tickregistryFastPlace,
				newValue -> ConfigManager.config.tickregistryFastPlace = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Nightvision", "Toggles permanent clientside nightvision", ConfigManager.config.tickregistryNightVision,
				newValue -> ConfigManager.config.tickregistryNightVision = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "No Fog", "Toggles fog", ConfigManager.config.mixinNoFog,
				newValue -> ConfigManager.config.mixinNoFog = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Pickup Notifier", "Toggles pickup notifications", ConfigManager.config.tickregistryPickupNotifier,
				newValue -> ConfigManager.config.tickregistryPickupNotifier = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Projectile Trail", "Toggles projectile trails", ConfigManager.config.tickregistryProjectileTrail,
				newValue -> ConfigManager.config.tickregistryProjectileTrail = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Singleplayer Statistics", "Displays session stats for your current world on the pause screen (requires world rejoin)", ConfigManager.config.mixinStatistics,
				newValue -> ConfigManager.config.mixinStatistics = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Storm Pause", "Pauses active singleplayer world when it starts storming (automatically disabled)", ConfigManager.config.tickregistryStormPause,
				newValue -> ConfigManager.config.tickregistryStormPause = (Boolean) newValue,
				parent);
		// endregion General


		// region Chat
		ConfigCategory chat = builder.getOrCreateCategory(Text.of("Chat Settings"));

		chat.addEntry(entryBuilder.startEnumSelector(
						Text.of("Chat Color"),
						NotchArrowUtilsConfig.ChatColors.class,
						ConfigManager.config.textformatColor)
				.setTooltip(Text.of("Changes the command feedback chat color"))
				.setDefaultValue(ConfigManager.config.textformatColor)
				.setSaveConsumer(newValue -> {
					ConfigManager.config.textformatColor = newValue;
					ConfigManager.saveConfig();
				})
				.build());

		addConfigEntryBoolean(chat, "Bold Chat", "Toggles bolded command feedback in chat", ConfigManager.config.textformatBold,
				newValue -> ConfigManager.config.textformatBold = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(chat, "Italic Chat", "Toggles italicized command feedback in chat", ConfigManager.config.textformatItalic,
				newValue -> ConfigManager.config.textformatItalic = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(chat, "Underline Chat", "Toggles underlined command feedback in chat", ConfigManager.config.textformatUnderline,
				newValue -> ConfigManager.config.textformatUnderline = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(chat, "Mod Chat Prefix", "Toggles mod prefix in chat", ConfigManager.config.textformatPrefix,
				newValue -> ConfigManager.config.textformatPrefix = (Boolean) newValue,
				parent);

		chat.addEntry(entryBuilder.startEnumSelector(
						Text.of("Prefix Color"),
						NotchArrowUtilsConfig.ChatColors.class,
						ConfigManager.config.textformatColorPrefix)
				.setTooltip(Text.of("Changes the mod prefix color"))
				.setDefaultValue(ConfigManager.config.textformatColorPrefix)
				.setSaveConsumer(newValue -> {
					ConfigManager.config.textformatColorPrefix = newValue;
					ConfigManager.saveConfig();
				})
				.build());

		addConfigEntryBoolean(chat, "Bold Prefix", "Toggles bolded mod prefix in chat", ConfigManager.config.textformatBoldPrefix,
				newValue -> ConfigManager.config.textformatBoldPrefix = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(chat, "Italic Prefix", "Toggles italicized mod prefix in chat", ConfigManager.config.textformatItalicPrefix,
				newValue -> ConfigManager.config.textformatItalicPrefix = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(chat, "Underline Prefix", "Toggles underlined mod prefix in chat", ConfigManager.config.textformatUnderlinePrefix,
				newValue -> ConfigManager.config.textformatUnderlinePrefix = (Boolean) newValue,
				parent);
		// endregion Chat


		// region Calculator
		ConfigCategory calculator = builder.getOrCreateCategory(Text.of("Custom Calculator Variables"));

		addConfigEntryString(calculator, "Custom Variable 1 Name", "Name for the custom variable when used in the calculator", ConfigManager.config.calculatorVar1Name,
				newValue -> ConfigManager.config.calculatorVar1Name = (String) newValue,
				parent);

		addConfigEntryDouble(calculator, "Custom Variable 1 Value", "Value for the custom variable when used in the calculator", ConfigManager.config.calculatorVar1Value,
				newValue -> ConfigManager.config.calculatorVar1Value = (Double) newValue,
				parent);

		addConfigEntryString(calculator, "Custom Variable 2 Name", "Name for the custom variable when used in the calculator", ConfigManager.config.calculatorVar2Name,
				newValue -> ConfigManager.config.calculatorVar2Name = (String) newValue,
				parent);

		addConfigEntryDouble(calculator, "Custom Variable 2 Value", "Value for the custom variable when used in the calculator", ConfigManager.config.calculatorVar2Value,
				newValue -> ConfigManager.config.calculatorVar2Value = (Double) newValue,
				parent);

		addConfigEntryString(calculator, "Custom Variable 3 Name", "Name for the custom variable when used in the calculator", ConfigManager.config.calculatorVar3Name,
				newValue -> ConfigManager.config.calculatorVar3Name = (String) newValue,
				parent);

		addConfigEntryDouble(calculator, "Custom Variable 3 Value", "Value for the custom variable when used in the calculator", ConfigManager.config.calculatorVar3Value,
				newValue -> ConfigManager.config.calculatorVar3Value = (Double) newValue,
				parent);

		addConfigEntryString(calculator, "Custom Variable 4 Name", "Name for the custom variable when used in the calculator", ConfigManager.config.calculatorVar4Name,
				newValue -> ConfigManager.config.calculatorVar4Name = (String) newValue,
				parent);

		addConfigEntryDouble(calculator, "Custom Variable 4 Value", "Value for the custom variable when used in the calculator", ConfigManager.config.calculatorVar4Value,
				newValue -> ConfigManager.config.calculatorVar4Value = (Double) newValue,
				parent);

		addConfigEntryString(calculator, "Custom Variable 5 Name", "Name for the custom variable when used in the calculator", ConfigManager.config.calculatorVar5Name,
				newValue -> ConfigManager.config.calculatorVar5Name = (String) newValue,
				parent);

		addConfigEntryDouble(calculator, "Custom Variable 5 Value", "Value for the custom variable when used in the calculator", ConfigManager.config.calculatorVar5Value,
				newValue -> ConfigManager.config.calculatorVar5Value = (Double) newValue,
				parent);
		// endregion Calculator


		// region AutoRocket
		ConfigCategory autoRocket = builder.getOrCreateCategory(Text.of("Auto Rocket"));

		addConfigEntryBoolean(autoRocket, "Auto Rocket Safe Landing", "Should Auto Rocket attempt to land at low Elytra durability", ConfigManager.config.tickRegistryAutoRocketSafeLanding,
				newValue -> ConfigManager.config.tickRegistryAutoRocketSafeLanding = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(autoRocket, "Auto Rocket Safe Landing Disconnect", "Should the client disconnect from the server after landing safely", ConfigManager.config.tickregistryAutoRocketDisconnectOnSafeLanding,
				newValue -> ConfigManager.config.tickregistryAutoRocketDisconnectOnSafeLanding = (Boolean) newValue,
				parent);

		addConfigEntryInteger(autoRocket, "Auto Rocket Minimum Delay", "Minimum time in seconds between rocket uses", ConfigManager.config.tickregistryAutoRocketMinDelay,
				newValue -> ConfigManager.config.tickregistryAutoRocketMinDelay = (Integer) newValue,
				parent, 1, 30);

		addConfigEntryInteger(autoRocket, "Auto Rocket Minimum Y Level", "Y level to stay above while using rockets (200+ recommended)", ConfigManager.config.tickregistryAutoRocketMinY,
				newValue -> ConfigManager.config.tickregistryAutoRocketMinY = (Integer) newValue,
				parent, 60, 400);
		// endregion AutoRocket


		// region HotbarCycling
		ConfigCategory hotbarCycling = builder.getOrCreateCategory(Text.of("Hotbar Cycling"));

		addConfigEntryBoolean(hotbarCycling, "Lock Slot 1", "Lock column 1 of the inventory from cycling", ConfigManager.config.hotbarCyclingLockSlot1,
				newValue -> ConfigManager.config.hotbarCyclingLockSlot1 = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(hotbarCycling, "Lock Slot 2", "Lock column 2 of the inventory from cycling", ConfigManager.config.hotbarCyclingLockSlot2,
				newValue -> ConfigManager.config.hotbarCyclingLockSlot2 = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(hotbarCycling, "Lock Slot 3", "Lock column 3 of the inventory from cycling", ConfigManager.config.hotbarCyclingLockSlot3,
				newValue -> ConfigManager.config.hotbarCyclingLockSlot3 = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(hotbarCycling, "Lock Slot 4", "Lock column 4 of the inventory from cycling", ConfigManager.config.hotbarCyclingLockSlot4,
				newValue -> ConfigManager.config.hotbarCyclingLockSlot4 = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(hotbarCycling, "Lock Slot 5", "Lock column 5 of the inventory from cycling", ConfigManager.config.hotbarCyclingLockSlot5,
				newValue -> ConfigManager.config.hotbarCyclingLockSlot5 = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(hotbarCycling, "Lock Slot 6", "Lock column 6 of the inventory from cycling", ConfigManager.config.hotbarCyclingLockSlot6,
				newValue -> ConfigManager.config.hotbarCyclingLockSlot6 = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(hotbarCycling, "Lock Slot 7", "Lock column 7 of the inventory from cycling", ConfigManager.config.hotbarCyclingLockSlot7,
				newValue -> ConfigManager.config.hotbarCyclingLockSlot7 = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(hotbarCycling, "Lock Slot 8", "Lock column 8 of the inventory from cycling", ConfigManager.config.hotbarCyclingLockSlot8,
				newValue -> ConfigManager.config.hotbarCyclingLockSlot8 = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(hotbarCycling, "Lock Slot 9", "Lock column 9 of the inventory from cycling", ConfigManager.config.hotbarCyclingLockSlot9,
				newValue -> ConfigManager.config.hotbarCyclingLockSlot9 = (Boolean) newValue,
				parent);
		// endregion HotbarCycling


		// region Overlay
		ConfigCategory overlay = builder.getOrCreateCategory(Text.of("Overlay HUD"));

		addConfigEntryBoolean(overlay, "Overlay HUD", "The main toggle for the overlay in the top left", ConfigManager.config.tickregistryOverlay,
				newValue -> ConfigManager.config.tickregistryOverlay = (Boolean) newValue,
				parent);

		addConfigEntryFloat(overlay, "Overlay Scale", "Scale of the entire overlay", ConfigManager.config.tickregistryOverlayScale,
				newValue -> ConfigManager.config.tickregistryOverlayScale = (float) newValue,
				parent);

		overlay.addEntry(entryBuilder.startEnumSelector(
						Text.of("Overlay Location"),
						NotchArrowUtilsConfig.OverlayLocation.class,
						ConfigManager.config.tickregistryOverlayLocation)
				.setTooltip(Text.of("Changes the location of the info overlay"))
				.setDefaultValue(ConfigManager.config.tickregistryOverlayLocation)
				.setSaveConsumer(newValue -> {
					ConfigManager.config.tickregistryOverlayLocation = newValue;
					ConfigManager.saveConfig();
				})
				.build());

		addConfigEntryBoolean(overlay, "Overlay Text Shadow", "Should the overlay text have a shadow behind it", ConfigManager.config.tickregistryOverlayTextShadow,
				newValue -> ConfigManager.config.tickregistryOverlayTextShadow = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(overlay, "Display Coordinates", "Should the overlay display coordinates", ConfigManager.config.tickregistryOverlayCoordinates,
				newValue -> ConfigManager.config.tickregistryOverlayCoordinates = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(overlay, "Display Item Durability", "Should the overlay display held item durability", ConfigManager.config.tickregistryOverlayHeldDurability,
				newValue -> ConfigManager.config.tickregistryOverlayHeldDurability = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(overlay, "Display FPS", "Should the overlay display FPS", ConfigManager.config.tickregistryOverlayFPS,
				newValue -> ConfigManager.config.tickregistryOverlayFPS = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(overlay, "Display Ping", "Should the overlay display ping", ConfigManager.config.tickregistryOverlayPing,
				newValue -> ConfigManager.config.tickregistryOverlayPing = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(overlay, "Display Speed", "Should the overlay display speed", ConfigManager.config.tickregistryOverlaySpeed,
				newValue -> ConfigManager.config.tickregistryOverlaySpeed = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(overlay, "Display Direction", "Should the overlay display the direction you're facing", ConfigManager.config.tickregistryOverlayFacing,
				newValue -> ConfigManager.config.tickregistryOverlayFacing = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(overlay, "Display Day", "Should the overlay display the current ingame day", ConfigManager.config.tickregistryOverlayDay,
				newValue -> ConfigManager.config.tickregistryOverlayDay = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(overlay, "Display Time", "Should the overlay display the current ingame time", ConfigManager.config.tickregistryOverlayTime,
				newValue -> ConfigManager.config.tickregistryOverlayTime = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(overlay, "Display Biome", "Should the overlay display the current biome", ConfigManager.config.tickregistryOverlayBiome,
				newValue -> ConfigManager.config.tickregistryOverlayBiome = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(overlay, "Display Targeted Block", "Should the overlay display the targeted block", ConfigManager.config.tickregistryOverlayTargetedBlock,
				newValue -> ConfigManager.config.tickregistryOverlayTargetedBlock = (Boolean) newValue,
				parent);
		// endregion Overlay


		// region Tweaks
		ConfigCategory tweaks = builder.getOrCreateCategory(Text.of("Functionality Tweaks"));

		addConfigEntryBoolean(tweaks, "Auto Attack Passive", "Should Auto Attack target passive mobs (not including villagers)", ConfigManager.config.tickregistryAutoAttackPassive,
				newValue -> ConfigManager.config.tickregistryAutoAttackPassive = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(tweaks, "Auto Attack Players", "Should Auto Attack target players", ConfigManager.config.tickregistryAutoAttackPlayer,
				newValue -> ConfigManager.config.tickregistryAutoAttackPlayer = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(tweaks, "Auto Fish Recast", "Should Auto Fish recast after catching a fish", ConfigManager.config.tickregistryAutoFishRecast,
				newValue -> ConfigManager.config.tickregistryAutoFishRecast = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(tweaks, "Auto Tool From Inventory", "Should Auto Tool pull tools from your inventory (can flag anticheat)", ConfigManager.config.tickregistryAutoToolFromInventory,
				newValue -> ConfigManager.config.tickregistryAutoToolFromInventory = (Boolean) newValue,
				parent);

		addConfigEntryInteger(tweaks, "Auto Tool Slot", "Slot to put tools into if pulling from inventory", ConfigManager.config.tickregistryAutoToolSlot,
				newValue -> ConfigManager.config.tickregistryAutoToolSlot = (Integer) newValue,
				parent, 1, 9);

		addConfigEntryBoolean(tweaks, "Auto Tool Switch Back", "Should Auto Tool switch back to the original slot after you finish mining", ConfigManager.config.tickregistryAutoToolSwitchBack,
				newValue -> ConfigManager.config.tickregistryAutoToolSwitchBack = (Boolean) newValue,
				parent);

		addConfigEntryInteger(tweaks, "Breadcrumbs Minimum Spacing", "Minimum space between particles for breadcrumbs", ConfigManager.config.tickregistryBreadcrumbsMinimumSpacing,
				newValue -> ConfigManager.config.tickregistryBreadcrumbsMinimumSpacing = (Integer) newValue,
				parent, 0, 16);
		
		addConfigEntryInteger(tweaks, "Breadcrumbs View Distance", "Maximum distance away that breadcrumbs particles render", ConfigManager.config.tickregistryBreadcrumbsViewDistance,
				newValue -> ConfigManager.config.tickregistryBreadcrumbsViewDistance = (Integer) newValue,
				parent, 0, 100);

		addConfigEntryBoolean(tweaks, "Colorful Coordinate Overlay", "Use chat coloring settings in the coordinate overlay", ConfigManager.config.tickregistryColorfulCoordinateOverlay,
				newValue -> ConfigManager.config.tickregistryColorfulCoordinateOverlay = (Boolean) newValue,
				parent);

		addConfigEntryInteger(tweaks, "Durability Warnings Percentage", "Sets the percentage of durability for warnings", ConfigManager.config.tickregistryDurabilityWarningsPercentage,
				newValue -> ConfigManager.config.tickregistryDurabilityWarningsPercentage = (Integer) newValue,
				parent, 1, 50);

		addConfigEntryInteger(tweaks, "Durability Warnings Time", "Sets the minimum amount of time between durability warnings (in seconds)", ConfigManager.config.tickregistryDurabilityWarningsTime,
				newValue -> ConfigManager.config.tickregistryDurabilityWarningsTime = (Integer) newValue,
				parent, 15, 600);

		addConfigEntryBoolean(tweaks, "Floating Fast Place", "Allows midair Fast Place, disabled in multiplayer", ConfigManager.config.tickregistryFloatingFastPlace,
				newValue -> ConfigManager.config.tickregistryFloatingFastPlace = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(tweaks, "NightVision AntiBlindness", "Should the NightVision effect also remove Blindness", ConfigManager.config.tickregistryNightVisionAntiBlindness,
				newValue -> ConfigManager.config.tickregistryNightVisionAntiBlindness = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(tweaks, "NightVision AntiDarkness", "Should the NightVision effect also remove Darkness", ConfigManager.config.tickregistryNightVisionAntiDarkness,
				newValue -> ConfigManager.config.tickregistryNightVisionAntiDarkness = (Boolean) newValue,
				parent);

		tweaks.addEntry(entryBuilder.startEnumSelector(
						Text.of("Pickup Notifier Location"),
						NotchArrowUtilsConfig.PickupNotifierLocation.class,
						ConfigManager.config.tickregistryPickupNotifierLocation)
				.setTooltip(Text.of("Changes the location of item pickup notifications"))
				.setDefaultValue(ConfigManager.config.tickregistryPickupNotifierLocation)
				.setSaveConsumer(newValue -> {
					ConfigManager.config.tickregistryPickupNotifierLocation = newValue;
					ConfigManager.saveConfig();
				})
				.build());

		addConfigEntryInteger(tweaks, "Pickup Notifier Time", "Amount of time to display item pickups in seconds", ConfigManager.config.tickregistryPickupNotifierTime,
				newValue -> ConfigManager.config.tickregistryPickupNotifierTime = (Integer) newValue,
				parent, 1, 30);

		addConfigEntryInteger(tweaks, "Projectile Trail View Distance", "Maximum distance away that projectile trail particles render", ConfigManager.config.tickregistryProjectileTrailDistance,
				newValue -> ConfigManager.config.tickregistryProjectileTrailDistance = (Integer) newValue,
				parent, 0, 128);

		addConfigEntryInteger(tweaks, "Zoom Sensitivity Adjustment", "Changes your sensitivity to x% of your default sensitivity while zooming", ConfigManager.config.mixinZoomSensitivityFactor,
				newValue -> ConfigManager.config.mixinZoomSensitivityFactor = (Integer) newValue,
				parent, 10, 100);
		// endregion Tweaks

		return builder.build();
	}

	private void addConfigEntryBoolean(ConfigCategory category, String label, String tooltip, Object value, Consumer<Object> saveConsumer, Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(Text.of("NotchArrowUtils Configuration"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		category.addEntry(entryBuilder.startBooleanToggle(Text.of(label), (Boolean) value)
			.setTooltip(Text.of(tooltip))
			.setDefaultValue((Boolean) value)
			.setSaveConsumer(newValue -> {
			saveConsumer.accept(newValue);
			ConfigManager.saveConfig();
			})
			.build());
	}

	private void addConfigEntryInteger(ConfigCategory category, String label, String tooltip, Object value, Consumer<Object> saveConsumer, Screen parent, int min, int max) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(Text.of("NotchArrowUtils Configuration"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		category.addEntry(entryBuilder.startIntSlider(Text.of(label), (Integer) value, min, max)
			.setTooltip(Text.of(tooltip))
			.setDefaultValue((Integer) value)
			.setSaveConsumer(newValue -> {
			saveConsumer.accept(newValue);
			ConfigManager.saveConfig();
			})
			.build());
	}

	private void addConfigEntryDouble(ConfigCategory category, String label, String tooltip, Object value, Consumer<Object> saveConsumer, Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(Text.of("NotchArrowUtils Configuration"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		category.addEntry(entryBuilder.startDoubleField(Text.of(label), (Double) value)
				.setTooltip(Text.of(tooltip))
				.setDefaultValue((Double) value)
				.setSaveConsumer(newValue -> {
					saveConsumer.accept(newValue);
					ConfigManager.saveConfig();
				})
				.build());
	}

	private void addConfigEntryFloat(ConfigCategory category, String label, String tooltip, Float value, Consumer<Object> saveConsumer, Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(Text.of("NotchArrowUtils Configuration"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		category.addEntry(entryBuilder.startFloatField(Text.of(label),value)
				.setTooltip(Text.of(tooltip))
				.setSaveConsumer(newValue -> {
					saveConsumer.accept(newValue);
					ConfigManager.saveConfig();
				})
				.build());
	}

	private void addConfigEntryString(ConfigCategory category, String label, String tooltip, Object value, Consumer<Object> saveConsumer, Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(Text.of("NotchArrowUtils Configuration"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		category.addEntry(entryBuilder.startStrField(Text.of(label), (String) value)
				.setTooltip(Text.of(tooltip))
				.setDefaultValue((String) value)
				.setSaveConsumer(newValue -> {
					saveConsumer.accept(newValue);
					ConfigManager.saveConfig();
				})
				.build());
	}
}
