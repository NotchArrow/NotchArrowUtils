package notcharrowutils.config;

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
		return parent -> createConfigScreen(parent);
	}

	private Screen createConfigScreen(Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(Text.of("NotchArrowUtils Configuration"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		// General
		ConfigCategory general = builder.getOrCreateCategory(Text.of("General Settings"));

		addConfigEntryBoolean(general, "Auto Fish", ConfigManager.config.tickregistryAutoFishMode,
				newValue -> ConfigManager.config.tickregistryAutoFishMode = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Auto Tool", ConfigManager.config.tickregistryAutoTool,
				newValue -> ConfigManager.config.tickregistryAutoTool = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Breadcrumbs", ConfigManager.config.tickregistryBreadcrumbs,
				newValue -> ConfigManager.config.tickregistryBreadcrumbs = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Coordinate Overlay", ConfigManager.config.tickregistryCoordinateOverlay,
				newValue -> ConfigManager.config.tickregistryCoordinateOverlay = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Mob Grinder", ConfigManager.config.tickregistryMobGrinderMode,
				newValue -> ConfigManager.config.tickregistryMobGrinderMode = (Boolean) newValue,
				parent);

		// Chat
		ConfigCategory chat = builder.getOrCreateCategory(Text.of("Chat Settings"));

		List<String> colorSuggestions = Arrays.asList(
			"BLACK", "DARK_BLUE", "DARK_GREEN", "DARK_AQUA", "DARK_RED", "DARK_PURPLE", "GOLD", "GRAY",
			"DARK_GRAY", "BLUE", "GREEN", "AQUA", "RED", "LIGHT_PURPLE", "YELLOW", "WHITE");

		chat.addEntry(entryBuilder.startStringDropdownMenu(Text.of("Chat Color"), ConfigManager.config.textformatColor)
				.setDefaultValue(ConfigManager.config.textformatColor)
				.setSelections(colorSuggestions)
				.setSaveConsumer(newValue -> {
					ConfigManager.config.textformatColor = newValue;
					ConfigManager.saveConfig();
				})
				.build());

		addConfigEntryBoolean(chat, "Bold", ConfigManager.config.textformatBold,
				newValue -> ConfigManager.config.textformatBold = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(chat, "Italic", ConfigManager.config.textformatItalic,
				newValue -> ConfigManager.config.textformatItalic = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(chat, "Underline", ConfigManager.config.textformatUnderline,
				newValue -> ConfigManager.config.textformatUnderline = (Boolean) newValue,
				parent);

		// Tweaks
		ConfigCategory tweaks = builder.getOrCreateCategory(Text.of("Functionality Tweaks"));

		addConfigEntryBoolean(tweaks, "Instant Fishing Recast", ConfigManager.config.tickregistryInstantFishingRecast,
				newValue -> ConfigManager.config.tickregistryInstantFishingRecast = (Boolean) newValue,
				parent);

		addConfigEntryInteger(tweaks, "Breadcrumbs View Distance", ConfigManager.config.tickregistryBreadcrumbsViewDistance,
				newValue -> ConfigManager.config.tickregistryBreadcrumbsViewDistance = (Integer) newValue,
				parent, 0, 100);

		addConfigEntryBoolean(tweaks, "Colorful Coordinate Overlay", ConfigManager.config.tickregistryColorfulCoordinateOverlay,
				newValue -> ConfigManager.config.tickregistryColorfulCoordinateOverlay = (Boolean) newValue,
				parent);


		return builder.build();
	}

	private void addConfigEntryBoolean(ConfigCategory category, String label, Object value, Consumer<Object> saveConsumer, Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(Text.of("NotchArrowUtils Configuration"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		category.addEntry(entryBuilder.startBooleanToggle(Text.of(label), (Boolean) value)
			.setDefaultValue((Boolean) value)
			.setSaveConsumer(newValue -> {
			saveConsumer.accept(newValue);
			ConfigManager.saveConfig();
			})
			.build());
	}

	private void addConfigEntryInteger(ConfigCategory category, String label, Object value, Consumer<Object> saveConsumer, Screen parent, int min, int max) {
		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(Text.of("NotchArrowUtils Configuration"));
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		category.addEntry(entryBuilder.startIntSlider(Text.of(label), (Integer) value, min, max)
			.setDefaultValue((Integer) value)
			.setSaveConsumer(newValue -> {
			saveConsumer.accept(newValue);
			ConfigManager.saveConfig();
			})
			.build());
	}
}