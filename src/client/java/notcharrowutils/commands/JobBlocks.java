package notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class JobBlocks {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("jobblocks")
				.executes(JobBlocks::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			client.player.sendMessage(TextFormat.styledText(
					"""
					Barrel - Fisherman
					Blast Furnace - Armorer
					Brewing Stand - Cleric
					Cartography Table - Cartographer
					Cauldron - Leatherworker
					Composter - Farmer
					Fletching Table - Fletcher
					Grindstone - Weaponsmith
					Lectern - Librarian
					Loom - Shepherd
					Smithing Table - Toolsmith
					Smoker - Butcher
					Stonecutter - Mason
					"""
			), false);
		}

		return 1;
	}
}
