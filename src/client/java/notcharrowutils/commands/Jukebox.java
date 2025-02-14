package notcharrowutils.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import notcharrowutils.helper.SuggestionBuilder;
import notcharrowutils.helper.TextFormat;

import java.util.Arrays;
import java.util.List;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Jukebox {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	private static final List<String> musicDiscSuggestions = Arrays.asList("cat", "wait", "13", "blocks", "chirp", "far", "mall",
			"mellohi", "stal", "strad", "ward", "11", "pigstep", "otherside", "relic", "5", "stop");
	private static final SuggestionProvider<FabricClientCommandSource> musicDiscSuggestionsProvider = SuggestionBuilder.createSuggestionProvider(musicDiscSuggestions);


	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("jukebox")
				.then(argument("disc", StringArgumentType.string())
				.suggests(musicDiscSuggestionsProvider)
						.executes(Jukebox::execute));
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		String discName = StringArgumentType.getString(context, "disc");
		SoundManager soundManager = client.getSoundManager();

		RegistryEntry.Reference<SoundEvent> soundEvent = switch (discName) {
			case "cat" -> SoundEvents.MUSIC_DISC_CAT;
			case "wait" -> SoundEvents.MUSIC_DISC_WAIT;
			case "13" -> SoundEvents.MUSIC_DISC_13;
			case "blocks" -> SoundEvents.MUSIC_DISC_BLOCKS;
			case "chirp" -> SoundEvents.MUSIC_DISC_CHIRP;
			case "far" -> SoundEvents.MUSIC_DISC_FAR;
			case "mall" -> SoundEvents.MUSIC_DISC_MALL;
			case "mellohi" -> SoundEvents.MUSIC_DISC_MELLOHI;
			case "stal" -> SoundEvents.MUSIC_DISC_STAL;
			case "strad" -> SoundEvents.MUSIC_DISC_STRAD;
			case "ward" -> SoundEvents.MUSIC_DISC_WARD;
			case "11" -> SoundEvents.MUSIC_DISC_11;
			case "pigstep" -> SoundEvents.MUSIC_DISC_PIGSTEP;
			case "otherside" -> SoundEvents.MUSIC_DISC_OTHERSIDE;
			case "relic" -> SoundEvents.MUSIC_DISC_RELIC;
			case "5" -> SoundEvents.MUSIC_DISC_5;
			default -> null;
		};

		if (client.player != null) {
			if (soundEvent != null) {
				discName = discName.substring(0, 1).toUpperCase() + discName.substring(1);
				client.player.sendMessage(TextFormat.styledText("Now playing: " + discName), false);
				soundManager.stopAll();
				client.player.playSound(soundEvent.value(), Float.MAX_VALUE, 1.0F);
			} else if (discName.equals("stop")) {
				client.player.sendMessage(TextFormat.styledText("Disc stopped."), false);
				soundManager.stopAll();
			} else {
				client.player.sendMessage(TextFormat.styledText("Invalid disc."), false);
			}
		}

		return 1;
	}
}
