package notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.config.ConfigManager;
import notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class CoordinateOverlay {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("coordinateoverlay")
				.executes(CoordinateOverlay::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		ConfigManager.config.tickregistryCoordinateOverlay = !ConfigManager.config.tickregistryCoordinateOverlay;
		ConfigManager.saveConfig();
		client.player.sendMessage(TextFormat.styledText("Coordinate overlay toggled."), false);

		return 1;
	}
}
