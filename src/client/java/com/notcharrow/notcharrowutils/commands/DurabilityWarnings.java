package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class DurabilityWarnings {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("durabilitywarnings")
				.executes(DurabilityWarnings::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			if (!ConfigManager.config.tickregistryDurabilityWarnings) {
				client.player.sendMessage(TextFormat.styledText("Durability Warnings enabled."), false);
			} else {
				client.player.sendMessage(TextFormat.styledText("Durability Warnings disabled."), false);
			}
		}
		ConfigManager.config.tickregistryDurabilityWarnings = !ConfigManager.config.tickregistryDurabilityWarnings;
		ConfigManager.saveConfig();

		return 1;
	}
}
