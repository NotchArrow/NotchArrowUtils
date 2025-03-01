package com.notcharrow.notcharrowutils.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class AutoFish {
	private static final MinecraftClient client = MinecraftClient.getInstance();

	public static LiteralArgumentBuilder<FabricClientCommandSource> registerCommand() {
		return literal("autofish")
				.executes(AutoFish::execute);
	}

	private static int execute(CommandContext<FabricClientCommandSource> context) {
		ConfigManager.config.tickregistryAutoFishMode = !ConfigManager.config.tickregistryAutoFishMode;
		ConfigManager.saveConfig();
		if (ConfigManager.config.tickregistryAutoFishMode) {
			client.player.sendMessage(TextFormat.styledText("Auto fish is now enabled."), false);
			client.player.sendMessage(TextFormat.styledText("Cast your bobber to start fishing."), false);
			client.player.sendMessage(TextFormat.styledText("Auto recast is enabled with a 500ms to 1s delay."), false);
		} else {
			client.player.sendMessage(TextFormat.styledText("Auto fish is now disabled."), false);
		}

		return 1;
	}
}
