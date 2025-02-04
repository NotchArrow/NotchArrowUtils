package notcharrowutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.helper.SuggestionBuilder;
import notcharrowutils.helper.TextFormat;

import java.util.Arrays;
import java.util.List;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Help {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	private static final List<String> categorySuggestions = Arrays.asList("math", "information", "utility", "automation", "misc");
	private static final SuggestionProvider<FabricClientCommandSource> categorySuggestionsProvider = SuggestionBuilder.createSuggestionProvider(categorySuggestions);

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("help")
				.then(argument("Command Category", StringArgumentType.string())
				.suggests(categorySuggestionsProvider)
						.executes(Help::execute));
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		switch (StringArgumentType.getString(context, "Command Category")) {
			case "math": client.player.sendMessage(TextFormat.styledText(
					"""
					calculate (expression) - evaluates the expression
					coinflip - prints Heads! or Tails! at 50/50 odds
					mynethercoords - takes current location and divides by 8
					myoverworldcoords - takes current location and multiplies by 8
					randomdecimal (min) (max) (places) - generates a random float between the values
					randomnumber (min) (max) - generates a random integer between the values
					"""
			)); break;
			case "information": client.player.sendMessage(TextFormat.styledText(
					"""
					enchantinfo - lists information about every enchantment
					expinfo - lists information about your experience level
					inventoryinfo - lists items in your inventory and quantities
					"""
			)); break;
			case "utility": client.player.sendMessage(TextFormat.styledText(
					"""
					afkoptimize - mutes sound and reduces fps for afking
					breadcrumbs - creates a particle trail that follows you
					coordinateoverlay - displays your location above your hotbar constantly
					diamondfinder - scans for diamonds in the set distance around you
					nightvision - provides permanent nightvision effect
					"""
			)); break;
			case "automation": client.player.sendMessage(TextFormat.styledText(
					"""
					autofish - automatically fishes for you
					autotool - automatically switches to the best tool in your hotbar
					mobgrinder - automatically attacks mobs you look at
					"""
			)); break;
			case "misc": client.player.sendMessage(TextFormat.styledText(
					"""
					help - displays this information about every command
					preferences - configures settings about this mod's chat display
					"""
			)); break;
		}
		return 1;
	}
}
