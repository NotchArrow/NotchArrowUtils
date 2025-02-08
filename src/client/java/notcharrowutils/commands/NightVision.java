package notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.config.ConfigManager;
import notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class NightVision {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("nightvision")
				.executes(NightVision::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			client.player.sendMessage(TextFormat.styledText("Nightvision toggled."));
		}
		ConfigManager.config.tickregistryNightVision = !ConfigManager.config.tickregistryNightVision;

		return 1;
	}
}
