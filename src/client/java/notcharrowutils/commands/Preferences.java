package notcharrowutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.config.ConfigManager;
import notcharrowutils.helper.SuggestionBuilder;
import notcharrowutils.helper.TextFormat;

import java.util.Arrays;
import java.util.List;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Preferences {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	static List<String> colorSuggestions = Arrays.asList(
			"black", "dark_blue", "dark_green", "dark_aqua", "dark_red", "dark_purple", "gold", "gray",
			"dark_gray", "blue", "green", "aqua", "red", "light_purple", "yellow", "white");
	static SuggestionProvider<FabricClientCommandSource> colorSuggestionsProvider = SuggestionBuilder.createSuggestionProvider(colorSuggestions);

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("preferences")
				.then(literal("color")
						.then(argument("Output Color", StringArgumentType.string())
						.suggests(colorSuggestionsProvider)
								.executes(Preferences::executeColor)))
				.then(literal("bold")
						.executes(Preferences::executeBold))
				.then(literal("italic")
						.executes(Preferences::executeItalic))
				.then(literal("underline")
						.executes(Preferences::executeUnderline));
	}

	private static int executeColor(CommandContext<FabricClientCommandSource> context) {
		String colorPreference = StringArgumentType.getString(context, "Output Color");
		ConfigManager.config.textformatColor = colorPreference.toUpperCase();
		ConfigManager.saveConfig();
		client.player.sendMessage(TextFormat.styledText("Preferences Changed Successfully!"));

		return 1;
	}

	private static int executeBold(CommandContext<FabricClientCommandSource> context) {
		ConfigManager.config.textformatBold = !ConfigManager.config.textformatBold;
		ConfigManager.saveConfig();
		client.player.sendMessage(TextFormat.styledText("Preferences Changed Successfully!"));

		return 1;
	}

	private static int executeItalic(CommandContext<FabricClientCommandSource> context) {
		ConfigManager.config.textformatItalic = !ConfigManager.config.textformatItalic;
		ConfigManager.saveConfig();
		client.player.sendMessage(TextFormat.styledText("Preferences Changed Successfully!"));

		return 1;
	}

	private static int executeUnderline(CommandContext<FabricClientCommandSource> context) {
		ConfigManager.config.textformatUnderline = !ConfigManager.config.textformatUnderline;
		ConfigManager.saveConfig();
		client.player.sendMessage(TextFormat.styledText("Preferences Changed Successfully!"));

		return 1;
	}
}
