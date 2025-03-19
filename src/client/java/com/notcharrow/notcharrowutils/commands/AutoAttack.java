package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class AutoAttack {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("autoattack")
				.executes(AutoAttack::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		ConfigManager.config.tickregistryAutoAttack = !ConfigManager.config.tickregistryAutoAttack;
		ConfigManager.saveConfig();
		if (client.player != null) {
			if (ConfigManager.config.tickregistryAutoAttack) {
				client.player.sendMessage(TextFormat.styledText("Auto attack is now enabled."), false);
			} else {
				client.player.sendMessage(TextFormat.styledText("Auto attack is now disabled."), false);
			}
		}

		return 1;
	}
}
