package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Statistics {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("statistics")
				.executes(Statistics::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			if (!ConfigManager.config.mixinStatistics) {
				client.player.sendMessage(TextFormat.styledText("Singleplayer Statistics on the pause menu enabled."), false);
				client.player.sendMessage(TextFormat.styledText("Requires a world relog to being tracking properly."), false);
			} else {
				client.player.sendMessage(TextFormat.styledText("Singleplayer Statistics on the pause menu disabled."), false);
			}
		}
		ConfigManager.config.mixinStatistics = !ConfigManager.config.mixinStatistics;
		ConfigManager.saveConfig();

		return 1;
	}
}
