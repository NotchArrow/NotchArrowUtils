package notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.config.ConfigManager;
import notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class AutoReplant {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("autoreplant")
				.executes(AutoReplant::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			client.player.sendMessage(TextFormat.styledText("Auto Replant toggled."));
		}
		ConfigManager.config.tickregistryAutoReplant = !ConfigManager.config.tickregistryAutoReplant;

		return 1;
	}
}