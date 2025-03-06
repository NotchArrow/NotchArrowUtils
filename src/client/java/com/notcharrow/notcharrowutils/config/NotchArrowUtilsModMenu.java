package com.notcharrow.notcharrowutils.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.*;
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

		addConfigEntryBoolean(general, "Auto Fish", "Toggles automatic fishing", ConfigManager.config.tickregistryAutoFishMode,
				newValue -> ConfigManager.config.tickregistryAutoFishMode = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Auto Replant", "Toggles automatic replanting by right clicking while holding seeds", ConfigManager.config.tickregistryAutoReplant,
				newValue -> ConfigManager.config.tickregistryAutoReplant = (Boolean) newValue,
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

		addConfigEntryBoolean(general, "Coordinate Overlay", "Toggles an overlay that displays your coordinates", ConfigManager.config.tickregistryCoordinateOverlay,
				newValue -> ConfigManager.config.tickregistryCoordinateOverlay = (Boolean) newValue,
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

		addConfigEntryBoolean(general, "Storm Pause", "Pauses active singleplayer world when it starts storming (automatically disabled)", ConfigManager.config.tickregistryStormPause,
				newValue -> ConfigManager.config.tickregistryStormPause = (Boolean) newValue,
				parent);
		// endregion General


		// region Chat
		ConfigCategory chat = builder.getOrCreateCategory(Text.of("Chat Settings"));

		List<String> colorSuggestions = Arrays.asList(
			"BLACK", "DARK_BLUE", "DARK_GREEN", "DARK_AQUA", "DARK_RED", "DARK_PURPLE", "GOLD", "GRAY",
			"DARK_GRAY", "BLUE", "GREEN", "AQUA", "RED", "LIGHT_PURPLE", "YELLOW", "WHITE");

		chat.addEntry(entryBuilder.startStringDropdownMenu(Text.of("Chat Color"), ConfigManager.config.textformatColor)
				.setTooltip(Text.of("Changes the command feedback chat color"))
				.setDefaultValue(ConfigManager.config.textformatColor)
				.setSelections(colorSuggestions)
				.setSaveConsumer(newValue -> {
					ConfigManager.config.textformatColor = newValue;
					ConfigManager.saveConfig();
				}).build());

		addConfigEntryBoolean(chat, "Bold", "Toggles bolded command feedback in chat", ConfigManager.config.textformatBold,
				newValue -> ConfigManager.config.textformatBold = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(chat, "Italic", "Toggles italicized command feedback in chat", ConfigManager.config.textformatItalic,
				newValue -> ConfigManager.config.textformatItalic = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(chat, "Underline", "Toggles underlined command feedback in chat", ConfigManager.config.textformatUnderline,
				newValue -> ConfigManager.config.textformatUnderline = (Boolean) newValue,
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


		// region Tweaks
		ConfigCategory tweaks = builder.getOrCreateCategory(Text.of("Functionality Tweaks"));

		addConfigEntryBoolean(tweaks, "Auto Attack Passive", "Should Auto Attack target passive mobs (including players)", ConfigManager.config.tickregistryAutoAttackPassive,
				newValue -> ConfigManager.config.tickregistryAutoAttackPassive = (Boolean) newValue,
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

		addConfigEntryBoolean(tweaks, "Floating Fast Place", "Allows midair Fast Place, disabled in multiplayer", ConfigManager.config.tickregistryFloatingFastPlace,
				newValue -> ConfigManager.config.tickregistryFloatingFastPlace = (Boolean) newValue,
				parent);

		List<String> pickupNotifierLocations = Arrays.asList("TOP_LEFT", "TOP_RIGHT", "BOTTOM_LEFT", "BOTTOM_RIGHT");

		tweaks.addEntry(entryBuilder.startStringDropdownMenu(Text.of("Pickup Notifier Location"), ConfigManager.config.tickregistryPickupNotifierLocation)
				.setTooltip(Text.of("Changes the location of item pickup notifications"))
				.setDefaultValue(ConfigManager.config.tickregistryPickupNotifierLocation)
				.setSelections(pickupNotifierLocations)
				.setSaveConsumer(newValue -> {
					ConfigManager.config.tickregistryPickupNotifierLocation = newValue;
					ConfigManager.saveConfig();
				}).build());

		addConfigEntryInteger(tweaks, "Pickup Notifier Time", "Amount of time to display item pickups in seconds", ConfigManager.config.tickregistryPickupNotifierTime,
				newValue -> ConfigManager.config.tickregistryPickupNotifierTime = (Integer) newValue,
				parent, 1, 30);
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
