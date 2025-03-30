package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Breadcrumbs {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("breadcrumbs")
				.executes(Breadcrumbs::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		if (client.player != null) {
			if (!ConfigManager.config.tickregistryBreadcrumbs) {
				client.player.sendMessage(TextFormat.styledText("Breadcrumbs enabled."), false);
			} else {
				client.player.sendMessage(TextFormat.styledText("Breadcrumbs disabled."), false);
			}
		}
		ConfigManager.config.tickregistryBreadcrumbs = !ConfigManager.config.tickregistryBreadcrumbs;
		ConfigManager.saveConfig();

		return 1;
	}
}
