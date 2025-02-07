package notcharrowutils.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NotchArrowUtilsModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> createConfigScreen(parent);
	}

	static List<String> colorSuggestions = Arrays.asList(
			"BLACK", "DARK_BLUE", "DARK_GREEN", "DARK_AQUA", "DARK_RED", "DARK_PURPLE", "GOLD", "GRAY",
			"DARK_GRAY", "BLUE", "GREEN", "AQUA", "RED", "LIGHT_PURPLE", "YELLOW", "WHITE");

	private Screen createConfigScreen(Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(Text.of("NotchArrowUtils Configuration"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		// General
		ConfigCategory general = builder.getOrCreateCategory(Text.of("General Settings"));

		Map<String, Boolean> generalBooleanSettings = new LinkedHashMap<>();
		generalBooleanSettings.put("Auto Fish", ConfigManager.config.tickregistryAutoFishMode);
		generalBooleanSettings.put("Auto Tool", ConfigManager.config.tickregistryAutoTool);
		generalBooleanSettings.put("Breadcrumbs", ConfigManager.config.tickregistryBreadcrumbs);
		generalBooleanSettings.put("Coordinate Overlay", ConfigManager.config.tickregistryCoordinateOverlay);
		generalBooleanSettings.put("Mob Grinder", ConfigManager.config.tickregistryMobGrinderMode);

		generalBooleanSettings.forEach((key, value) -> {
			general.addEntry(entryBuilder.startBooleanToggle(Text.of(key), value)
					.setDefaultValue(value)
					.setSaveConsumer(newValue -> {
						switch (key) {
							case "Auto Tool":
								ConfigManager.config.tickregistryAutoTool = newValue;
								break;
							case "Auto Fish":
								ConfigManager.config.tickregistryAutoFishMode = newValue;
								break;
							case "Mob Grinder":
								ConfigManager.config.tickregistryMobGrinderMode = newValue;
								break;
							case "Breadcrumbs":
								ConfigManager.config.tickregistryBreadcrumbs = newValue;
								break;
							case "Coordinate Overlay":
								ConfigManager.config.tickregistryCoordinateOverlay = newValue;
								break;
						}
						ConfigManager.saveConfig();
					})
					.build());
		});

		// Chat
		ConfigCategory chat = builder.getOrCreateCategory(Text.of("Chat Settings"));

		chat.addEntry(entryBuilder.startStringDropdownMenu(Text.of("Chat Color"), ConfigManager.config.textformatColor)
				.setDefaultValue(ConfigManager.config.textformatColor)
				.setSelections(colorSuggestions)
				.setSaveConsumer(newValue -> {
					ConfigManager.config.textformatColor = newValue;
					ConfigManager.saveConfig();
				})
				.build());

		Map<String, Boolean> chatBooleanSettings = new LinkedHashMap<>();
		chatBooleanSettings.put("Bold", ConfigManager.config.textformatBold);
		chatBooleanSettings.put("Italic", ConfigManager.config.textformatItalic);
		chatBooleanSettings.put("Underline", ConfigManager.config.textformatUnderline);

		chatBooleanSettings.forEach((key, value) -> {
			chat.addEntry(entryBuilder.startBooleanToggle(Text.of(key), value)
					.setDefaultValue(value)
					.setSaveConsumer(newValue -> {
						switch (key) {
							case "Bold":
								ConfigManager.config.textformatBold = newValue;
								break;
							case "Italic":
								ConfigManager.config.textformatItalic = newValue;
								break;
							case "Underline":
								ConfigManager.config.textformatUnderline = newValue;
								break;
						}
						ConfigManager.saveConfig();
					})
					.build());
		});

		// Tweaks
		ConfigCategory tweaks = builder.getOrCreateCategory(Text.of("Functionality Tweaks"));

		tweaks.addEntry(entryBuilder.startBooleanToggle(Text.of("Instant Fishing Recast"), ConfigManager.config.tickregistryInstantFishingRecast)
				.setDefaultValue(ConfigManager.config.tickregistryInstantFishingRecast)
				.setSaveConsumer(newValue -> {
					ConfigManager.config.tickregistryInstantFishingRecast = newValue;
					ConfigManager.saveConfig();
				})
				.build());

		tweaks.addEntry(entryBuilder.startIntSlider(Text.of("Breadcrumbs View Distance"), ConfigManager.config.tickregistryBreadcrumbsViewDistance, 0, 100)
				.setDefaultValue(ConfigManager.config.tickregistryBreadcrumbsViewDistance)
				.setSaveConsumer(newValue -> {
					ConfigManager.config.tickregistryBreadcrumbsViewDistance = newValue;
					ConfigManager.saveConfig();
				})
				.build());

		return builder.build();
	}
}