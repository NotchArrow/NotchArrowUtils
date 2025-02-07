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

		addConfigEntryBoolean(general, "Auto Fish", "Toggles automatic fishing", ConfigManager.config.tickregistryAutoFishMode,
				newValue -> ConfigManager.config.tickregistryAutoFishMode = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Auto Tool", "Toggles automatic tool switching when breaking blocks", ConfigManager.config.tickregistryAutoTool,
				newValue -> ConfigManager.config.tickregistryAutoTool = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Breadcrumbs", "Toggles a particle trail that displays where you've walked", ConfigManager.config.tickregistryBreadcrumbs,
				newValue -> ConfigManager.config.tickregistryBreadcrumbs = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Coordinate Overlay", "Toggles an overlay that displays your coordinates", ConfigManager.config.tickregistryCoordinateOverlay,
				newValue -> ConfigManager.config.tickregistryCoordinateOverlay = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(general, "Mob Grinder", "Toggles automatic attacking when you look at mobs", ConfigManager.config.tickregistryMobGrinderMode,
				newValue -> ConfigManager.config.tickregistryMobGrinderMode = (Boolean) newValue,
				parent);

		// Chat
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
				})
				.build());

		addConfigEntryBoolean(chat, "Bold", "Toggles bolded command feedback in chat", ConfigManager.config.textformatBold,
				newValue -> ConfigManager.config.textformatBold = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(chat, "Italic", "Toggles italicized command feedback in chat", ConfigManager.config.textformatItalic,
				newValue -> ConfigManager.config.textformatItalic = (Boolean) newValue,
				parent);

		addConfigEntryBoolean(chat, "Underline", "Toggles underlined command feedback in chat", ConfigManager.config.textformatUnderline,
				newValue -> ConfigManager.config.textformatUnderline = (Boolean) newValue,
				parent);

		// Tweaks
		ConfigCategory tweaks = builder.getOrCreateCategory(Text.of("Functionality Tweaks"));

		addConfigEntryBoolean(tweaks, "Instant Fishing Recast", "Recast your fishing rod instantly instead of after a delay when using autofish", ConfigManager.config.tickregistryInstantFishingRecast,
				newValue -> ConfigManager.config.tickregistryInstantFishingRecast = (Boolean) newValue,
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
}
