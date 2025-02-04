package notcharrowutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;
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
						.executes(Preferences::executeUnderline))
				.then(literal("reset")
						.executes(Preferences::executeReset));
	}

	private static int executeColor(CommandContext<FabricClientCommandSource> context) {
		String colorPreference = StringArgumentType.getString(context, "Output Color");
		TextFormat.COLOR = Formatting.valueOf(colorPreference.toUpperCase());
		client.player.sendMessage(TextFormat.styledText("Preferences Changed Successfully!"));

		return 1;
	}

	private static int executeBold(CommandContext<FabricClientCommandSource> context) {
		TextFormat.BOLD = !TextFormat.BOLD;
		client.player.sendMessage(TextFormat.styledText("Preferences Changed Successfully!"));

		return 1;
	}

	private static int executeItalic(CommandContext<FabricClientCommandSource> context) {
		TextFormat.ITALIC = !TextFormat.ITALIC;
		client.player.sendMessage(TextFormat.styledText("Preferences Changed Successfully!"));

		return 1;
	}

	private static int executeUnderline(CommandContext<FabricClientCommandSource> context) {
		TextFormat.UNDERLINE = !TextFormat.UNDERLINE;
		client.player.sendMessage(TextFormat.styledText("Preferences Changed Successfully!"));

		return 1;
	}

	private static int executeReset(CommandContext<FabricClientCommandSource> context) {
		TextFormat.setDefaults();
		client.player.sendMessage(TextFormat.styledText("Preferences Reset Successfully!"));

		return 1;
	}
}
