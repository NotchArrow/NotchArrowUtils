package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class FastPlace {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("fastplace")
				.executes(FastPlace::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			if (!ConfigManager.config.tickregistryFastPlace) {
				client.player.sendMessage(TextFormat.styledText("Fast Place enabled."), false);
			} else {
				client.player.sendMessage(TextFormat.styledText("Fast Place disabled."), false);
			}
		}
		ConfigManager.config.tickregistryFastPlace = !ConfigManager.config.tickregistryFastPlace;
		ConfigManager.saveConfig();

		return 1;
	}
}
