package notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import notcharrowutils.config.ConfigManager;
import notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Breadcrumbs {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("breadcrumbs")
				.executes(Breadcrumbs::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		ConfigManager.config.tickregistryBreadcrumbs = !ConfigManager.config.tickregistryBreadcrumbs;
		ConfigManager.saveConfig();
		client.player.sendMessage(TextFormat.styledText("Breadcrumbs toggled."), false);

		return 1;
	}
}
