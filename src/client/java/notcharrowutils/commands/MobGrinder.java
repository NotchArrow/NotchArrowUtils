package notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.helper.TextFormat;
import notcharrowutils.ticks.TickRegistry;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class MobGrinder {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("mobgrinder")
				.executes(MobGrinder::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		TickRegistry.mobGrinderMode = !TickRegistry.mobGrinderMode;
		if (TickRegistry.mobGrinderMode) {
			client.player.sendMessage(TextFormat.styledText("Auto attack is now enabled."));
		} else {
			client.player.sendMessage(TextFormat.styledText("Auto attack is now disabled."));
		}

		return 1;
	}
}
