package notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.helper.TextFormat;
import notcharrowutils.ticks.TickRegistry;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class AutoTool {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("autotool")
				.executes(AutoTool::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		TickRegistry.autoTool = !TickRegistry.autoTool;
		if (TickRegistry.autoTool) {
			client.player.sendMessage(TextFormat.styledText("Auto tool is now enabled."));
			client.player.sendMessage(TextFormat.styledText("All tools used must be in your hotbar."));
		} else {
			client.player.sendMessage(TextFormat.styledText("Auto tool is now disabled."));
		}

		return 1;
	}
}
