package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class AutoRocket {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("autorocket")
				.executes(AutoRocket::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			if (!ConfigManager.config.tickregistryAutoRocket) {
				client.player.sendMessage(TextFormat.styledText("AutoRocket enabled."), false);
				client.player.sendMessage(TextFormat.styledText("Start flying with an Elytra while holding fireworks."), false);
			} else {
				client.player.sendMessage(TextFormat.styledText("AutoRocket disabled."), false);
			}
		}
		ConfigManager.config.tickregistryAutoRocket = !ConfigManager.config.tickregistryAutoRocket;
		ConfigManager.saveConfig();

		return 1;
	}
}